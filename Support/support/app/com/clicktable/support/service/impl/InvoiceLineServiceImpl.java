/**
 *
 */
package com.clicktable.support.service.impl;

import java.math.BigDecimal;
import java.util.*;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.clicktable.model.Restaurant;
import com.clicktable.support.dao.intf.InvoiceLineDao;
import com.clicktable.support.dao.intf.ItemDao;
import com.clicktable.support.exception.ServiceValidationException;
import com.clicktable.support.model.AggregateTax;
import com.clicktable.support.model.InvoiceLine;
import com.clicktable.support.model.InvoiceLineTax;
import com.clicktable.support.model.PlanItem;
import com.clicktable.support.model.TaxLayer;
import com.clicktable.support.service.intf.InvoiceLineService;

/**
 * @author j.yadav
 */

public abstract class InvoiceLineServiceImpl implements InvoiceLineService {

    @Autowired
    private InvoiceLineDao invoiceLineDao;

    @Autowired
    private ItemDao itemDao;

    @Override
    public InvoiceLine create(InvoiceLine invoiceLine) {
        return invoiceLineDao.create(invoiceLine);
    }

    @Override
    public InvoiceLine generateInvoiceLine(Restaurant restaurant, PlanItem planItem, Integer lineNumber, String token,Date planStartDate) {

        InvoiceLine invoiceLine = new InvoiceLine();

        invoiceLine.setItem(planItem.getItem());
        invoiceLine.setLineNumber(lineNumber);
        invoiceLine.setRestaurantGuid(restaurant.getGuid());

        invoiceLine.setCreatedBy(token);
        invoiceLine.setUpdatedBy(token);

        // set Item Quantity and set promotional summary..
        int itemQuantity = calculateItemCount(restaurant,planStartDate);

        invoiceLine.setItemQty(planItem.getMinItemQuantity());
        if (itemQuantity > planItem.getMinItemQuantity()) {
            invoiceLine.setItemQty(itemQuantity);
        }

        // set Item Price
        BigDecimal itemPrice = planItem.getItemUnitPrice();
        invoiceLine.setItemPlanPrice(itemPrice);

        setInvoiceLineDates(invoiceLine);

        // set Basic Amount
        invoiceLine.setBasicAmount(itemPrice.multiply(BigDecimal.valueOf(invoiceLine.getItemQty())));

        // set Taxes
        calculateTaxes(invoiceLine, restaurant, token);
        return invoiceLine;
    }

    private void setInvoiceLineDates(InvoiceLine invoiceLine) {
        invoiceLine.setFinMonth(DateTime.now().minusMonths(1).getMonthOfYear());
        invoiceLine.setFinYear(DateTime.now().minusMonths(1).getYear());
        invoiceLine.setOrderDate(DateTime.now().minusDays(1).withTime(0, 0, 0, 0).toDate());
    }

    private void calculateTaxes(InvoiceLine invoiceLine, Restaurant restaurant, String token) {

        List<InvoiceLineTax> invoiceLineTaxes = new ArrayList<InvoiceLineTax>();

        InvoiceLineTax layer0Tax = new InvoiceLineTax();
        layer0Tax.setAmount(invoiceLine.getBasicAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        layer0Tax.setTaxRate(BigDecimal.valueOf(100));
        layer0Tax.setInvoiceLine(invoiceLine);

        layer0Tax.setCreatedBy(token);
        layer0Tax.setUpdatedBy(token);

        invoiceLineTaxes.add(layer0Tax);

        List<AggregateTax> list;

        list = itemDao.getAggregateTaxOnItem(invoiceLine.getItem(), restaurant);

        if (list.isEmpty()) {
            list = itemDao.getAggregateTaxByCountryForItem(invoiceLine.getItem(), restaurant);

        }
        Float taxAmount = 0f;
        // Issue while getting tax info for given item of restaurant. No tax
        // calculated
        if (!list.isEmpty()) {

            Set<TaxLayer> tax = list.get(0).getTaxLayers();
            Set<TaxLayer> taxLayers = new TreeSet<TaxLayer>();
            taxLayers.addAll(tax);

            int layerNo = 1;

            for (TaxLayer taxLayer : taxLayers) {
                if (taxLayer.getLayerNumber().equals(layerNo)) {
                    taxAmount += applyTax(taxLayer, invoiceLine, invoiceLineTaxes, token);
                } else {
                    invoiceLineTaxes.add(null);
                }
                layerNo++;
            }
            invoiceLine.setLineTaxes(invoiceLineTaxes);
            invoiceLine.setTaxAmount(BigDecimal.valueOf(taxAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN));
            invoiceLine.setTotalAmount(invoiceLine.getBasicAmount().add(invoiceLine.getTaxAmount()));
        } else {
            throw new ServiceValidationException(
                    "Exception in AggregateTax Fetching");

        }

    }

    private float applyTax(TaxLayer taxLayer, InvoiceLine invoiceLine, List<InvoiceLineTax> invoiceLineTaxes,
                           String token) {
        float amount = 0f;
        for (int i = 0; i < taxLayer.getApplicableLayers().size(); i++) {
            Integer applicableLayer = taxLayer.getApplicableLayers().get(i);
            BigDecimal invoiceLineAmount = invoiceLineTaxes.get(applicableLayer).getAmount();
            BigDecimal singleTaxRate = taxLayer.getSingleTax().getRate();

            amount += ((invoiceLineAmount.multiply(singleTaxRate)).divide(BigDecimal.valueOf(100))).floatValue();

        }

        InvoiceLineTax layerTax = new InvoiceLineTax();
        layerTax.setCreatedBy(token);
        layerTax.setUpdatedBy(token);
        layerTax.setAmount(BigDecimal.valueOf(amount).setScale(2, BigDecimal.ROUND_HALF_DOWN));
        layerTax.setTaxRate(taxLayer.getSingleTax().getRate());
        layerTax.setTaxLayer(taxLayer);
        layerTax.setInvoiceLine(invoiceLine);
        invoiceLineTaxes.add(layerTax);
        return amount;

    }

    abstract int calculateItemCount(Restaurant restaurant,Date planStartdate);

}
