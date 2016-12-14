package com.clicktable.support.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

import com.clicktable.support.util.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author j.yadav
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class TaxLayer extends BaseEntity implements Comparable<TaxLayer> {

    private static final int MIN_LAYER_NUMBER = 1;
    private static final int MAX_LAYER_NUMBER = 9;

    private static final long serialVersionUID = -6843409529472873032L;
    @Required(message = ErrorCodes.SINGLE_TAX_REQUIRED)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "singleTaxId")
    SingleTax singleTax;
    @Id
    @GeneratedValue
    private Integer id;
    @Max(message = ErrorCodes.TAX_LAYER_NUMBER_MAX_VALUE, value = MAX_LAYER_NUMBER)
    @Min(message = ErrorCodes.TAX_LAYER_NUMBER_MIN_VALUE, value = MIN_LAYER_NUMBER)
    @Required(message = ErrorCodes.TAX_LAYER_NUMBER_REQUIRED)
    private Integer layerNumber;

    @Required(message = ErrorCodes.AGGREGATE_APPLY_ON_LAYER_REQUIRED)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TaxApplicableLayer", joinColumns = @JoinColumn(name = "taxLayerId"))
    @Column(name = "applicableLayer")
    private List<Integer> applicableLayers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLayerNumber() {
        return layerNumber;
    }

    public void setLayerNumber(Integer layerNumber) {
        this.layerNumber = layerNumber;
    }


    public List<Integer> getApplicableLayers() {
        return applicableLayers;
    }

    public void setApplicableLayers(List<Integer> applyOnLayer) {
        this.applicableLayers = applyOnLayer;
    }

    public SingleTax getSingleTax() {
        return singleTax;
    }

    public void setSingleTax(SingleTax singleTax) {
        this.singleTax = singleTax;
    }

    @Override
    public int compareTo(TaxLayer other) {
        return Integer.compare(this.layerNumber, other.layerNumber);
    }

}
