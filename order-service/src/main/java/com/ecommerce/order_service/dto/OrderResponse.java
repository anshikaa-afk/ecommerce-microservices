package com.ecommerce.order_service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long orderId;

    private String status;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;
}
