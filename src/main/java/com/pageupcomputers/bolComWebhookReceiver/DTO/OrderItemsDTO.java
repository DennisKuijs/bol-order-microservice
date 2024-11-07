package com.pageupcomputers.bolComWebhookReceiver.DTO;

import java.math.BigDecimal;

/**
 * DTO for the order items (Items that are included in a specific order)
 */
public class OrderItemsDTO {
    private ProductDTO product;
    private Integer quantity;
    private Integer quantityShipped;
    private Integer quantityCancelled;
    private BigDecimal unitPrice;

    /**
     * The commission that is payed to the marketplace
     */
    private BigDecimal commission;


    public ProductDTO getProduct() {
        return this.product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityShipped() {
        return this.quantityShipped;
    }

    public void setQuantityShipped(Integer quantityShipped) {
        this.quantityShipped = quantityShipped;
    }

    public Integer getQuantityCancelled() {
        return this.quantityCancelled;
    }

    public void setQuantityCancelled(Integer quantityCancelled) {
        this.quantityCancelled = quantityCancelled;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getCommission() {
        return this.commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public OrderItemsDTO() {
        
    }

    @Override
    public String toString() {
        return "{" +
            " product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", quantityShipped='" + getQuantityShipped() + "'" +
            ", quantityCancelled='" + getQuantityCancelled() + "'" +
            ", unitPrice='" + getUnitPrice() + "'" +
            ", commission='" + getCommission() + "'" +
            "}";
    }


}
