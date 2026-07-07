package com.ecommerce.order_service.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEvent {

    private Long productId;

    private Integer quantity;
}

