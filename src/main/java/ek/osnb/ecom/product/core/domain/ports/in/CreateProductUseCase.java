package ek.osnb.ecom.product.core.domain.ports.in;

public interface CreateProductUseCase {
    record Command(String name, String description, double price) {}
    record Result(String productId) {}
    Result create(Command command);
}
