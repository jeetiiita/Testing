/**
 *
 */
package com.clicktable.support.exception;

import java.util.List;

import com.clicktable.validate.ValidationError;

/**
 * @author j.yadav
 */
public class ServiceValidationException extends RuntimeException {

    List<ValidationError> errorList;

    List<String> list;

    public ServiceValidationException() {
        super();
    }

    public ServiceValidationException(String message) {
        super(message);
    }

    public ServiceValidationException(String message,
                                      List<ValidationError> errorList) {
        super(message);
        this.setErrorList(errorList);
    }


    public ServiceValidationException(String message,
                                      List<ValidationError> errorList, List<String> list) {
        super(message);
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