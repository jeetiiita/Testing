package com.clicktable.support.service.impl;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import play.Logger;

import com.clicktable.support.dao.intf.PaymentDao;
import com.clicktable.support.model.Invoice;
import com.clicktable.support.model.InvoiceBillingInfo;
import com.clicktable.support.model.InvoiceLine;
import com.clicktable.support.model.InvoiceLineTax;
import com.clicktable.support.model.Payment;
import com.clicktable.support.model.TaxLayer;
import com.clicktable.support.service.intf.InvoicePdfService;
import com.clicktable.support.util.SupportConstants;
import com.clicktable.util.UtilityMethods;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Component
public class InvoicePdfServiceImpl implements InvoicePdfService {

    private static final Logger.ALogger log = Logger
            .of(InvoicePdfServiceImpl.class);

    @Autowired
    PaymentDao paymentDao;

    com.itextpdf.text.Font contentRegular = getFont(9, false);
    com.itextpdf.text.Font contentBold = getFont(9, true);

    @Override
    public void generateInvoicePdf(Invoice invoice) {

        String restaurantGuid = invoice.getRestaurantGuid();

        if (SupportConstants.PAID.equals(invoice.getStatus())) {
            Map<String, Object> validParams = new HashMap<String, Object>();
            validParams.put(SupportConstants.INVOICE_ID, invoice.getInvoiceId());
            List<Payment> payments = paymentDao.findByFields(Payment.class, validParams);
            for (Payment payment : payments) {
                if (SupportConstants.PAYMENT_SUCCESS.equalsIgnoreCase(payment.getOrderStatus())) {
                    invoice.setPaymentDate(payment.getUpdatedDate());
                    invoice.setPaymentMode(payment.getPaymentMode());
                }
            }
        }

        FileOutputStream fileOutputStream = null;
        PdfReader reader = null;
        PdfStamper stamper = null;

        try {
            String path = SupportConstants.PATH;
            String year = invoice.getFinYear().toString();
            String month = new DateFormatSymbols().getMonths()[invoice.getFinMonth() - 1];

            fileOutputStream = new FileOutputStream(path + "//" + SupportConstants.INVOICE_FOLDER + "//" + year + "-" + month + "-" + restaurantGuid + ".pdf");
            reader = new PdfReader(SupportConstants.PDF_TEMPLATE_PATH);
            stamper = new PdfStamper(reader, fileOutputStream);

            populateInvoiceAndRestInfo(invoice, stamper);

            PdfPTable expenseTable = generateExpenseTable(invoice.getInvoiceLines());
            PdfPTable taxTable = generateTaxTable(invoice);
            PdfPTable payableAmountTable = generateAmountTable(invoice);

            ColumnText column = new ColumnText(stamper.getOverContent(1));
            Rectangle rectPage1 = new Rectangle(36, 36, 559, 553);
            column.setSimpleColumn(rectPage1);
            column.addElement(expenseTable);
            column.addElement(taxTable);
            column.addElement(payableAmountTable);

            int pagecount = 1;
            Rectangle rectPage2 = new Rectangle(36, 36, 559, 806);
            int statusInt = column.go();
            Rectangle pagesize = PageSize.A4;
            while (ColumnText.hasMoreText(statusInt)) {
                statusInt = triggerNewPage(stamper, pagesize, column, rectPage2, ++pagecount);
            }

        } catch (FileNotFoundException e) {
            log.error("Invoice PDF file not found for restaurant " + restaurantGuid, e);
        } catch (DocumentException e) {
            log.error("Exception in creating new page in PDF file for restaurant " + restaurantGuid, e);
        } catch (IOException e) {
            log.error("Exception in reading a template file for restaurant " + restaurantGuid, e);
        } finally {
            try {
                if (stamper != null)
                    stamper.close();
                if (reader != null)
                    reader.close();
            } catch (DocumentException | IOException e) {
                log.error("Exception in closing reader or stamper resource object for restaurant " + restaurantGuid, e);
            }
        }
    }

