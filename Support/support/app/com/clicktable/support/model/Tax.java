package com.clicktable.support.model;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import play.data.validation.Constraints.Required;

import com.clicktable.support.util.SupportConstants;
import com.clicktable.support.util.ErrorCodes;

/**
 * @author j.yadav
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Tax extends BaseEntity {

    private static final long serialVersionUID = -3907778808644505325L;

    @Required(message = ErrorCodes.TAX_NAME_REQUIRED)
    protected String name;

    @Required(message = ErrorCodes.TAX_DESCRIPTION_REQUIRED)
    protected String description;

    protected String status = SupportConstants.ACTIVE;

    protected String state;

    @Required(message = ErrorCodes.TAX_COUNTRY_REQUIRED)
    protected String country;

    @Required(message = ErrorCodes.CENTRAL_TAX_REQUIRED)
    protected Boolean centralTax;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getCentralTax() {
        return centralTax;
    }

    public void setCentralTax(Boolean centralTax) {
        this.centralTax = centralTax;
    }

}