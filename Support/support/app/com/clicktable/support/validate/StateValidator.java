package com.clicktable.support.validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clicktable.support.dao.intf.StateDao;
import com.clicktable.support.model.State;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.CustomValidations;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

@Service
public class StateValidator extends EntityValidator<State> {

    @Autowired
    StateDao stateDao;

    public List<ValidationError> validateStateOnAdd(State state) {
        List<ValidationError> errorList = validateOnAdd(state);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", SupportConstants.ACTIVE);
        params.put("stateCode", state.getStateCode());

        List<State> stateList = stateDao.findByFields(State.class, params);

        if (!stateList.isEmpty())
            errorList = CustomValidations.populateErrorList(errorList,
                    SupportConstants.STATECODE, UtilityMethods
                            .getErrorMsg(ErrorCodes.STATE_CODE_ALREADY_EXIST),
                    ErrorCodes.STATE_CODE_ALREADY_EXIST);
        return errorList;


    }


}
