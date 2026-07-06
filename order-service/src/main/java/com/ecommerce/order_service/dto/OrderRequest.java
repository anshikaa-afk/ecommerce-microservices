package com.ecommerce.order_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "User Id is required")
    private Long userId;

    @Valid
    @NotEmpty(message = "Order should contain at least one item")
    private List<OrderItemRequest> items;
}
