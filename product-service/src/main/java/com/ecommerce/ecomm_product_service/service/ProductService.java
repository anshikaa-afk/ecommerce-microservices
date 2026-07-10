package com.ecommerce.ecomm_product_service.service;

import com.ecommerce.ecomm_product_service.dto.ProductRequest;
import com.ecommerce.ecomm_product_service.dto.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

    List<ProductResponse> searchProducts(  String keyword, BigDecimal budget);

    List<ProductResponse> getProductsByCategory(Long categoryId);
}
