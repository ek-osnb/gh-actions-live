package ek.osnb.ecom.product.adapters.out.jpa;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
class BaseEntity {
    @Id
    private Long id;

    public BaseEntity() {}

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
