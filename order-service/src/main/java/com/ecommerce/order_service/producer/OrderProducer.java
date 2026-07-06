package com.ecommerce.order_service.producer;

import com.ecommerce.order_service.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private static final String TOPIC = "order-created";

    public void publishOrderCreated(OrderCreatedEvent event){
        log.info("Publishing Order Event {}", event.getOrderId());
        kafkaTemplate.send(TOPIC, event);
    }
}
