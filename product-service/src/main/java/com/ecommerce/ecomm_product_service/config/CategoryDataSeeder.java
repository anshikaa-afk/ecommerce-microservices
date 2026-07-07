package com.ecommerce.ecomm_product_service.config;

import com.ecommerce.ecomm_product_service.entity.Category;
import com.ecommerce.ecomm_product_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class CategoryDataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count() > 0) {
            return;
        }

        List<Category> categories = List.of(
                Category.builder()
                        .name("Laptop")
                        .description("Laptops and notebooks")
                        .build(),

                Category.builder()
                        .name("Mobile")
                        .description("Smartphones")
                        .build(),

                Category.builder()
                        .name("Monitor")
                        .description("Computer monitors")
                        .build(),

                Category.builder()
                        .name("Keyboard")
                        .description("Mechanical and membrane keyboards")
                        .build(),

                Category.builder()
                        .name("Mouse")
                        .description("Wireless and gaming mouse")
                        .build(),

                Category.builder()
                        .name("SSD")
                        .description("Solid State Drives")
                        .build(),

                Category.builder()
                        .name("RAM")
                        .description("Memory Modules")
                        .build()

        );

        categoryRepository.saveAll(categories);

        System.out.println("Categories Seeded");
    }
}
