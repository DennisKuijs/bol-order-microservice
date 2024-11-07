package com.pageupcomputers.bolComWebhookReceiver.DTO;

/**
 * DTO for a single order (Order Id for the order that is fetched based on the query parameters)
 */
public class OrderDTO {
    private String orderId;

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderDTO() {
    }
    
    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            "}";
    }
}
