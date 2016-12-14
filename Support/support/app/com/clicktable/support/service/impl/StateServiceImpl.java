package com.clicktable.support.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clicktable.support.dao.intf.StateDao;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.State;
import com.clicktable.support.service.intf.StateService;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.ValidationError;

@Component
public class StateServiceImpl implements StateService {


    @Autowired
    private StateDao stateDao;


    @Override
    public State addState(State state) {
        State stateEntity = stateDao.create(state);
        return stateEntity;
    }

    @Override
    public List<State> getState(Map<String, Object> params) {
        List<State> stateList = stateDao.findByFields(State.class, params);
        return stateList;
    }


    @Override
    public Boolean disable(Integer id) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();
        State state = stateDao.find(id);

        if (state == null) {
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.ID, UtilityMethods.getErrorMsg(ErrorCodes.ID_DOES_NOT_EXIST), ErrorCodes.ID_DOES_NOT_EXIST);

            throw new ServiceValidationException("Exception in StateService disable Function", errorList);
        }
        if (state.getStatus().equals(SupportConstants.INACTIVE_STATUS)) {
            errorList = CustomValidations.populateErrorList(errorList, SupportConstants.STATUS, UtilityMethods.getErrorMsg(ErrorCodes.STATUS_ALREADY_INACTIVE), ErrorCodes.STATUS_ALREADY_INACTIVE);

            throw new ServiceValidationException("Exception in StateService disable Function", errorList);
        }
        state.setStatus(SupportConstants.INACTIVE_STATUS);
        stateDao.update(state);
        return true;
    }

}
