package ek.osnb.ecom.product.core.domain.ports.out;

public interface SaveProductPort {
    Long save(Long productId, String name, String description, double price);
}
