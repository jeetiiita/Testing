/**
 *
 */
package com.clicktable.support.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.clicktable.support.util.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

/**
 * @author j.yadav
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class SingleTax extends Tax {

    private static final long serialVersionUID = -6734159618086006278L;

    @Id
    @GeneratedValue
    private Integer id;

    @Required(message = ErrorCodes.DISPLAY_NAME_REQUIRED)
    private String displayName;

    @Required(message = ErrorCodes.TAX_RATE_REQUIRED)
    @Max(message = ErrorCodes.TAX_RATE_MAX_VALUE, value = 100)
    @Min(message = ErrorCodes.TAX_RATE_MIN_VALUE, value = 0)
    private BigDecimal rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
