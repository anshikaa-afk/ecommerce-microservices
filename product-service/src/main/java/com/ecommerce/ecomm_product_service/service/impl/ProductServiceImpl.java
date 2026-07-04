package com.ecommerce.ecomm_product_service.service.impl;

import com.ecommerce.ecomm_product_service.dto.ProductRequest;
import com.ecommerce.ecomm_product_service.dto.ProductResponse;
import com.ecommerce.ecomm_product_service.entity.Category;
import com.ecommerce.ecomm_product_service.entity.Product;
import com.ecommerce.ecomm_product_service.exception.CategoryNotFoundException;
import com.ecommerce.ecomm_product_service.exception.ProductNotFoundException;
import com.ecommerce.ecomm_product_service.repository.CategoryRepository;
import com.ecommerce.ecomm_product_service.repository.ProductRepository;
import com.ecommerce.ecomm_product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found"
                ));

        Product product = Product.builder().name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(category)
                .build();
        Product saved = productRepository.save(product);
        return mapToResponse(saved);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->
                        new ProductNotFoundException(
                                "Product not found with id "+id));
        return mapToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->
                        new ProductNotFoundException(
                                "Product not found with id "+id));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->new CategoryNotFoundException(
                        "Category not found with id "+id
                ));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);

        Product updated = productRepository.save(product);
        return mapToResponse(updated);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(Product product){
        return ProductResponse.builder().id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
