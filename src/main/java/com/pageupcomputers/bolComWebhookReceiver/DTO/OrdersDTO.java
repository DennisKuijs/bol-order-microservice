package com.pageupcomputers.bolComWebhookReceiver.DTO;

import java.util.List;

/**
 * DTO for a list of fetched orders (In case there are multiple orders fetched at the same time)
 */
public class OrdersDTO {
    private List<OrderDTO> orders;

    public List<OrderDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public OrdersDTO() {
    }


    @Override
    public String toString() {
        return "{" +
            " orders='" + getOrders() + "'" +
            "}";
    }

}
