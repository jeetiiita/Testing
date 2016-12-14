package com.clicktable.support.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author j.yadav
 */

@Entity
public class InvoiceLineTax extends BaseEntity {

    private static final long serialVersionUID = -2884088203967324384L;

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "invoiceLineId")
    private InvoiceLine invoiceLine;
    @ManyToOne
    @JoinColumn(name = "taxLayerId")
    private TaxLayer taxLayer;
    private BigDecimal taxRate;
    private BigDecimal amount;

    public InvoiceLineTax() {
        super();
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public InvoiceLine getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(InvoiceLine invoiceLine) {
        this.invoiceLine = invoiceLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaxLayer getTaxLayer() {
        return taxLayer;
    }

    public void setTaxLayer(TaxLayer taxLayer) {
        this.taxLayer = taxLayer;
    }


}
