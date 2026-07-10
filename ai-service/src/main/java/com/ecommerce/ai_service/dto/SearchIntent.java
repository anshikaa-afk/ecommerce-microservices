package com.ecommerce.ai_service.dto;

import java.math.BigDecimal;

public record SearchIntent(

        String category,

        BigDecimal budget,

        String purpose

) {}
