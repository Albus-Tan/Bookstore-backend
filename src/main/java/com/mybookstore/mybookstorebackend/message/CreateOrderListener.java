package com.mybookstore.mybookstorebackend.message;

import com.mybookstore.mybookstorebackend.service.OrderService;
import com.mybookstore.mybookstorebackend.websocket.WebSocketServer;
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

    @Autowired
    private WebSocketServer webSocketServer;

    @KafkaListener(topics = "CreateOrder", groupId = "group_create_order")
    public void CreateOrderControllerListener(ConsumerRecord<String, String> record) {
        String uid = record.value();
        System.out.println("CreateOrderListener get uid " + uid);
        Integer oid = orderService.createOrderFromUserCart(Integer.valueOf(uid));
        kafkaTemplate.send("CreateOrderResult",  "key", uid + ";" +oid.toString());
    }

    @KafkaListener(topics = "CreateOrderResult", groupId = "group_create_order")
    public void CreateOrderResultListener(ConsumerRecord<String, String> record) {
        String value = record.value();
        String[] para = value.split(";");
        System.out.println("CreateOrderResultListener order_id " + para[1] + " uid " + para[0]);
        webSocketServer.sendMessage(para[1],para[0]);
    }
}
