package com.pageupcomputers.bolComWebhookReceiver.DTO;

import java.util.List;

/**
 * DTO for the order details (Includes order items and billing details)
 */
public class OrderDetailsDTO {
    private String orderId;
    private boolean pickupPoint;
    private String orderPlacedDateTime;
    private ShipmentDetailsDTO shipmentDetails;
    private BillingDetailsDTO billingDetails;
    private List<OrderItemsDTO> orderItems;

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean getPickupPoint() {
        return this.pickupPoint;
    }

    public void setPickupPoint(boolean pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getOrderPlacedDateTime() {
        return this.orderPlacedDateTime;
    }

    public void setOrderPlacedDateTime(String orderPlacedDateTime) {
        this.orderPlacedDateTime = orderPlacedDateTime;
    }

    public ShipmentDetailsDTO getShipmentDetails() {
        return this.shipmentDetails;
    }

    public void setShipmentDetails(ShipmentDetailsDTO shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    public BillingDetailsDTO getBillingDetails() {
        return this.billingDetails;
    }

    public void setBillingDetails(BillingDetailsDTO billingDetails) {
        this.billingDetails = billingDetails;
    }

    public List<OrderItemsDTO> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItemsDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderDetailsDTO() {}

    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            ", pickupPoint='" + getShipmentDetails() + "'" +
            ", orderPlacedDateTime='" + getOrderPlacedDateTime() + "'" +
            ", shipmentDetails='" + getShipmentDetails() + "'" +
            ", billingDetails='" + getBillingDetails() + "'" +
            ", orderItems='" + getOrderItems() + "'" +
            "}";
    }

}
