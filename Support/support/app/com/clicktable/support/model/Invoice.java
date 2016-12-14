package com.clicktable.support.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

/*
 * created by j.yadav
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class Invoice extends BaseEntity {

    private static final long serialVersionUID = 9058605119475876333L;
    @Id
    @GeneratedValue
    private Long id;
    private Long invoiceId;
    private Integer bookNumber;
    @Required(message = ErrorCodes.INVOICE_REST_GUID_REQUIRED)
    @MaxLength(message = ErrorCodes.INVOICE_REST_GUID_MAXLENGTH, value = 50)
    private String restaurantGuid;
    private String status = SupportConstants.CREATED;
    private String currency = SupportConstants.CURRENCY_IND;
    private BigDecimal basicAmount;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    @Max(message = ErrorCodes.INVOICE_ROUND_AMOUNT_MAX_VALUE, value = 1)
    @Min(message = ErrorCodes.INVOICE_ROUND_AMOUNT_MIN_VALUE, value = -1)
    private BigDecimal roundOffAmount;
    @Min(message = ErrorCodes.INVOICE_DISCOUNT_MIN_VALUE, value = 0)
    private BigDecimal discount = new BigDecimal(0);
    private Integer netAmount;
    @JsonFormat(pattern = SupportConstants.DATE_FORMAT, timezone = SupportConstants.TIMEZONE)
    private Date invoiceDate;
    @JsonFormat(pattern = SupportConstants.DATE_FORMAT, timezone = SupportConstants.TIMEZONE)
    private Date dueDate;
    @Required(message = ErrorCodes.INVOICE_MONTH_REQUIRED)
    @Max(message = ErrorCodes.INVOICE_MONTH_MAX_VALUE, value = 12)
    @Min(message = ErrorCodes.INVOICE_MONTH_MIN_VALUE, value = 1)
    private Integer finMonth;
    @Required(message = ErrorCodes.INVOICE_YEAR_REQUIRED)
    private Integer finYear;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billingInfoId")
    private InvoiceBillingInfo billingInfo;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InvoiceLine> invoiceLines;
    @Transient
    private String paymentMode;
    @Transient
    private Date paymentDate;

    @Override
    public String toString() {
        return "id=" + id + " InvoiceId=" + invoiceId + " bookNumber=" + bookNumber + " restaurantGuid="
                + restaurantGuid + " status=" + status + " currency=" + currency + " basicAmount=" + basicAmount
                + " totalAmount" + totalAmount + " roundOffAmount" + roundOffAmount + " netAmount=" + netAmount
                + " invoiceDate" + invoiceDate + " dueDate" + dueDate + " finMonth" + finMonth + " finYear" + finYear
                + " discount" + discount;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getRestaurantGuid() {
        return restaurantGuid;
    }

    public void setRestaurantGuid(String restaurantGuid) {
        this.restaurantGuid = restaurantGuid;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBasicAmount() {
        return basicAmount;
    }

    public void setBasicAmount(BigDecimal basicAmount) {
        this.basicAmount = basicAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRoundOffAmount() {
        return roundOffAmount;
    }

    public void setRoundOffAmount(BigDecimal roundOffAmount) {
        this.roundOffAmount = roundOffAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Integer netAmount) {
        this.netAmount = netAmount;
    }

    public Date getDueDate() {
        return dueDate == null ? null : (Date) dueDate.clone();
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate == null ? null : (Date) dueDate.clone();
    }

    public Integer getFinMonth() {
        return finMonth;
    }

    public void setFinMonth(Integer finMonth) {
        this.finMonth = finMonth;
    }

    public Integer getFinYear() {
        return finYear;
    }

    public void setFinYear(Integer finYear) {
        this.finYear = finYear;
    }

    public InvoiceBillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(InvoiceBillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public Date getInvoiceDate() {
        return invoiceDate == null ? null : (Date) invoiceDate.clone();
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setInvoieceDate(Date date) {
        this.invoiceDate = invoiceDate == null ? null : (Date) invoiceDate.clone();
    }

    public String getCtAddress() {
        return SupportConstants.CT_ADDRESS;
    }

    public String getCtPin() {
        return SupportConstants.CT_PIN;
    }

    public String getCtPhone() {
        return SupportConstants.CT_PHONE;
    }

    public String getCtEmail() {
        return SupportConstants.CT_EMAIL;
    }

    public String getCtWebsite() {
        return SupportConstants.CT_WEBSITE;
    }

    public String getCtServiceTax() {
        return SupportConstants.CT_SERVICE_TAX;
    }

    public String getCtVat() {
        return SupportConstants.CT_VAT;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


}