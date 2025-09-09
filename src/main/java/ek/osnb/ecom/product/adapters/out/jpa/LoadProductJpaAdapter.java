package ek.osnb.ecom.product.adapters.out.jpa;

import ek.osnb.ecom.product.core.domain.model.Product;
import ek.osnb.ecom.product.core.domain.ports.out.LoadProductPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class LoadProductJpaAdapter implements LoadProductPort {
    private final ProductRepository productRepository;

    public LoadProductJpaAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductEntity loadProduct(Long id) {
        Optional<ProductEntity> en = productRepository.findById(id);
        if (en.isEmpty()) {
            throw new RuntimeException("Not found");
        }
        return en.get();
    }

    @Override
    public Product load(Long productId) {
        ProductEntity entity = loadProduct(productId);
        return mapToDomain(entity);
    }

    private Product mapToDomain(ProductEntity entity) {
        System.out.println(entity.getId());
        var obj = new Product(entity.getId(), entity.getName(), entity.getPrice());
        System.out.println("Obj: " +obj.getName() );
        return obj;
    }


}
