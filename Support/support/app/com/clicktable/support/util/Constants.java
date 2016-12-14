// $codepro.audit.disable fieldJavadoc
package com.clicktable.support.util;

public class Constants extends com.clicktable.util.Constants {

    public static final String GUID = "guid";
    // SQL Connection configuration

    public static final String JDBC_DRIVER = "sql.driver_name";
    public static final String SQL_DB_URL = "sql.db_url";
    public static final String CONVERSATION_NODE = "Conversation";
    public static final String GUEST_INFO_NODE = "GuestInfo";
    public static final String DELIVERY_LABEL = "Delivery";

    public static final String SMS_STATUS = "smsStatus";
    public static final String SMS_STATUS_CAUSE = "smsStatusCause";

    // Invoice tax ,tax category and payment
    public static final String INVOICE = "invoice";
    public static final String INVOICE_ID = "invoiceId";
    public static final String INVOICE_DUE_DATE = "invoice.dueDate";
    public static final String TAX = "tax";
    public static final String TAX_CODE = "taxCode";
    public static final String CAT_CODE = "catCode";
    public static final String INVOICE_STATE = "state";
    public static final String INVOICE_COUNTRY = "country";
    public static final String INVOICE_CURRENCY = "currency";
    public static final String TAXCATEGORY = "taxCategory";
    public static final String TAX_DATE = "date";
    public static final String TAX_STATUS = "ACTIVE";
    public static final String STATUS = "status";
    public static final String TIMEZONE = "IST";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH-mm-ss";
    public static final String INVOICEGUID = "invoiceGuid";
    public static final String TAX_NAME = "TAX_NAME";

    // Delivery
    public static final String DELIVERY_SMS_ID = "externalId";
    public static final String DELIVERY_SMS_STATUS = "status";
    public static final String DELIVERY_SMS_STATUS_CAUSE = "cause";
    public static final String FAIL = "FAIL";

    // delivery message fail causes
    public static final String ABSENT_SUBSCRIBER = "ABSENT_SUBSCRIBER";
    public static final String UNKNOWN_SUBSCRIBER = "UNKNOWN_SUBSCRIBER";
    public static final String SYSTEM_FAILURE = "SYSTEM_FAILURE";
    public static final String CALL_BARRED = "CALL_BARRED";
    public static final String SERVICE_DOWN = "SERVICE_DOWN";
    public static final String OTHER = "OTHER";
    public static final String DND = "DND";
    public static final String DND_FAIL = "DND_FAIL";
    public static final String DND_TIMEOUT = "DND_TIMEOUT";
    public static final Object BLOCKED_MASK = "BLOCKED_MASK";
    public static final String ERROR = "ERROR";
    public static final String MSG_SENT = "MSG_SENT";

    // PlayWs access for Restaurant

    public static final String CLICKTABLE_URL = "clicktable.url";
    public static final String CLICKTABLE_USER = "username.encode";
    public static final String CLICKTABLE_PASSWORD = "password.encode";
    public static final String ONBOARDING_URL = "onboarding.url";

    //invoice
    public static final String CT_NAME = "Clicktable Technologies LLP";
    public static final String CT_ADDRESS = "A-2, Sector 59, Noida, UP";
    public static final String PIN = "PIN";
    public static final String CT_PIN = "201309";
    public static final String PHONE = "Phone";
    public static final String CT_PHONE = "+91-120-3955555";
    public static final String EMAIL = "Email";
    public static final String CT_EMAIL = "bill@clicktable.com";
    public static final String WEBSITE = "Website";
    public static final String CT_WEBSITE = "www.clicktable.com";
    public static final String SERVICE_TAX = "Service Tax No";
    public static final String CT_SERVICE_TAX = "AADCK073LST001";
    public static final String VAT = "VAT No.";
    public static final String CT_VAT = "27122334221V";

    public static final String INVOICE_FOLDER = "InvoicePDF";
    public static final String INVOICE_TXT = "Invoice";

