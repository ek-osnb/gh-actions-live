package ek.osnb.ecom.product.config;

import ek.osnb.ecom.product.core.app.CreateProductService;
import ek.osnb.ecom.product.core.app.ProductQueryService;
import ek.osnb.ecom.product.core.domain.ports.in.ProductQueryUseCase;
import ek.osnb.ecom.product.core.domain.ports.out.LoadProductPort;
import ek.osnb.ecom.product.core.domain.ports.out.SaveProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIConfig {

    @Bean
    public ProductQueryUseCase productQueryUseCase(LoadProductPort loadProductPort) {
        return new ProductQueryService(loadProductPort);
    }

    @Bean
    public CreateProductService createProductUseCase(SaveProductPort saveProductPort) {
        return new CreateProductService(saveProductPort);
    }
}
