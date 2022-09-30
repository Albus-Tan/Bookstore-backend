package com.mybookstore.mybookstorebackend.message;

import com.mybookstore.mybookstorebackend.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "CreateOrder", groupId = "group_create_order")
    public void CreateOrderControllerListener(ConsumerRecord<String, String> record) {
        String uid = record.value();
        System.out.println("CreateOrderListener get uid " + uid);
        Integer oid = orderService.createOrderFromUserCart(Integer.valueOf(uid));
        kafkaTemplate.send("CreateOrderResult",  "key", oid.toString());
    }

    @KafkaListener(topics = "CreateOrderResult", groupId = "group_create_order")
    public void CreateOrderResultListener(ConsumerRecord<String, String> record) {
        String value = record.value();
        System.out.println("CreateOrderResultListener order_id " + value);
    }
}
