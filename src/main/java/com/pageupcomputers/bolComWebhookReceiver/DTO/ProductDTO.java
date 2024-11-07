package com.pageupcomputers.bolComWebhookReceiver.DTO;

/**
 * DTO for the product details (Information about the product that is bought in a specific order)
 */
public class ProductDTO {

    /**
     * The unique 13-number EAN code to identify the product
     */
    private String ean;

    /**
     * The title of the product
     */
    private String title;


    public String getEan() {
        return this.ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProductDTO() {

    }

    @Override
    public String toString() {
        return "{" +
            " ean='" + getEan() + "'" +
            ", title='" + getTitle() + "'" +
            "}";
    }

}
