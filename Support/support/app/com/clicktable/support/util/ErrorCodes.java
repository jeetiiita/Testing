package com.clicktable.support.util;

public class ErrorCodes {

    // Basic Errors
    public static final String INVALID_STATUS = "1";
    public static final String INVALID_LANG_CODE = "2";
    public static final String INVALID_REGION = "3";
    public static final String INVALID_MOBILE_NO = "4";
    public static final String INVALID_EMAIL_FORMAT = "5";
    public static final String EMAIL_ACCOUNT_NOT_EXIST = "6";
    public static final String INVALID_VALUE = "7";
    public static final String REQUIRED = "error.required";
    public static final String NOT_UPDATABLE = "9";
    public static final String ONE_OF_THESE_REQUIRED = "10";

    public static final String GUID_REQUIRED = "11";
    public static final String INVALID_GUID = "12";

    public static final String ID_DOES_NOT_EXIST = "13";

    public static final String INVALID_PATTERN = "11111";
    public static final String INVALID_MOBILE = "11112";

    public static final String INVALID_HEADER = "702";
    public static final String LANG_CD = "703";

    // support
    public static final String SUBJECT_REQUIRED_SUPPORT = "360001";
    public static final String ACCOUNT_ID_REQUIRED_SUPPORT = "360002";
    public static final String RESTAURANT_NAME_REQUIRED_SUPPORT = "360003";
    public static final String USERNAME_REQUIRED_SUPPORT = "360004";
    public static final String DEVICE_REQUIRED_SUPPORT = "360005";
    public static final String OS_REQUIRED_SUPPORT = "360006";
    public static final String ISSUE_TYPE_REQUIRED_SUPPORT = "360007";
    public static final String DESCRIPTION_REQUIRED_SUPPORT = "360008";
    public static final String ATTACHMENT_MAX = "360009";
    public static final String INVALID_ATTACHMENT = "360010";
    public static final String ATTACHMENT_REQUIRED = "360011";
    public static final String SUBJECT_MAXLENGTH_SUPPORT = "360012";
    public static final String INVALID_USERNAME_SUPPORT = "360013";
    public static final String DEVICE_MAXLENGTH_SUPPORT = "360014";
    public static final String OS_MAXLENGTH_SUPPORT = "360015";
    public static final String DESCRIPTION_MAXLENGTH_SUPPORT = "360016";
    public static final String INVALID_FILE_NAME = "360017";
    public static final String FILE_SIZE = "360018";
    public static final String INVALID_ISSUE_TYPE = "360019";

    // conversation
    public static final String INVALID_ORIGIN = "200001";
    public static final String INVALID_SENT_BY = "200006";
    public static final String INVALID_GUEST_ID = "200002";
    public static final String TEMPLATE_MAXLENGTH = "200003";
    public static final String INVALID_TEMPLATE_GUID = "200004";
    public static final String TEMPLATE_SIZE = "200005";
    public static final String GUESTCONVERSATION_REST_GUID = "60201";

    public static final String GUESTCONVERSATION_MESSAGE_REQUIRED = "310202";
    public static final String GUESTCONVERSATION_MESSAGE_MAXLENGTH = "200203";
    public static final String GUESTCONVERSATION_GUEST_GUID = "60202";
    public static final String GUESTCONVERSATION_SENTBY = "200205";
    public static final String GUESTCONVERSATION_ORIGIN = "200206";
    public static final String GUESTCONVERSATION_ORIGIN_ID = "200207";
    public static final String GUESTCONVERSATION_CREATE_BY_REQUIRED = "200208";
    public static final String GUESTCONVERSATION_UPDATE_BY_REQUIRED = "200209";
    public static final String GUESTCONVERSATION_CREATE_BY_MAXLENGTH = "200210";
    public static final String GUESTCONVERSATION_UPDATE_BY_MAXLENGTH = "200211";
    public static final String GUESTCONVERSATION_SMS_ID = "200212";
    public static final String GUESTCONVERSATION_SMS_STATUS = "200213";
    public static final String GUESTCONVERSATION_SMS_CAUSE = "200214";
    public static final String GUESTCONVERSATION_GUEST_GUID_MAXLENGTH = "200215";
    public static final String GUESTCONVERSATION_SENT_BY_MAXLENGTH = "200216";
    public static final String GUESTCONVERSATION_ORIGIN_MAXLENGTH = "200217";
    public static final String GUESTCONVERSATION_SMS_ID_MAXLENGTH = "200218";
    public static final String GUESTCONVERSATION_ORIGIN_ID_MAXLENGTH = "200219";
    public static final String GUESTCONVERSATION_SMS_STATUS_MAXLENGTH = "200220";
    public static final String GUESTCONVERSATION_SMS_CAUSE_MAXLENGTH = "200221";
    public static final String GUESTCONVERSATION_GUID = "200222";
    public static final String GUESTCONVERSATION_GUID_MAXLENGTH = "200223";
    public static final String GUESTCONVERSATION_RESTAURANT_GUID_MAXLENGTH = "200224";
    public static final String GUESTCONVERSATION_GUEST_MOBILE_MAX_LENGTH = "200225";
    public static final String GUESTCONVERSATION_GUEST_MOBILE_MIN_LENGTH = "200226";
    public static final String GUESTCONVERSATION_GUEST_MOBILE_NUM = "200227";
    public static final String GUESTCONVERSATION_SMS_COUNT = "200228";

