package com.ecommerce.notification_service.event;

import lombok.*;

import java.math.BigDecimal;

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
