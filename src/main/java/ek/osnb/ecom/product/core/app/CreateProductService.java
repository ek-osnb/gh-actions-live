package ek.osnb.ecom.product.core.app;

import ek.osnb.ecom.product.core.domain.model.Product;
import ek.osnb.ecom.product.core.domain.ports.in.CreateProductUseCase;
import ek.osnb.ecom.product.core.domain.ports.out.SaveProductPort;

public class CreateProductService implements CreateProductUseCase {
    private final SaveProductPort saveProductPort;

    public CreateProductService(SaveProductPort saveProductPort) {
        this.saveProductPort = saveProductPort;
    }


    @Override
    public Result create(Command command) {
        Product newProduct = new Product(command.name(), command.price());

        saveProductPort.save(newProduct.getId(), newProduct.getName(), null, newProduct.getPrice());
        return new Result(newProduct.getId().toString());
    }
}
