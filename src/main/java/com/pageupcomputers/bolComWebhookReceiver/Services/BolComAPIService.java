package com.pageupcomputers.bolComWebhookReceiver.Services;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrderDetailsDTO;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrderQueryDTO;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrdersDTO;
import com.pageupcomputers.bolComWebhookReceiver.Exceptions.BolComBadRequestException;
import com.pageupcomputers.bolComWebhookReceiver.Exceptions.BolComOrderNotFoundException;
import kong.unirest.Unirest;

@Service
public class BolComAPIService {

    /**
     * Inject Bol.com authenticate service
     */
    @Autowired
    private BolComAuthenticateService bolComAuthenticateService;

    /**
     * Instanstiate the Logger class
     */
    Logger logger = LoggerFactory.getLogger(BolComAPIService.class);

    /**
     * API Call to the Bol.com API to retrieve the orders based on different query parameters
     * @param fulfilmentMethod
     * @param status
     * @param changeInterval
     * @return OrdersDTO list with the bol.com orders that are fetched
     */
    public OrdersDTO retrieveOrders(OrderQueryDTO orderQuery) {

        logger.debug(String.format("fulfilmentMethod contents: %s", orderQuery.getFulfilmentMethod()));
        logger.debug(String.format("status contents: %s", orderQuery.getStatus()));
        logger.debug(String.format("changeInterval contents: %s", orderQuery.getInterval()));

        /**
         * Function to check if access token is still valid
         */
        bolComAuthenticateService.checkAccessToken();

        return Unirest.get("https://api.bol.com/retailer/orders")
        .header("accept", "application/vnd.retailer.V10+json")
        .header("Authorization", "Bearer " + System.getProperty("accessToken"))
        .queryString("fulfilment-method", orderQuery.getFulfilmentMethod())
        .queryString("status", orderQuery.getStatus())
        .queryString("change-interval-minute", orderQuery.getInterval())
        .asObject(OrdersDTO.class)
        .ifSuccess(response -> {
            if(response.getBody().getOrders() == null) {
                response.getBody().setOrders(Collections.emptyList());
                logger.info("There are no new orders to fetch, please try again later");
            } 
            else {
                logger.info(String.format("There are %s new orders fetched", response.getBody().getOrders().size()));
            }
        })
        .ifFailure(response -> {
            throw new BolComBadRequestException("There was a problem while fetching Bol.com orders, please try again later");
        })
        .getBody();
    }

    /**
     * API Call to the Bol.com API to retrieve the order details based on an orderId
     * @param orderId
     * @return OrderdetailsDTO with the order details for the specific orderId
     */
    public OrderDetailsDTO retrieveOrderDetails(String orderId) {

        logger.debug(String.format("OrderId contents: %s", orderId));

        /**
         * Function to check if access token is still valid
         */
        bolComAuthenticateService.checkAccessToken();

        return Unirest.get("https://api.bol.com/retailer/orders/{orderId}")
        .header("accept", "application/vnd.retailer.V10+json")
        .header("Authorization", "Bearer " + System.getProperty("accessToken"))
        .routeParam("orderId", orderId)
        .asObject(OrderDetailsDTO.class)
        .ifSuccess(response -> {
            logger.info(String.format("Order details for order %s succesfully fetched", response.getBody().getOrderId()));
        })
        .ifFailure(response -> {
            if(response.getStatus() == 400) {
                throw new BolComBadRequestException("There was a problem while fetching Bol.com orders details, please try again later");
            }
            else if(response.getStatus() == 404) {
                throw new BolComOrderNotFoundException("The order with the given orderId was not found!");
            }
        })
        .getBody();
    }
}


