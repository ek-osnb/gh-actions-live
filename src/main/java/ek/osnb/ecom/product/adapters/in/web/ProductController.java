package ek.osnb.ecom.product.adapters.in.web;

import ek.osnb.ecom.product.core.domain.ports.in.CreateProductUseCase;
import ek.osnb.ecom.product.core.domain.ports.in.ProductQueryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductQueryUseCase productQueryUseCase;
    private final CreateProductUseCase createProductUseCase;

    public ProductController(ProductQueryUseCase productQueryUseCase,CreateProductUseCase createProductUseCase) {
        this.productQueryUseCase = productQueryUseCase;
        this.createProductUseCase = createProductUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductQueryUseCase.ProductView> getProductById(@PathVariable Long id) {
        System.out.println("----- PRODUCT ENDPOINT -----");
        return ResponseEntity.ok(productQueryUseCase.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<CreateProductUseCase.Result> createProduct(@RequestBody CreateProductUseCase.Command command) {
        // Implementation for creating a product would go here
        return ResponseEntity.ok(createProductUseCase.create(command));
    }
}
