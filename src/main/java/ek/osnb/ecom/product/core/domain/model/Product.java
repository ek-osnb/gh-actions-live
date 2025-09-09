package ek.osnb.ecom.product.core.domain.model;

import ek.osnb.ecom.product.core.domain.exceptions.BusinessLogicException;

import java.util.concurrent.atomic.AtomicLong;

// DOMAIN CLASS PRODUCT
public class Product {
    private Long id;
    private String name;
    private double price;

    private static AtomicLong sequence = new AtomicLong(3000L);

    public Product(Long id, String name, double price) {
        this.id = id;
//        if (isValidName(name)) {
//            throw new BusinessLogicException("Product name cannot be empty or blank");
//        }
//        if (isValidPrice(price)) {
//            throw new BusinessLogicException("Price cannot be less than or equal to zero");
//        }
        this.name = name;
        this.price = price;
    }

    private boolean isValidName(String name) {
        return !(name == null || name.isEmpty() || name.isBlank());
    }

    private boolean isValidPrice(double price) {
        return price > 0;
    }

    public Product(String name, double price) {
        this.id = sequence.incrementAndGet();
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