    // invoice


    public static final String INVOICE_ID_REQUIRED = "5000013";
    public static final String INVOICE_MESSAGE_MAXLENGTH = "5000014";
    public static final String INVOICE_REST_GUID_REQUIRED = "5000015";
    public static final String INVOICE_OWNER_NAME_REQUIRED = "5000016";
    public static final String INVOICE_OWNER_ADDRESS_REQUIRED = "5000017";
    public static final String INVOICE_OWNER_EMAIL_REQUIRED = "5000018";
    public static final String INVOICE_OWNER_PHONE_REQUIRED = "5000019";
    public static final String INVOICE_DATE_REQUIRED = "5000020";
    public static final String INVOICE_TAX_AMOUNT_REQUIRED = "5000021";
    public static final String INVOICE_BASIC_AMOUNT_REQUIRED = "5000022";
    public static final String INVOICE_DISCOUNT_REQUIRED = "5000023";
    public static final String INVOICE_AMOUNT_REQUIRED = "5000024";
    public static final String INVOICE_STATUS_REQUIRED = "5000025";
    public static final String INVOICE_CURRENCY_REQUIRED = "5000026";
    public static final String INVOICE_TAX_YEAR_REQUIRED = "5000027";
    public static final String INVOICE_ROUND_AMOUNT_REQUIRED = "5000028";
    public static final String INVOICE_DUE_DATE_REQUIRED = "5000029";


    //InvoiceLine

    public static final String INVOICE_LINE_NUMBER_REQUIRED = "5100001";
    public static final String INVOICE_LINE_ORDER_DATE_REQUIRED = "5100002";
    public static final String INVOICE_LINE_ITEM_REQUIRED = "5100003";
    public static final String INVOICE_LINE_ITEM_QTY_REQUIRED = "5100004";
    public static final String INVOICE_LINE_ITEM_PLAN_PRICE_REQUIRED = "5100005";
    public static final String INVOICE_LINE_TOTAL_AMOUNT_REQUIRED = "5100006";
    public static final String INVOICE_LINE_BASIC_AMOUNT_REQUIRED = "5100007";
    public static final String INVOICE_LINE_TAX_AMOUNT_REQUIRED = "5100008";
    public static final String INVOICE_LINE_REST_GUID_REQUIRED = "5100009";


    // Invoice Max and Min

    public static final String INVOICE_TAX_AMOUNT_MAX_VALUE = "5000034";
    public static final String INVOICE_TAX_AMOUNT_MIN_VALUE = "5000035";
    public static final String INVOICE_DISCOUNT_MAX_VALUE = "5000036";
    public static final String INVOICE_DISCOUNT_MIN_VALUE = "5000037";
    public static final String INVOICE_AMOUNT_MAX_VALUE = "5000038";
    public static final String INVOICE_AMOUNT_MIN_VALUE = "5000039";

    public static final String INVOICE_TAX_YEAR_MAX_VALUE = "5000040";
    public static final String INVOICE_TAX_YEAR_MIN_VALUE = "5000041";
    public static final String INVOICE_ROUND_AMOUNT_MAX_VALUE = "5000042";
    public static final String INVOICE_ROUND_AMOUNT_MIN_VALUE = "5000043";

