package com.pageupcomputers.bolComWebhookReceiver.DTO;

/**
 * DTO for the billing details (Details visible on invoice)
 */
public class BillingDetailsDTO {
    private String salutation;
    private String firstName;
    private String surname;
    private String streetName;
    private String houseNumber;
    private String houseNumberExtension;
    private String extraAddressInformation;
    private String zipCode;
    private String city;
    private String countryCode;
    private String email;
    private String company;
    private String vatNumber;
    private String kvkNumber;

    public String getSalutation() {
        return this.salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetname(String streetName) {
        this.streetName = streetName;
    }

    public String getHousenumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberExtension() {
        return this.houseNumberExtension;
    }

    public void setHouseNumberExtension(String houseNumberExtension) {
        this.houseNumberExtension = houseNumberExtension;
    }

    public String getExtraAddressInformation() {
        return this.extraAddressInformation;
    }

    public void setExtraAddressInformation(String extraAddressInformation) {
        this.extraAddressInformation = extraAddressInformation;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVatNumber() {
        return this.vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getKvkNumber() {
        return this.kvkNumber;
    }

    public void setKvkNumber(String kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public BillingDetailsDTO() {

    }

    @Override
    public String toString() {
        return "{" +
            " salutation='" + getSalutation() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", streetName='" + getStreetName() + "'" +
            ", houseNumber='" + getHousenumber() + "'" +
            ", houseNumberExtension='" + getHouseNumberExtension() + "'" +
            ", extraAddressInformation='" + getExtraAddressInformation() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", city='" + getCity() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", email='" + getEmail() + "'" +
            ", company='" + getCompany() + "'" +
            ", vatNumber='" + getVatNumber() + "'" +
            ", kvkNumber='" + getKvkNumber() + "'" +
            "}";
    }
}
