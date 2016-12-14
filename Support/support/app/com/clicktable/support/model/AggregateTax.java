package com.clicktable.support.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.clicktable.support.util.ErrorCodes;
import com.clicktable.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import play.data.validation.Constraints.Required;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class AggregateTax extends Tax {

    private static final long serialVersionUID = 7735237042519869675L;
    @Required(message = ErrorCodes.TAX_LAYERS_INFO_REQUIRED)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "aggregateTaxId")
    Set<TaxLayer> taxLayers;
    @Id
    @GeneratedValue
    private Integer id;
    @Required(message = ErrorCodes.START_DATE_REQUIRED)
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = Constants.TIMEZONE)
    private Date startDate;
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = Constants.TIMEZONE)
    private Date endDate;

    @Override
    public String toString() {
        return "id=" + id + " name=" + name + " description=" + description + " status=" + status + " state=" + state
                + " country=" + country + " startDate=" + startDate + " endDate" + endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<TaxLayer> getTaxLayers() {
        return taxLayers;
    }

    public void setTaxLayers(Set<TaxLayer> taxLayers) {
        this.taxLayers = taxLayers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    public Date getEndDate() {
        return endDate == null ? null : (Date) endDate.clone();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate == null ? null : (Date) endDate.clone();
    }

}
