package com.ecommerce.ai_service.dto;

import java.math.BigDecimal;

public record ProductDto(Long id, String name, String description,
                         BigDecimal price, Integer stock, String category) {
}
