package ek.osnb.ecom.product.adapters.out.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
class ProductEntity extends BaseEntity {
    private String name;
    private Double price;
    private String description;

    public ProductEntity() {}

    public ProductEntity(Long id, String name, Double price, String description) {
        super(id);
        this.name = name;
        this.price = price;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
