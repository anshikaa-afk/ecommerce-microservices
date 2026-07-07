package com.ecommerce.ecomm_product_service.config;

import com.ecommerce.ecomm_product_service.entity.Category;
import com.ecommerce.ecomm_product_service.entity.Product;
import com.ecommerce.ecomm_product_service.repository.CategoryRepository;
import com.ecommerce.ecomm_product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class ProductDataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if(productRepository.count() > 0){
            return;
        }

        Category laptop = categoryRepository.findByName("Laptop").orElseThrow();

        Category mobile = categoryRepository.findByName("Mobile").orElseThrow();

        Category monitor = categoryRepository.findByName("Monitor").orElseThrow();

        List<Product> products = List.of(
                Product.builder()
                        .name("Lenovo LOQ 15")
                        .description("Intel Core i5, RTX 4050, 16GB RAM, 512GB SSD")
                        .price(new BigDecimal("74999"))
                        .stock(20)
                        .category(laptop)
                        .build(),

                Product.builder()
                        .name("Acer Nitro V")
                        .description("Intel Core i5, RTX 4050 Gaming Laptop")
                        .price(new BigDecimal("69999"))
                        .stock(18)
                        .category(laptop)
                        .build(),

                Product.builder()
                        .name("HP Victus 15")
                        .description("Ryzen 7 Gaming Laptop")
                        .price(new BigDecimal("65999"))
                        .stock(15)
                        .category(laptop)
                        .build(),

                Product.builder()
                        .name("Samsung Galaxy S25")
                        .description("Flagship Android Smartphone")
                        .price(new BigDecimal("79999"))
                        .stock(30)
                        .category(mobile)
                        .build(),

                Product.builder()
                        .name("Dell 27 Inch Monitor")
                        .description("27-inch IPS Monitor")
                        .price(new BigDecimal("18999"))
                        .stock(12)
                        .category(monitor)
                        .build()

        );

        productRepository.saveAll(products);

        System.out.println("Products Seeded Successfully");
    }
}
