package com.ecommerce.inventory_service.service;

import com.ecommerce.inventory_service.event.OrderCreatedEvent;
import com.ecommerce.inventory_service.event.OrderItemEvent;

public interface InventoryService {

    void reduceStock(OrderCreatedEvent event);
}