    public static final String INVOICE_GUID_MAXLENGTH = "5000044";
    public static final String INVOICE_NUM_MAXLENGTH = "5000045";
    public static final String INVOICE_REST_GUID_MAXLENGTH = "5000046";
    public static final String INVOICE_OWNER_NAME_MAXLENGTH = "5000047";
    public static final String INVOICE_OWNER_ADDRESS_MAXLENGTH = "5000048";
    public static final String INVOICE_OWNER_EMAIL_MAXLENGTH = "5000049";
    public static final String INVOICE_OWNER_PHONE_MAXLENGTH = "5000050";
    public static final String INVOICE_STATUS_MAXLENGTH = "5000051";
    public static final String INVOICE_REMARKS_MAXLENGTH = "5000052";

    public static final String INVOICE_CONVERSATION_COUNT_REQUIRED = "5000053";
    public static final String INVOICE_CONVERSATION_COUNT_MAX_VALUE = "5000054";
    public static final String INVOICE_CONVERSATION_COUNT_MIN_VALUE = "5000055";

    public static final String INVOICE_CONVERSATION_MONTH_REQUIRED = "5000056";
    public static final String INVOICE_CONVERSATION_MONTH_MAX_VALUE = "5000057";
    public static final String INVOICE_CONVERSATION_MONTH_MIN_VALUE = "5000058";
    public static final String INVOICE_CONVERSATION_YEAR_REQUIRED = "5000059";
    public static final String INVOICE_CONVERSATION_YEAR_MAX_VALUE = "5000060";
    public static final String INVOICE_CONVERSATION_YEAR_MIN_VALUE = "5000061";

    public static final String INVOICE_UNBILLED_AMOUNT_REQUIRED = "5000062";

    public static final String INVOICE_UNBILLED_MAX_VALUE = "5000063";
    public static final String INVOICE_UNBILLED_MIN_VALUE = "5000064";

    public static final String INVOICE_YEAR_MAX_VALUE = "5000065";
    public static final String INVOICE_YEAR_REQUIRED = "5000066";
    public static final String INVOICE_YEAR_MIN_VALUE = "5000067";
    public static final String INVOICE_MONTH_REQUIRED = "5000068";
    public static final String INVOICE_MONTH_MAX_VALUE = "5000069";
    public static final String INVOICE_MONTH_MIN_VALUE = "5000070";

    public static final String GUID_MAXLENGTH = "5000071";

    public static final String CREATED_DATE_REQUIRED = "5000072";
    public static final String UPDATED_DATE_REQUIRD = "5000073";

    public static final String CREATED_BY_REQUIRED = "5000074";
    public static final String UPDATED_BY_REQUIRD = "5000075";

    public static final String INVOICE_CONVERSATION_QUANTITY_REQUIRED = "6000001";
    public static final String INVOICE_UNIT_PRICE_REQUIRED = "6000002";

    public static final String INVOICE_SENT_DATE_REQUIRED = "6000003";
    public static final String INVOICE_CONVERSATION_TAX_AMOUNT_REQUIRED = "6000004";
    public static final String INVOICE_SUBTOTAL_REQUIRED = "6000005";

    public static final String INVOICE_CONVERSATION_QUANTITY_MAX_VALUE = "6000006";
    public static final String INVOICE_CONVERSATION_QUANTITY_MIN_VALUE = "6000007";
    public static final String INVOICE_UNIT_PRICE_MAX_VALUE = "6000008";
    public static final String INVOICE_UNIT_PRICE_MIN_VALUE = "6000009";
    public static final String INVOICE_TAX_AMOUNT_TXN_MAX_VALUE = "6000010";
    public static final String INVOICE_TAX_AMOUNT_TXN_MIN_VALUE = "6000011";

    public static final String INVOICE_SUBTOTAL_MAX_VALUE = "6000012";
    public static final String INVOICE_SUBTOTAL_MIN_VALUE = "6000013";
    public static final String INVOICE_NOT_FOUND = "6000014";

    //TAX
    public static final String TAX_NAME_REQUIRED = "6000015";
    public static final String TAX_DESCRIPTION_REQUIRED = "6000016";
    public static final String TAX_STATUS_REQUIRED = "6000017";
    public static final String TAX_RATE_REQUIRED = "6000018";
    public static final String TAX_RATE_MAX_VALUE = "6000019";
    public static final String TAX_RATE_MIN_VALUE = "6000020";
    public static final String TAX_STATE_TAX_REQUIRED = "6000021";
    public static final String TAX_COUNTRY_REQUIRED = "6000022";
    public static final String TAX_STATE_REQUIRED = "6000023";

