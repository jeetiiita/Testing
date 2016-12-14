package com.clicktable.support.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;

import com.clicktable.support.util.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Entity
public class Plan extends BaseEntity {

    private static final long serialVersionUID = 1849757765071392773L;

    @Id
    @GeneratedValue
    private Integer id;

    @Required(message = ErrorCodes.PLAN_NAME_REQUIRED)
    private String name;

    @Required(message = ErrorCodes.PLAN_DISPLAY_NAME_REQUIRED)
    private String displayName;

    @Required(message = ErrorCodes.PLAN_STATUS_REQUIRED)
    private String status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Required(message = ErrorCodes.PLAN_ITEM_REQUIRED)
    @JoinColumn(name = "planId")
    private Set<PlanItem> planItems;

    public Set<PlanItem> getPlanItems() {
        return planItems;
    }

    public void setPlanItems(Set<PlanItem> planItems) {
        this.planItems = planItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
