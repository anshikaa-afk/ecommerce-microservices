package com.ecommerce.inventory_service.service.impl;

import com.ecommerce.inventory_service.event.OrderCreatedEvent;
import com.ecommerce.inventory_service.event.OrderItemEvent;
import com.ecommerce.inventory_service.repository.InventoryRepository;
import com.ecommerce.inventory_service.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public void reduceStock(OrderCreatedEvent event) {

    }
}