    private PdfPTable generateAmountTable(Invoice invoice) throws DocumentException {

        PdfPTable payableAmountTable = new PdfPTable(3);

        float payableAmountWidth[] = {6.5f, 2.15f, 1.35f};
        payableAmountTable.setWidths(payableAmountWidth);
        payableAmountTable.setWidthPercentage(102);

        PdfPCell payableAmountCell = new PdfPCell(new Paragraph(SupportConstants.AMNT_IN_WRDS, contentRegular));
        payableAmountCell.setBorder(Rectangle.LEFT);
        payableAmountCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        payableAmountCell.setFixedHeight(15);
        payableAmountCell.setPaddingLeft(12f);
        payableAmountCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        payableAmountTable.addCell(payableAmountCell);

        payableAmountCell = new PdfPCell(new Paragraph(""));
        payableAmountCell.setBorder(Rectangle.NO_BORDER);
        payableAmountTable.addCell(payableAmountCell);

        payableAmountCell = new PdfPCell(new Paragraph(""));
        payableAmountCell.setBorder(Rectangle.RIGHT);
        payableAmountTable.addCell(payableAmountCell);

        Integer total = invoice.getNetAmount();
        payableAmountCell = new PdfPCell(new Paragraph(convertNumberToWords(total) + SupportConstants.RPS_ONLY, contentBold));
        payableAmountCell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
        payableAmountCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        payableAmountCell.setPaddingLeft(10f);
        payableAmountCell.setFixedHeight(20);
        payableAmountTable.addCell(payableAmountCell);

        payableAmountCell = new PdfPCell(new Paragraph(SupportConstants.AMNT_TTL, contentBold));
        payableAmountCell.setBorder(Rectangle.BOTTOM);
        payableAmountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        payableAmountCell.setVerticalAlignment(Element.ALIGN_TOP);
        payableAmountTable.addCell(payableAmountCell);

        payableAmountCell = new PdfPCell(new Paragraph(SupportConstants.INR_SYMBOL + total.toString(), contentBold));
        payableAmountCell.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
        payableAmountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        payableAmountCell.setVerticalAlignment(Element.ALIGN_TOP);
        payableAmountCell.setPaddingRight(15f);
        payableAmountTable.addCell(payableAmountCell);

        PdfPCell payableAmountCell2 = new PdfPCell();
        payableAmountCell2.addElement(payableAmountTable);
        return payableAmountTable;
    }

    private PdfPTable generateTaxTable(Invoice invoice) throws DocumentException {

        BigDecimal subTotal;
        BigDecimal amount;
        int type = 3;
        BigDecimal taxAmount;
        String taxType = null;
        int[] vAlignAmount = {Element.ALIGN_CENTER};
        int[] hAlignAmount = {Element.ALIGN_CENTER, Element.ALIGN_LEFT, Element.ALIGN_CENTER, Element.ALIGN_RIGHT};
        PdfPTable taxTable = new PdfPTable(4);
        float[] amountTableWidth = {0.8f, 5.6f, 2.25f, 1.35f};
        taxTable.setWidthPercentage(102);
        taxTable.setWidths(amountTableWidth);
        subTotal = invoice.getBasicAmount();
        String[][] subTotalRow = {{"", SupportConstants.SUB_TOTAL, "", SupportConstants.INR_SYMBOL + subTotal.toString()}};
        generateTableRows(taxTable, subTotalRow, 1, 4, contentRegular, vAlignAmount, hAlignAmount, type, 14);
        type = 2;

        Map<String, BigDecimal> taxMap = new HashMap<String, BigDecimal>();
        List<InvoiceLine> invoceLines = invoice.getInvoiceLines();
        for (InvoiceLine invoiceLine : invoceLines) {
            List<InvoiceLineTax> invoiceLineTaxList = invoiceLine.getLineTaxes();
            for (InvoiceLineTax invoiceLineTax : invoiceLineTaxList) {
                taxAmount = invoiceLineTax.getAmount();
                TaxLayer layer = invoiceLineTax.getTaxLayer();
                if (layer != null) {
                    taxType = layer.getSingleTax().getDisplayName();
                    if (taxMap.containsKey(taxType)) {
                        taxMap.put(taxType, taxMap.get(taxType).add(taxAmount));
                    } else {
                        taxMap.put(taxType, taxAmount);
                    }
                }

            }
        }

        for (String tax : taxMap.keySet()) {
            amount = taxMap.get(tax);
            if (amount != null) {
                String[][] taxData = {{"", tax, "", SupportConstants.INR_SYMBOL + amount.toString()}};
                generateTableRows(taxTable, taxData, 1, 4, contentRegular, vAlignAmount, hAlignAmount, type, 14);
            }
        }
        String[][] roundOff = {{"", SupportConstants.RND_OFF, "", SupportConstants.INR_SYMBOL + invoice.getRoundOffAmount().toString()}};
        generateTableRows(taxTable, roundOff, 1, 4, contentRegular, vAlignAmount, hAlignAmount, 4, 14);
        taxTable.getDefaultCell().setPaddingLeft(5);
        return taxTable;
    }

