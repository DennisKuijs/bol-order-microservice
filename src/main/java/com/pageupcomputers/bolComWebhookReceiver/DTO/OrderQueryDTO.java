package com.pageupcomputers.bolComWebhookReceiver.DTO;

/**
 * DTO for the Order Query parameters 
 */
public class OrderQueryDTO {
    private String fulfilmentMethod;
    private String status;
    private Integer interval;

    public String getFulfilmentMethod() {
        return this.fulfilmentMethod;
    }

    public void setFulfilmentMethod(String fulfilmentMethod) {
        this.fulfilmentMethod = fulfilmentMethod;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getInterval() {
        return this.interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }


    public OrderQueryDTO() {
    }


    @Override
    public String toString() {
        return "{" +
            " fulfilmentMethod='" + getFulfilmentMethod() + "'" +
            ", status='" + getStatus() + "'" +
            ", interval='" + getInterval() + "'" +
            "}";
    }

}