    public static final String INVALID_TAX_STATUS = "6000024";
    public static final String INVALID_TAX_STATE = "6000025";
    public static final String INVALID_TAX_COUNTRY = "6000026";
    public static final String INVALID_TAX_TYPE = "6000027";
    public static final String DUPLICATE_TAX_NAME = "6000028";
    public static final String INVALID_TAX_NAME = "6000029";


    //AGGREAGATE TAX
    public static final String AGGREGATE_TAX_ID_REQUIRED = "7000001";
    public static final String SINGLE_TAX_ID_REQUIRED = "7000002";
    public static final String AGGREGATE_TAX_RATE_REQUIRED = "7000003";
    public static final String STATUS_ALREADY_INACTIVE = "7000004";
    public static final String START_DATE_REQUIRED = "7000005";
    public static final String END_DATE_REQUIRED = "7000006";
    public static final String TAX_LAYERS_INFO_REQUIRED = "7000007";

    public static final String INVALID_APPLICABLE_LAYER = "7000017";
    public static final String AGGRGEGATE_TAX_AND_SINGLE_TAX_CODE_ENTRY_EXIST = "7000018";
    public static final String CENTRAL_TAX_REQUIRED = "7000019";

    //TaxLayer
    public static final String AGGREGATE_TAX_REQUIRED = "7100001";
    public static final String AGGREGATE_APPLY_ON_LAYER_REQUIRED = "7100002";
    public static final String SINGLE_TAX_REQUIRED = "7100003";
    public static final String TAX_LAYER_NUMBER_MAX_VALUE = "7100004";
    public static final String TAX_LAYER_NUMBER_MIN_VALUE = "7100005";
    public static final String TAX_LAYER_NUMBER_REQUIRED = "7100006";
    public static final String AGGREGATE_SINGLE_TAX_ENTRY_ALREADY_EXIST = "7100007";

    //ItemAggregaetTax
    public static final String ITEM_AGGREGATE_TAX_START_DATE_REQUIRED = "7110001";
    public static final String ITEM_AGGREGATE_TAX_END_DATE_REQUIRED = "7110002";
    public static final String ITEM_AGGREGATE_TAX_ALREADY_EXIST = "7110003";
    public static final String START_DATE_AFTER_END_DATE = "7110004";
    public static final String AGGREGATE_TAX_OF_SAME_STATE_NOT_ALLOWED = "7110005";

    public static final String SINGLE_TAX_ATTACHED_WITH_AGGRGATETAX = "7110006";

    //State
    public static final String STATE_NAME_REQUIRED = "7200001";
    public static final String STATE_CODE_REQUIRED = "7200002";
    public static final String STATE_CODE_ALREADY_EXIST = "7200003";


    // Delivery
    public static final String DELIVERY_EXTERNAL_ID_REQUIRED = "800001";
    public static final String DELIVERY_EXTERNAL_ID_MAXLENGTH = "800002";
    public static final String DELIVERYL_TIME_REQUIRED = "800003";
    public static final String DELIVERY_TIME_MAXLENGTH = "800004";
    public static final String DELIVERYL_STATUS_REQUIRED = "800005";
    public static final String DELIVERY_STATUS_MAXLENGTH = "800006";
    public static final String DELIVERYL_PHONE_REQUIRED = "800007";
    public static final String DELIVERY_PHONE_MAXLENGTH = "800008";
    public static final String DELIVERYL_CAUSE_REQUIRED = "800009";
    public static final String DELIVERY_CAUSE_MAXLENGTH = "800010";
    public static final String DELIVERY_PHONE_MINLENGTH = "800011";
    public static final String INVALID_SMS_STATUS = "800012";
    public static final String INVALID_SMS_STATUS_CAUSE = "800013";

    // ENUM TAX AND INVOICE

    public static final String INVALID_INVOICE_COUNTRY = "9000002";
    public static final String INVALID_INVOICE_STATE = "9000003";
    public static final String INVALID_INVOICE_STATUS = "9000004";
    public static final String INVALID_INVOICE_CURRENCY = "9000005";

    public static final String INVALID_TAX_CATEGORY_STATUS = "9000008";

    public static final String DUPLICATE_CAT_CODE = "9000010";

    public static final String INVALID_TAX_GUID = "9000011";
    public static final String TAX_GUID_REQUIRED = "9000012";

