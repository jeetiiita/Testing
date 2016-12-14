package com.clicktable.support.model;

import com.clicktable.support.util.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints.Required;

import javax.persistence.*;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class Payment extends BaseEntity {

    public static final String STATUS_PROCESSING = "Processing";
    private static final long serialVersionUID = 6962249179926116478L;
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    //	@Required(message = ErrorCodes.ORDER_ID_REQUIRED)
    @JsonProperty("order_id")
    @Transient
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "invoiceId")
    @JsonIgnore
    private Invoice Invoice;
    @JsonProperty("tracking_id")
    private String trackingId;
    @JsonProperty("bank_ref_no")
    private String bankRefNo;
    @Required(message = ErrorCodes.PAYMENT_ORDER_STATUS_REQUIRED)
    @JsonProperty("order_status")
    private String orderStatus;
    @JsonProperty("failure_message")
    private String failureMessage;
    @JsonProperty("payment_mode")
    private String paymentMode;
    @JsonProperty("card_name")
    private String cardName;
    // as per the information from CCavenue sometimes it is not sent from the bank side
    @JsonProperty("status_code")
    private Integer statusCode;
    // as per the information from CCavenue sometimes it is not sent from the bank side
    @JsonProperty("status_message")
    private String statusMessage;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("amount")
    private BigDecimal amount;

    @Override
    public String toString() {
        return "id=" + id + " orderId=" + orderId + " bankRefNo=" + bankRefNo + " orderStatus=" + orderStatus
                + " orderStatus=" + orderStatus + " failureMessage=" + failureMessage + " paymentMode=" + paymentMode
                + " cardName" + cardName + " statusCode" + statusCode + " statusMessage=" + statusMessage + " currency"
                + currency + " amount" + amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getBankRefNo() {
        return bankRefNo;
    }

    public void setBankRefNo(String bankRefNo) {
        this.bankRefNo = bankRefNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Invoice getInvoice() {
        return Invoice;
    }

    public void setInvoice(Invoice invoice) {
        Invoice = invoice;
    }

}
