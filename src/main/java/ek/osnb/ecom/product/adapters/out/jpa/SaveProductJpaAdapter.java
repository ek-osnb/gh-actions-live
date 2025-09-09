package ek.osnb.ecom.product.adapters.out.jpa;

import ek.osnb.ecom.product.core.domain.ports.out.SaveProductPort;
import org.springframework.stereotype.Service;

@Service
public class SaveProductJpaAdapter implements SaveProductPort {
    private final ProductRepository productRepository;

    public SaveProductJpaAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductEntity loadProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    @Override
    public Long save(Long productId, String name, String description, double price) {
        ProductEntity newProduct = new ProductEntity(productId, name, price,null);
        productRepository.save(newProduct);
        return newProduct.getId();
    }
}
