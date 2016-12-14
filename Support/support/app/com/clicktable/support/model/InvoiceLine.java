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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

/**
 * @author j.yadav
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class InvoiceLine extends BaseEntity {

    private static final long serialVersionUID = -3326536006832909059L;
    @Required(message = ErrorCodes.INVOICE_LINE_ITEM_REQUIRED)
    @ManyToOne
    @JoinColumn(name = "itemId")
    Item item;
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;
    @Required(message = ErrorCodes.INVOICE_LINE_REST_GUID_REQUIRED)
    @MaxLength(message = ErrorCodes.INVOICE_GUID_MAXLENGTH, value = 50)
    private String restaurantGuid;
    @Required(message = ErrorCodes.INVOICE_LINE_NUMBER_REQUIRED)
    private Integer lineNumber;
    @Required(message = ErrorCodes.INVOICE_LINE_ORDER_DATE_REQUIRED)
    @JsonFormat(pattern = SupportConstants.DATE_FORMAT, timezone = SupportConstants.TIMEZONE)
    private Date orderDate;
    @Required(message = ErrorCodes.INVOICE_LINE_ITEM_QTY_REQUIRED)
    private Integer itemQty;

    @Required(message = ErrorCodes.INVOICE_LINE_ITEM_PLAN_PRICE_REQUIRED)
    private BigDecimal itemPlanPrice;

    @Required(message = ErrorCodes.INVOICE_MONTH_REQUIRED)
    private Integer finMonth;

    @Required(message = ErrorCodes.INVOICE_YEAR_REQUIRED)
    private Integer finYear;

    @Required(message = ErrorCodes.INVOICE_CURRENCY_REQUIRED)
    private String currency = SupportConstants.CURRENCY_IND;

    @Required(message = ErrorCodes.INVOICE_LINE_BASIC_AMOUNT_REQUIRED)
    private BigDecimal basicAmount;

    @Required(message = ErrorCodes.INVOICE_LINE_TAX_AMOUNT_REQUIRED)
    private BigDecimal taxAmount;

    @Required(message = ErrorCodes.INVOICE_LINE_TOTAL_AMOUNT_REQUIRED)
    @Min(message = ErrorCodes.INVOICE_AMOUNT_MIN_VALUE, value = 0)
    private BigDecimal totalAmount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoiceLine", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InvoiceLineTax> lineTaxes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRestaurantGuid() {
        return restaurantGuid;
    }

    public void setRestaurantGuid(String restaurantGuid) {
        this.restaurantGuid = restaurantGuid;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Date getOrderDate() {
        return orderDate == null ? null : (Date) orderDate.clone();
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate == null ? null : (Date) orderDate.clone();
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

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public BigDecimal getItemPlanPrice() {
        return itemPlanPrice;
    }

    public void setItemPlanPrice(BigDecimal itemPlanPrice) {
        this.itemPlanPrice = itemPlanPrice;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<InvoiceLineTax> getLineTaxes() {
        return lineTaxes;
    }

    public void setLineTaxes(List<InvoiceLineTax> totalTax) {
        this.lineTaxes = totalTax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
