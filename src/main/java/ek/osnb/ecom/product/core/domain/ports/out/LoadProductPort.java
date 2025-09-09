package ek.osnb.ecom.product.core.domain.ports.out;

import ek.osnb.ecom.product.core.domain.model.Product;

public interface LoadProductPort {
    Product load(Long productId);
}
