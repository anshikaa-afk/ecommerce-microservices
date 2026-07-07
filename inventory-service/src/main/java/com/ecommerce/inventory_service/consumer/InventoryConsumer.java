package com.ecommerce.inventory_service.consumer;

import com.ecommerce.inventory_service.event.OrderCreatedEvent;
import com.ecommerce.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consume(OrderCreatedEvent event){
        log.info("Inventory Update Started");
        inventoryService.reduceStock(event);
        log.info("Reducing inventory for Order {}",
                event.getOrderId());
    }
}
