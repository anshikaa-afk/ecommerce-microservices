package com.ecommerce.inventory_service.event;

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
