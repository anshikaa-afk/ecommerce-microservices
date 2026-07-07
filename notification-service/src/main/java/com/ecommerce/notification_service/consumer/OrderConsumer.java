package com.ecommerce.notification_service.consumer;

import com.ecommerce.notification_service.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumer {
    @KafkaListener(
            topics = "order-created",
            groupId = "notification-group"
    )
    public void consume(OrderCreatedEvent event) {

        log.info("=====================================");
        log.info("EMAIL SENT SUCCESSFULLY");
        log.info("Order Id : {}", event.getOrderId());
        log.info("User Id  : {}", event.getUserId());
        log.info("Amount   : {}", event.getTotalAmount());
        log.info("=====================================");

    }
}
