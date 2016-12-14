package com.clicktable.support.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;

import com.clicktable.support.util.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class PlanItem extends BaseEntity {

    private static final long serialVersionUID = -1886615171669775333L;

    @Id
    @GeneratedValue
    private Integer id;

    @Required(message = ErrorCodes.ITEM_UNIT_PRICE_REQUIRED)
    private BigDecimal itemUnitPrice;

    @Required(message = ErrorCodes.MIN_ITEM_QTY_REQUIRED)
    private Integer minItemQuantity;

    @ManyToOne
    @Required(message = ErrorCodes.ITEM_REQUIRED)
    @JoinColumn(name = "itemId")
    private Item item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(BigDecimal itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public Integer getMinItemQuantity() {
        return minItemQuantity;
    }

    public void setMinItemQuantity(Integer minItemQuantity) {
        this.minItemQuantity = minItemQuantity;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
