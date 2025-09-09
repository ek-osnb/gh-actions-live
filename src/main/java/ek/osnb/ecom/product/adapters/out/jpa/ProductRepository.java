package ek.osnb.ecom.product.adapters.out.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
