/**
 *
 */
package com.clicktable.support.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.clicktable.model.Restaurant;

/**
 * @author j.yadav
 */

@Entity
public class InvoiceBillingInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String address1;

    private String city;

    private String address2;

    private String legalName;

    private String email;

    private String phone;

    private Integer zipCode;

    private String accountId;

    private String state;

    private String country;

    public InvoiceBillingInfo() {

    }

    public InvoiceBillingInfo(Restaurant restaurant) {
        this.setAccountId(restaurant.getAccountId());
        this.setAddress1(restaurant.getAddressLine1());
        this.setAddress2(restaurant.getAddressLine2());
        this.setCity(restaurant.getCity());
        this.setCountry(restaurant.getCountryCode());
        this.setEmail(restaurant.getEmail());
        this.setLeagalName(restaurant.getLegalName());
        this.setPhone(restaurant.getPhoneNo1());
        this.setState(restaurant.getPhysicalState());
        this.setZipCode(restaurant.getZipcode());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public void setLeagalName(String legalName) {
        this.legalName = legalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