    private void populateInvoiceAndRestInfo(Invoice invoice, PdfStamper stamper) throws IOException, DocumentException {

        //filling the information into template
        AcroFields form = stamper.getAcroFields();
        InvoiceBillingInfo billingInfo = invoice.getBillingInfo();

        String restAccountId = billingInfo.getAccountId();
        String restName = billingInfo.getLegalName();
        String restAddressLine1 = billingInfo.getAddress1();
        String restAddressLine2 = billingInfo.getAddress2();
        String restCity = billingInfo.getCity();
        String restState = billingInfo.getState();
        String restPin = new Integer(billingInfo.getZipCode()).toString();
        String restPhoneNo = billingInfo.getPhone();
        String restEmail = billingInfo.getEmail();

        String invoiceNo = invoice.getInvoiceId().toString();
        String bookNo = invoice.getBookNumber().toString();
        String date = dateToString(invoice.getInvoiceDate());
        String dueDate = dateToString(invoice.getDueDate());
        String status = matchStatus(invoice.getStatus());
        String paymentDate = dateToString(invoice.getPaymentDate());
        String paymentMode = invoice.getPaymentMode();
        if (invoice.getPaymentDate() != null) {
            Date paymentDateLong = invoice.getPaymentDate();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            paymentDate = dateFormat.format(paymentDateLong);
        }


        if (!restPhoneNo.contains("+91"))
            restPhoneNo = "+91" + restPhoneNo;

        form.setField("ctAddress", SupportConstants.CT_ADDRESS);
        form.setField("ctPin", SupportConstants.CT_PIN);
        form.setField("ctPhone", SupportConstants.CT_PHONE);
        form.setField("ctEmail", SupportConstants.CT_EMAIL);
        form.setField("ctWebsite", SupportConstants.CT_WEBSITE);
        form.setField("ctServiceTax", SupportConstants.CT_SERVICE_TAX);
        form.setField("ctVat", SupportConstants.CT_VAT);

        form.setField("invoiceNo", invoiceNo);
        form.setField("bookNo", bookNo);
        form.setField("date", date);
        form.setField("dueDate", dueDate);
        form.setField("status", status);
        form.setField("paymentDate", paymentDate);
        form.setField("paymentMode", paymentMode);

        form.setField("accountId", restAccountId);
        form.setField("restName", restName);
        form.setField("restAddressOne", restAddressLine1);
        form.setField("restAddressTwo", restAddressLine2);
        form.setField("restCityState", restCity + ", " + restState);
        form.setField("restPIN", restPin);
        form.setField("restEmail", restEmail);
        form.setField("restPhone", restPhoneNo);

        form.setGenerateAppearances(true);
        stamper.setFormFlattening(true);
    }

