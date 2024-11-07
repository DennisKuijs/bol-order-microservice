package com.pageupcomputers.bolComWebhookReceiver.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrderDetailsDTO;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrderQueryDTO;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrdersDTO;
import com.pageupcomputers.bolComWebhookReceiver.Exceptions.BolComBadCredentialsException;
import com.pageupcomputers.bolComWebhookReceiver.Exceptions.BolComBadRequestException;
import com.pageupcomputers.bolComWebhookReceiver.Exceptions.BolComOrderNotFoundException;
import com.pageupcomputers.bolComWebhookReceiver.Models.Order;
import com.pageupcomputers.bolComWebhookReceiver.Services.BolComAPIService;
import com.pageupcomputers.bolComWebhookReceiver.Services.OrderService;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    //Inject BolcomAPIService
    @Autowired
    private BolComAPIService bolComAPIService;

    //Inject Orderservice
    @Autowired
    private OrderService orderService;

    //Instanstiate the Logger class
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * Retrieves Bol.com orders based on query parameters and combines them with the order details before sending them to the RabbitMQ Exchange for further processing
     * @param orderQuery
     * @return OrdersDTO List with the processed bol.com orders
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<OrdersDTO> processOrders(OrderQueryDTO orderQuery) {
        
        logger.debug(String.format("OrderQueryDTO contents: %s", orderQuery.toString()));

        OrdersDTO orders = null;
        try {
            orders = bolComAPIService.retrieveOrders(orderQuery);
            logger.debug(String.format("OrdersDTO contents: %s", orders.toString()));
            orders.getOrders().forEach(order -> {
                logger.info(String.format("Processing order %s....", order.getOrderId()));
                logger.info(String.format("Check if order %s exists in database", order.getOrderId()));
                if(!orderService.orderExists(order.getOrderId())) {
                    logger.info(String.format("Order %s does not exists in database", order.getOrderId()));
                    
                    logger.info(String.format("Retrieving order details for order %s", order.getOrderId()));
                    OrderDetailsDTO orderDetails = bolComAPIService.retrieveOrderDetails(order.getOrderId());
                    
                    logger.debug(String.format("OrderDetailsDTO contents: %s", orderDetails.toString()));

                    logger.info(String.format("Add order %s to processing queue", order.getOrderId()));
                    Order queuedOrder = orderService.queueOrder(orderDetails);
                    logger.debug(String.format("Order contents: %s", queuedOrder.toString()));
                    
                    logger.info(String.format("Order %s successfully processed", order.getOrderId()));
                }
            });
        } 
        catch(BolComBadCredentialsException e) {
            logger.error(String.format(e.getMessage(), orderQuery.toString()));
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        catch(BolComOrderNotFoundException e) {
            logger.error(String.format(e.getMessage(), orderQuery.toString()));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch(JWTDecodeException | BolComBadRequestException e) {
            logger.error(String.format(e.getMessage(), orderQuery.toString()));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something did go wrong on our end, please try again later!");
        }
        catch(Exception e) {
            logger.error(String.format(e.getMessage(), orderQuery.toString()));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, something did go wrong on our end, please try again later!");
        }

        logger.info(String.format("All orders successfully processed %s", orders));

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    } 
}
