package com.ecommerce.ecomm_product_service.service;

import com.ecommerce.ecomm_product_service.dto.CategoryRequest;
import com.ecommerce.ecomm_product_service.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long id);

    CategoryResponse updateCategory(Long id,
                                    CategoryRequest request);

    void deleteCategory(Long id);
}
