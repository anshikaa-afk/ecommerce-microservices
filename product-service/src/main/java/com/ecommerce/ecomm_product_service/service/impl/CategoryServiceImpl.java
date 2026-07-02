package com.ecommerce.ecomm_product_service.service.impl;

import com.ecommerce.ecomm_product_service.dto.CategoryRequest;
import com.ecommerce.ecomm_product_service.dto.CategoryResponse;
import com.ecommerce.ecomm_product_service.entity.Category;
import com.ecommerce.ecomm_product_service.exception.CategoryAlreadyExistsException;
import com.ecommerce.ecomm_product_service.exception.CategoryNotFoundException;
import com.ecommerce.ecomm_product_service.repository.CategoryRepository;
import com.ecommerce.ecomm_product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        if(categoryRepository.existsByName(request.getName())){
            throw new CategoryAlreadyExistsException("Category already exixts");
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Category savedCategory = categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .description(savedCategory.getDescription())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .build()).toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id "+id));

        return CategoryResponse.builder().id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new CategoryNotFoundException(
                        "Category not found with id "+id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category updated = categoryRepository.save(category);

        return CategoryResponse.builder().id(updated.getId())
                .name(updated.getName())
                .description(updated.getDescription())
                .build();
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->
                new CategoryNotFoundException(
                        "Category not found with id "+id));

        categoryRepository.delete(category);
    }

}
