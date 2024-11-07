package com.pageupcomputers.bolComWebhookReceiver.Models;

import java.time.LocalDateTime;
import java.util.UUID;
import com.pageupcomputers.bolComWebhookReceiver.DTO.OrderDetailsDTO;
import com.pageupcomputers.bolComWebhookReceiver.Enums.Status;
import com.pageupcomputers.bolComWebhookReceiver.Utils.LocalDateTimeHelper;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * Model for storing the processed orders in the database.
 */
@Entity
@Table(name = "processed_orders")
public class Order {
    
    @Id
    private UUID id;
    private String orderId;
    private LocalDateTime orderPlaced;
    private LocalDateTime orderProcessed;
    private String country;

    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Invoice number the system receives from the administration software
     */
    private String invoiceNumber;

    /**
     * Boolean variable indicating if the invoice is sent to the customer by e-mail
     */
    private Boolean invoiceSentByEmail;

    /**
     * Boolean variable indicating if the invoice is uploaded to the user's account on the marketplace
     */
    private Boolean invoiceUploadedOnMarketplace;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderPlaced() {
        return this.orderPlaced;
    }

    public void setOrderPlaced(LocalDateTime orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public LocalDateTime getOrderProcessed() {
        return this.orderProcessed;
    }

    public void setOrderProcessed(LocalDateTime orderProcessed) {
        this.orderProcessed = orderProcessed;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Boolean isInvoiceSentByEmail() {
        return this.invoiceSentByEmail;
    }

    public Boolean getInvoiceSentByEmail() {
        return this.invoiceSentByEmail;
    }

    public void setInvoiceSentByEmail(Boolean invoiceSentByEmail) {
        this.invoiceSentByEmail = invoiceSentByEmail;
    }

    public Boolean isInvoiceUploadedOnMarketplace() {
        return this.invoiceUploadedOnMarketplace;
    }

    public Boolean getInvoiceUploadedOnMarketplace() {
        return this.invoiceUploadedOnMarketplace;
    }

    public void setInvoiceUploadedOnMarketplace(Boolean invoiceUploadedOnMarketplace) {
        this.invoiceUploadedOnMarketplace = invoiceUploadedOnMarketplace;
    }

    public Order() {

    }

    public Order(OrderDetailsDTO order) {
        this.id = UUID.randomUUID();
        this.orderId = order.getOrderId();
        this.orderPlaced = LocalDateTimeHelper.convertOffsetDateTimeToLocalDateTime(order.getOrderPlacedDateTime());
        this.orderProcessed = LocalDateTime.now();
        this.country = order.getBillingDetails().getCountryCode();
        this.status = Status.OPEN;
        this.invoiceSentByEmail = false;
        this.invoiceUploadedOnMarketplace = false;
    }
    
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", orderId='" + getOrderId() + "'" +
            ", orderPlaced='" + getOrderPlaced() + "'" +
            ", orderProcessed='" + getOrderProcessed() + "'" +
            ", country='" + getCountry() + "'" +
            ", status='" + getStatus() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", invoiceSentByEmail='" + isInvoiceSentByEmail() + "'" +
            ", invoiceUploadedOnMarketplace='" + isInvoiceUploadedOnMarketplace() + "'" +
            "}";
    }
}
