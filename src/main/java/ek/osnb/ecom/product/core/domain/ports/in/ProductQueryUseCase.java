package ek.osnb.ecom.product.core.domain.ports.in;

public interface ProductQueryUseCase {
    record ProductView(Long id, String name, double price) {}

    ProductView getProductById(Long productId);
}
