package com.ecommerce.ecomm_product_service.repository;

import com.ecommerce.ecomm_product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
    SELECT p
    FROM Product p
    WHERE (
        LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
     OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
     OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    )
    AND (:budget IS NULL OR p.price <= :budget)
    """)
    List<Product> searchProducts(@Param("keyword") String keyword,
                                 @Param("budget") BigDecimal budget);
    List<Product> findByCategoryId(Long categoryId);
}
