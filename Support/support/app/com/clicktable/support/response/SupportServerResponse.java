package com.clicktable.support.response;

import java.util.List;

import com.clicktable.response.BaseResponse;
import com.clicktable.validate.ValidationError;

import play.i18n.Messages;

public class SupportServerResponse extends BaseResponse {

    List<String> list;

    List<ValidationError> errorList;

    public SupportServerResponse(String responseCode, List<ValidationError> errorList, List<String> list) {
        this.setResponseStatus(false);
        this.setResponseCode(responseCode);
        this.setResponseMessage(Messages.get(responseCode));
        this.setErrorList(errorList);
        this.setList(list);
    }

    public List<ValidationError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ValidationError> errorList) {
        this.errorList = errorList;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

}
