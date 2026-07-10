package com.ecommerce.ai_service.service.impl;

import com.ecommerce.ai_service.client.ProductClient;
import com.ecommerce.ai_service.dto.ProductDto;
import com.ecommerce.ai_service.dto.SearchIntent;
import com.ecommerce.ai_service.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {
    private final ChatClient chatClient;
    private final ProductClient productClient;

    @Override
    public String chat(String message) {

        SearchIntent intent = extractIntent(message);

        BigDecimal budget = intent.budget();

        if (budget != null && budget.compareTo(BigDecimal.ZERO) == 0) {
            budget = null;
        }

        List<ProductDto> products =
                productClient.searchProducts(intent.category(), budget);

        if(products.isEmpty()) {

            return """
                    Sorry,
                    
                    I couldn't find any products
                    matching your request.
                    """;
        }

        return generateRecommendation(products, message);
    }

    private SearchIntent extractIntent(String message) {

        String prompt = """
        Extract product search information.
        
        Return:
        
        category
        
        budget
        
        purpose
        
        User:
        
        %s
        """.formatted(message);

        return chatClient.prompt(prompt)
                .call()
                .entity(SearchIntent.class);

    }

    private String generateRecommendation(
            List<ProductDto> products,
            String question) {
        String productContext = buildProductContext(products);

        String prompt = """
        You are an experienced e-commerce shopping assistant.
        
        Rules:
        
        1. Recommend ONLY from the products provided.
        2. Never invent products.
        3. Mention the exact price.
        4. Explain why each recommendation fits the customer's request.
        5. Use bullet points.
        6. If multiple products are suitable, rank them.
        
        Available Products:
        
        %s
        
        Customer Question:
        
        %s
        
        Provide a helpful recommendation.
        """.formatted(productContext, question);

        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    private String buildProductContext(List<ProductDto> products) {

        return products.stream()
                .map(product -> """
                    Name: %s
                    Category: %s
                    Description: %s
                    Price: ₹%s
                    Stock: %d
                    """
                        .formatted(
                                product.name(),
                                product.category(),
                                product.description(),
                                product.price(),
                                product.stock()))
                .collect(java.util.stream.Collectors.joining("\n------------------\n"));
    }
}
