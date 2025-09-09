package ek.osnb.ecom.product.adapters.out.http;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import ek.osnb.ecom.product.core.domain.model.Product;
import ek.osnb.ecom.product.core.domain.ports.out.LoadProductPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class LoadProductHttpAdapter implements LoadProductPort {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public LoadProductHttpAdapter(RestTemplate restTemplate,@Value("${external.product.base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = Objects.requireNonNull(baseUrl,"Base URL must be provided");
    }

    @Override
    public Product load(Long productId) {
        System.out.println(baseUrl+"/"+productId);
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(baseUrl+"/"+productId,ApiResponse.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to load product with id: " + productId);
        }

        ApiResponse apiResponse = response.getBody();
        ApiData data = apiResponse.data();

        return mapToDomain(data);
    }

    private record ApiResponse(ApiData data) {}

    private record ApiData(
            Long id,
            String name,
            List<Price> prices
    ) {}

    private record Price(
            double price,
            @JsonAlias("starting_at")
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
            LocalDateTime startingAt,
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
            @JsonAlias("ending_at")
            LocalDateTime endingAt
            ) {}

    private Product mapToDomain(ApiData data) {
        Price currentPrice = getCurrentPrice(data.prices());
        return new Product(data.id(), data.name(), currentPrice.price());
    }

    private Price getCurrentPrice(List<Price> prices) {
        LocalDateTime now = LocalDateTime.now();
        for (Price price : prices) {
            if ((price.startingAt().isBefore(now) || price.startingAt().isEqual(now)) &&
                (price.endingAt() == null || price.endingAt().isAfter(now))) {
                return price;
            }
        }
        throw new RuntimeException("No current price found");
    }
}
