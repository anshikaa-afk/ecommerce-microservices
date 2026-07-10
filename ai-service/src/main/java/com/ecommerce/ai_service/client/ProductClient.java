package com.ecommerce.ai_service.client;

import com.ecommerce.ai_service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient restClient;

    public List<ProductDto> searchProducts(String keyword, BigDecimal budget) {

        return restClient.get()
                .uri(uriBuilder -> {

                    uriBuilder.path("/api/products/search")
                            .queryParam("keyword", keyword);

                    if (budget != null) {
                        uriBuilder.queryParam("budget", budget);
                    }

                    return uriBuilder.build();
                })
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
