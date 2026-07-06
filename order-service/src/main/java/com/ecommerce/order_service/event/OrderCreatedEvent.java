package com.ecommerce.order_service.event;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {
    private Long orderId;

    private Long userId;

    private BigDecimal totalAmount;
}