    public static final String TAX_CATEGRORY_GUID_REQUIRED = "9000013";
    public static final String INVALID_TAX_CATEGORY_GUID = "9000014";
    public static final String VALIDTILL_AND_VALIDAFTER_INTEVRAL_EXIST = "9000015";
    public static final String VALIDAFTER_EXCEEDS_VALIDTILL_DATE = "9000016";
    public static final String OUT_OF_BOUND_DATES_FROM_TAX = "9000017";
    public static final String TAX_CATEGORY_CODE_NOT_EXIST = "9000018";

    // payment

    public static final String PAYMENT_TRACKING_ID_REQUIRED = "10000001";
    public static final String INVOICE_PAYMENT_BANK_REF_NO_REQUIRED = "10000002";
    public static final String PAYMENT_ORDER_STATUS_REQUIRED = "10000003";
    public static final String PAYMENT_FAILURE_MESSAGE_REQUIRED = "10000004";


    public static final String PAYMENT_MODE_REQUIRED = "10000005";
    public static final String PAYMENT_CARD_NAME_REQUIRED = "10000006";
    public static final String PAYMENT_STATUS_CODE_REQUIRED = "10000007";
    public static final String PAYMENT_STATUS_MESSAGE_REQUIRD = "10000008";

    public static final String PAYMENT_CURRENCY_REQUIRED = "10000009";
    public static final String PAYMENT_AMOUNT_REQUIRED = "10000010";
    public static final String ID_MISSING = "10000011";


    public static final String PAYMENT_ORDER_STATUS_FAILED = "10000012";
    public static final String INVOICE_NOT_VALID_FOR_PAYMENT = "10000014";
    public static final String PAYMENT_ORDER_ID_MISSING = "10000015";

    //item
    public static final String ITEM_NAME_REQUIRED = "1100001";
    public static final String ITEM_DESCRIPTION_REQUIRED = "1100002";
    public static final String ITEM_DEFAULT_PRICE_REQUIRED = "1100003";
    public static final String ITEM_STATUS_REQUIRED = "1100004";
    public static final String ITEM_WITH_NAME_EXISTS = "1100005";
    public static final String ITEM_STATUS_INACTIVE = "1100006";
    public static final String ITEM_ABSENT = "1100007";
    public static final String ITEM_TYPE_REQUIRED = "1100008";
    public static final String DISPLAY_NAME_REQUIRED = "1100009";


    //plan
    public static final String PLAN_NAME_REQUIRED = "1100011";
    public static final String PLAN_STATUS_REQUIRED = "1100012";
    public static final String PLAN_WITH_NAME_EXISTS = "1100013";
    public static final String PLAN_STATUS_INACTIVE = "1100014";
    public static final String PLAN_ABSENT = "1100015";
    public static final String PLAN_DOESNOT_EXISTS = "1100016";
    public static final String PLAN_DISPLAY_NAME_REQUIRED = "1100017";

    //planItem
    public static final String PLAN_REQUIRED = "1100021";
    public static final String ITEM_REQUIRED = "1100022";
    public static final String PLAN_ITEM_REQUIRED = "1100023";
    public static final String ITEM_UNIT_PRICE_REQUIRED = "1100024";
    public static final String MIN_ITEM_QTY_REQUIRED = "1100025";
    public static final String PLAN_ITEM_EXISTS = "1100026";
    public static final String PLAN_ITEM_LIST_MISSING = "1100027";
    public static final String ITEM_DUPLICATE = "1100028";

    //restPlan
    public static final String REST_GUID_REQUIRED = "1100031";
    public static final String REST_PLAN_START_DATE_REQUIRED = "1100032";
    public static final String REST_PLAN_END_DATE_REQUIRED = "1100033";
    public static final String REST_PLAN_STATUS_REQUIRED = "1100034";
    public static final String REST_PLAN_EXISTS = "1100035";
    public static final String REST_PLAN_DOESNOT_EXISTS = "1100036";
    public static final String REST_PLAN_INACTIVE = "1100037";

    public static final String INVOICE_DOES_NOT_EXISTS = "1100047";
    
    //Transaction History
	public static final String TXN_USER_GUID_REQUIRED = "123121";
	public static final String TXN_REQST_TYPE_REQUIRED = "123122";
	public static final String TXN_REQST_URL_REQUIRED = "123123";
	public static final String TXN_STATUS_REQUIRED = "123124";
	public static final String TXN_CREATED_DATE_REQUIRED = "123125";
	public static final String INVALID_REQUEST_TYPE = "123126";
    public static final String TXN_STATUS_CODE_REQUIRED = "123127";
}