    public static final String INVOICE_NO = "Invoice No.";
    public static final String INVOICE_BOOK_NO = "Book No.";
    public static final String INVOICE_DATE = "Date";
    public static final String INVOICE_DUE_DT = "Due Date";
    public static final String INVOICE_STATUS_TXT = "Status";
    public static final String INVOICE_PAYMENT_DT = "Payment Date";
    public static final String INVOICE_PAYMENT_MD = "Payment Mode";
    public static final String BILL_TO = "Bill To";

    public static final String LINE = "Line";
    public static final String TYPE = "TYPE";
    public static final String DESCPTN = "Description";
    public static final String PRICE = "Price";
    public static final String QUNT = "Quantity";
    public static final String AMNT = "Amount";

    public static final String VAT_PRCNT = "Value Added Tax 10%";
    public static final String SERVC_TAX_PRCNT = "Service Tax 14.5%";
    public static final String RND_OFF = "Round Off";
    public static final String AMNT_IN_WRDS = "Total Amount in words";
    public static final String AMNT_TTL = "Total";
    public static final String RPS_ONLY = " Rupees Only";
    public static final String CURRENCY_IND = "INR";

    public static final String LOGIN_URI = "/staff/login";
    public static final String ONBOARDING_URI = "/onboarding";

    public static final String NA = "NA";
    public static final String GENERATED = "GENERATED";
    public static final String PAID = "PAID";
    public static final String CREATED = "CREATED";
    public static final String WAITING = "waiting";


    public static final String TAXCODE = "taxCode";
    public static final String RESTAURANTGUID = "restaurantGuid";

    public static final String MONTH = "month";
    public static final String YEAR = "year";

    public static final String STATE = "state";
    public static final String COUNTRY = "country";
    public static final String ACTIVE = "ACTIVE";
    public static final String INDIA = "INDIA";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String IST = "IST";

    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";

    public static final String USERINFOMODEL = "userInfo";

    public static final String VALIDTILLAFTER = "validTillAfter";
    public static final String VALIDFROMBEFORE = "validFromBefore";

    public static final String VALIDTILL_AFTER_ORON = "validTillAfterOrOn";
    public static final String VALIDFROM_BEFORE_ORON = "validFromBeforeOrOn";

    public static final String LIST = "list";
    public static final String ORDER_ID = "orderId";

    public static final String STARTDATE = "startDate";
    public static final String ENDDATE = "endDate";

    public static final String EVENT_PROMOTION = "EVENT_PROMOTION";
    public static final String ORIGIN = "origin";
    public static final String SUCCESS = "SUCCESS";
    public static final String ORIGIN_GUID = "originGuid";

    public static final String FILE_NOT_FOUND = "File not found";
    public static final String PATH = "//home";

    public static final String INVOICE_HEADERS = "headers";
    public static final String RESTAURANT_URI = "/restaurant";

    public static final String DELIVERY_DATE = "deliveryDate";
    public static final String ORDERBY = "orderBy";

    public static final String PROMOTIONAL_MESSAGE = "Promotional Message";
    public static final String SMS = "SMS";
    public static final String PDF_TEMPLATE_PATH = "support//conf//MainTemplate.pdf";
    public static final String PDF_FONT_PATH = "support//conf//calibri.ttf";

    public static final String ACTIVE_STATUS = "ACTIVE";
    public static final String INACTIVE_STATUS = "INACTIVE";

    public static final String NAME = "name";
    public static final String ITEM_NAME = "Item name";
    public static final String REST_GUID = "restaurantGuid";
    public static final String RESTAURANT_GUID = "restaurantGuid";

    public static final String ITEM = "Item";

    public static final String PLAN = "Plan";

    public static final String ITEM_ID = "itemId";

    public static final String PLAN_ID = "planId";

    public static final String PLAN_ITEM = "Plan Item";

    public static final String REST_PLAN = "Restaurant Plan";

    public static final String SUB_TOTAL = "Sub Total";

    public static final String INR_SYMBOL = "\u20B9 ";

    public static final String PLAN_ITEM_LIST = "PlanItemList";

    public static final String DUMMY = "DUMMY";

    public static final String ENUMS_FILE = "enum.properties";
}
