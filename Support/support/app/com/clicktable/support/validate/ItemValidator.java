package com.clicktable.support.validate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clicktable.support.dao.intf.AggregateTaxDao;
import com.clicktable.support.dao.intf.ItemDao;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.Item;
import com.clicktable.support.util.ErrorCodes;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;
import com.clicktable.validate.EntityValidator;
import com.clicktable.validate.ValidationError;

@Service
public class ItemValidator extends EntityValidator<Item> {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private AggregateTaxDao aggregateTaxDao;

    public List<ValidationError> validateItemOnAdd(Item item) {

        List<ValidationError> errorList = validateOnAdd(item);

        if (errorList.isEmpty()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(SupportConstants.STATUS, SupportConstants.ACTIVE);
            params.put(SupportConstants.NAME, item.getName());

            List<Item> itemList = itemDao.findByFields(Item.class, params);
            if (!itemList.isEmpty()) {
                errorList.add(new ValidationError(SupportConstants.NAME,
                        UtilityMethods.getErrorMsg(ErrorCodes.ITEM_WITH_NAME_EXISTS),
                        ErrorCodes.ITEM_WITH_NAME_EXISTS));
            }

            Set<String> duplicateStateEntity = new HashSet<String>();

            for (AggregateTax aggregateTax : item.getAggregateTaxes()) {

                AggregateTax tax = aggregateTaxDao.find(aggregateTax.getId());
                boolean isExist;

                if (tax == null) {
                    errorList.add(new ValidationError("aggregateTaxes",
                            UtilityMethods.getErrorMsg(ErrorCodes.ID_DOES_NOT_EXIST), ErrorCodes.ID_DOES_NOT_EXIST));
                    break;
                } else {
                    isExist = duplicateStateEntity.add(tax.getState());
                    if (isExist == false) {
                        errorList.add(new ValidationError(SupportConstants.STATE,
                                UtilityMethods.getErrorMsg(ErrorCodes.AGGREGATE_TAX_OF_SAME_STATE_NOT_ALLOWED),
                                ErrorCodes.AGGREGATE_TAX_OF_SAME_STATE_NOT_ALLOWED));
                        break;
                    }
                }

            }
        }

        return errorList;
    }

    public List<ValidationError> validateItemOnUpdate(Item item) {

        List<ValidationError> errorList = validateOnAdd(item);

        Item itemEntity = itemDao.find(item.getId());

        if (itemEntity != null) {

            Set<AggregateTax> existingAggregateTaxes = itemEntity.getAggregateTaxes();
            Set<AggregateTax> updatingAggregateTax = item.getAggregateTaxes();
            Set<AggregateTax> copySet = new HashSet<AggregateTax>();
            copySet.addAll(updatingAggregateTax);

            for (AggregateTax updateTax : updatingAggregateTax) {
                for (AggregateTax tax : existingAggregateTaxes) {
                    AggregateTax taxEntity = aggregateTaxDao.find(updateTax.getId());
                    if (tax.getId().equals(taxEntity.getId()))
                        copySet.remove(updateTax);
                }
            }

            for (AggregateTax aggregateTax : copySet) {
                for (AggregateTax tax : existingAggregateTaxes) {
                    AggregateTax taxEntity = aggregateTaxDao.find(aggregateTax.getId());
                    if (tax.getCentralTax() == false && tax.getState().equals(taxEntity.getState())
                            && taxEntity.getStatus().equals(SupportConstants.ACTIVE)) {

                        errorList.add(new ValidationError(SupportConstants.STATE,
                                UtilityMethods.getErrorMsg(ErrorCodes.AGGREGATE_TAX_OF_SAME_STATE_NOT_ALLOWED),
                                ErrorCodes.AGGREGATE_TAX_OF_SAME_STATE_NOT_ALLOWED));
                        break;
                    }
                }
            }
        } else {
            errorList.add(new ValidationError("item", UtilityMethods.getErrorMsg(ErrorCodes.ID_DOES_NOT_EXIST),
                    ErrorCodes.ID_DOES_NOT_EXIST));
        }

        return errorList;

    }

}