    private com.itextpdf.text.Font getFont(int size, boolean isBold) {
        BaseFont bfbold = null;
        try {
            bfbold = BaseFont.createFont(SupportConstants.PDF_FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            log.error("Exception in font creation", e);
        }
        if (isBold)
            return new com.itextpdf.text.Font(bfbold, size, Font.BOLD);
        return new com.itextpdf.text.Font(bfbold, size, Font.PLAIN);
    }

    private PdfPTable generateExpenseTable(List<InvoiceLine> invoiceLineList) throws DocumentException {

        String line, unitPrice, quantity, amount, type, description, tax;
        Integer count = 1, totalColumn = 6;
        int[] vAlignExpense = {Element.ALIGN_CENTER};
        int[] hAlignExpenseHeader = {Element.ALIGN_CENTER, Element.ALIGN_CENTER, Element.ALIGN_LEFT, Element.ALIGN_CENTER, Element.ALIGN_CENTER, Element.ALIGN_RIGHT};
        int[] hAlignExpense = {Element.ALIGN_CENTER, Element.ALIGN_CENTER, Element.ALIGN_LEFT, Element.ALIGN_CENTER, Element.ALIGN_CENTER, Element.ALIGN_RIGHT};

        PdfPTable expeseTable = new PdfPTable(totalColumn);
        float[] expeseTableWidth = {0.8f, 1.8f, 3.8f, 1f, 1.25f, 1.35f};

        expeseTable.setWidths(expeseTableWidth);
        expeseTable.setWidthPercentage(102);

        String[][] header = {UtilityMethods.getEnumValues(SupportConstants.INVOICE, SupportConstants.INVOICE_HEADERS).stream().toArray(String[]::new)};
        generateTableRows(expeseTable, header, 1, totalColumn, contentBold, vAlignExpense, hAlignExpenseHeader, 1, 19);

        for (InvoiceLine invoiceLine : invoiceLineList) {
            line = invoiceLine.getLineNumber().toString();
            unitPrice = SupportConstants.INR_SYMBOL + invoiceLine.getItemPlanPrice().toString();
            quantity = invoiceLine.getItemQty().toString();
            amount = SupportConstants.INR_SYMBOL + invoiceLine.getBasicAmount().toString();
            tax = invoiceLine.getTaxAmount().toString();
            type = invoiceLine.getItem().getDisplayName();
            description = invoiceLine.getItem().getDescription();
            String[][] detail = {{line, type, description, quantity, unitPrice, amount, tax}};
            generateTableRows(expeseTable, detail, 1, totalColumn, contentRegular, vAlignExpense, hAlignExpense, 2, 18);
            count++;
        }
        return expeseTable;
    }

    private void generateTableRows(PdfPTable pdfPTable, String[][] data, int row, int col, com.itextpdf.text.Font font, int[] verticalAlign, int[] horizontalAlign, int type, float height) {
        int i, j;
        PdfPCell pdfPCell = new PdfPCell();

        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {

                pdfPCell = new PdfPCell(new Paragraph(data[i][j], font));

                pdfPCell.setVerticalAlignment(verticalAlign[i]);
                pdfPCell.setHorizontalAlignment(horizontalAlign[j]);
                pdfPCell.setMinimumHeight(height);

                if (horizontalAlign[j] == Element.ALIGN_RIGHT) {
                    pdfPCell.setPaddingRight(10f);
                }
                if (horizontalAlign[j] == Element.ALIGN_LEFT) {
                    pdfPCell.setPaddingLeft(6f);
                }
                if (type == 1) {
                    pdfPCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
                    pdfPCell.setBackgroundColor(new BaseColor(221, 221, 221));
                } else if (type == 2) {
                    pdfPCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
                } else if (type == 3) {
                    pdfPCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.TOP);
                } else {
                    pdfPCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
                }

                pdfPTable.addCell(pdfPCell);
            }
        }
    }

    private String convertNumberToWords(Integer n) {

        if (n == 0)
            return "Zero";

        String input;

        String[] maxs = {"", "", " Hundred", " Thousand", " Lakh", " Crore"};

        input = n.toString();
        String converted = "";
        int pos = 1;
        boolean hun = false;
        while (input.length() > 0) {
            if (pos == 1) // TENS AND UNIT POSITION
            {
                if (input.length() >= 2) // TWO DIGIT NUMBERS
                {
                    String temp = input.substring(input.length() - 2,
                            input.length());
                    input = input.substring(0, input.length() - 2);
                    converted += digits(temp);
                } else if (input.length() == 1) // 1 DIGIT NUMBER
                {
                    converted += digits(input);
                    input = "";
                }
                pos++;
            } else if (pos == 2) // HUNDRED POSITION
            {
                String temp = input.substring(input.length() - 1,
                        input.length());
                input = input.substring(0, input.length() - 1);
                if (converted.length() > 0 && digits(temp) != "") {
                    converted = (digits(temp) + maxs[pos] + " and") + converted;
                    hun = true;
                } else {
                    if (digits(temp) == "")
                        ;
                    else
                        converted = (digits(temp) + maxs[pos]) + converted;
                    hun = true;
                }
                pos++;
            } else if (pos > 2) // REMAINING NUMBERS PAIRED BY TWO
            {
                if (input.length() >= 2) // EXTRACT 2 DIGITS
                {
                    String temp = input.substring(input.length() - 2,
                            input.length());
                    input = input.substring(0, input.length() - 2);
                    if (!hun && converted.length() > 0)
                        converted = digits(temp) + maxs[pos] + " and"
                                + converted;
                    else {
                        if (digits(temp) == "")
                            ;
                        else
                            converted = digits(temp) + maxs[pos] + converted;
                    }
                } else if (input.length() == 1) // EXTRACT 1 DIGIT
                {
                    if (!hun && converted.length() > 0)
                        converted = digits(input) + maxs[pos] + " and"
                                + converted;
                    else {
                        if (digits(input) == "")
                            ;
                        else
                            converted = digits(input) + maxs[pos] + converted;
                        input = "";
                    }
                }
                pos++;
            }
        }
        return converted;
    }

    private String digits(String temp) {
        String[] units = {"", " One", " Two", " Three", " Four",
                " Five", " Six", " Seven", " Eight", " Nine"};
        String[] teen = {" Ten", " Eleven", " Twelve", " Thirteen",
                " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen",
                " Nineteen"};
        String[] tens = {" Twenty", " Thirty", " Forty", " Fifty",
                " Sixty", " Seventy", " Eighty", " Ninety"};

        String converted = "";
        for (int i = temp.length() - 1; i >= 0; i--) {
            int ch = temp.charAt(i) - 48;
            if (i == 0 && ch > 1 && temp.length() > 1)
                converted = tens[ch - 2] + converted;
            else if (i == 0 && ch == 1 && temp.length() == 2) {
                int sum = 0;
                for (int j = 0; j < 2; j++)
                    sum = (sum * 10) + (temp.charAt(j) - 48);
                return teen[sum - 10];
            } else {
                if (ch > 0)
                    converted = units[ch] + converted;
            }
        }
        return converted;
    }

	

    private String dateToString(Date date) {
        String reportDate = "";
        if (date != null) {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            reportDate = df.format(date);
        }
        return reportDate;
    }

    private String matchStatus(String statuDB) {
        if (statuDB == "PAID")
            return "Paid";
        return "Unpaid";
    }

    private int triggerNewPage(PdfStamper stamper, Rectangle pagesize, ColumnText column, Rectangle rect, int pagecount) throws DocumentException {
        stamper.insertPage(pagecount, pagesize);
        PdfContentByte canvas = stamper.getOverContent(pagecount);
        column.setCanvas(canvas);
        column.setSimpleColumn(rect);
        return column.go();
    }
}

