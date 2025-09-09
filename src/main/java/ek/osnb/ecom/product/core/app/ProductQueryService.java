package ek.osnb.ecom.product.core.app;

import ek.osnb.ecom.product.core.app.exceptions.NotFoundException;
import ek.osnb.ecom.product.core.domain.model.Product;
import ek.osnb.ecom.product.core.domain.ports.in.ProductQueryUseCase;
import ek.osnb.ecom.product.core.domain.ports.out.LoadProductPort;

public class ProductQueryService implements ProductQueryUseCase {
    private final LoadProductPort loadProduct;

    public ProductQueryService(LoadProductPort loadProduct) {
        this.loadProduct = loadProduct;
    }

    @Override
    public ProductView getProductById(Long productId) {
        Product product = loadProduct.load(productId);
        System.out.println("App layer: " + product.getId());
//        try {
//            product = ;
//
//        } catch (RuntimeException e) {
//            throw new NotFoundException("Product not found with id: " + productId);
//        }

        return new ProductView(product.getId(), product.getName(), product.getPrice());
    }
}
