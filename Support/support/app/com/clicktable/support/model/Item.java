package com.clicktable.support.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import play.data.validation.Constraints.Required;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class Item extends BaseEntity {

    private static final long serialVersionUID = 8407806599912860689L;
    @Id
    @GeneratedValue
    private Short id;
    @Required(message = ErrorCodes.ITEM_TYPE_REQUIRED)
    private String type;
    @Required(message = ErrorCodes.ITEM_NAME_REQUIRED)
    private String name;
    @Required(message = ErrorCodes.DISPLAY_NAME_REQUIRED)
    private String displayName;
    @Required(message = ErrorCodes.ITEM_DESCRIPTION_REQUIRED)
    private String description;
    @Required(message = ErrorCodes.ITEM_DEFAULT_PRICE_REQUIRED)
    private BigDecimal defaultUnitPrice;
    @Required(message = ErrorCodes.ITEM_STATUS_REQUIRED)
    private String status = SupportConstants.ACTIVE;
    @Required(message = ErrorCodes.AGGREGATE_TAX_ID_REQUIRED)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ItemAggregateTax", joinColumns = {@JoinColumn(name = "itemId")}, inverseJoinColumns = {
            @JoinColumn(name = "aggregateTaxId")})
    private Set<AggregateTax> aggregateTaxes;

    @Override
    public String toString() {
        return "id=" + id + " type=" + type + " name=" + name + " displayName=" + displayName + " status=" + status;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDefaultUnitPrice() {
        return defaultUnitPrice;
    }

    public void setDefaultUnitPrice(BigDecimal defaultUnitPrice) {
        this.defaultUnitPrice = defaultUnitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AggregateTax> getAggregateTaxes() {
        return aggregateTaxes;
    }

    public void setAggregateTaxes(Set<AggregateTax> aggregateTaxes) {
        this.aggregateTaxes = aggregateTaxes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
