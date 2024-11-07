package com.pageupcomputers.bolComWebhookReceiver.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrderDetailsDTO;
import com.pageupcomputers.bolComWebhookReceiver.Models.Order;
import com.pageupcomputers.bolComWebhookReceiver.Repositories.OrderRepository;

@Service
public class OrderService {

    /**
     * Inject OrderRepository for communicating with the database table processed_orders
     */
    @Autowired
    private OrderRepository orderRepository;

    /**
     * Inject RabbitMQ Connection template for communicating with the RabbitMQ Service
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    /**
     * Instanstiate the Logger class
     */
    Logger logger = LoggerFactory.getLogger(OrderService.class);

    /**
     * Function to add a new order to the RabbitMQ Queue and persist the order information in the database
     * @param orderDetails
     * @return The order that has been added to the queue and persist in the database
     */
    public Order queueOrder(OrderDetailsDTO orderDetails) {

        logger.debug(String.format("OrderDetailsDTO contents: %s", orderDetails.toString()));
        
        Order savedOrder = null;
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, orderDetails);
            logger.info(String.format("Order %s successfully added to processing queue", orderDetails.getOrderId()));
            
            Order order = new Order(orderDetails);
            savedOrder = orderRepository.save(order);
            logger.info(String.format("Order %s successfully stored in database!", orderDetails.getOrderId()));
        } 
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return savedOrder;
    }

    public boolean orderExists(String orderId) {
        return orderRepository.existsByOrderId(orderId);
    }

}
