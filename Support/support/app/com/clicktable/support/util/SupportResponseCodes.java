// $codepro.audit.disable fieldJavadoc

package com.clicktable.support.util;

public class SupportResponseCodes extends com.clicktable.util.ResponseCodes {
    // Guest Conversation
    public static final String GUEST_CONVERSATION_ADDED_SUCCESFULLY = "26001";
    public static final String GUEST_CONVERSATION_ADDITION_FAILURE = "26002";
    public static final String GUEST_CONVERSATION_RECORD_FETCH_SUCCESFULLY = "26003";
    public static final String GUEST_CONVERSATION_SUCCESFULLY = "26004";
    public static final String GUEST_CONVERSATION_FAILURE = "26005";
    public static final String GUEST_CONVERSATION_UPDATED_SUCCESFULLY = "26006";
    public static final String GUEST_CONVERSATION_UPDATION_FAILURE = "26007";
    public static final String RESTAURANT_EVENT_PROMOTION_FECTCHED_FAILED = "26008";

    //Invoice

    public static final String INVOICE_TXN_ADDED_SUCCESSFULLY = "5100001";
    public static final String INVOICE_TXN_ADDITION_FAILURE = "5100002";

    public static final String INVOICE_TXN_UPDATED_SUCCESSFULLY = "5100011";
    public static final String INVOICE_TXN_UPDATION_FAILURE = "5100012";

    public static final String INVOICE_ADDED_SUCCESSFULLY = "5100021";
    public static final String INVOICE_ADDITION_FAILURE = "5100022";

    public static final String INVOICE_UPDATED_SUCCESSFULLY = "5100031";
    public static final String INVOICE_UPDATION_FAILURE = "5100032";

    public static final String INVOICE_TXN_AMOUNT_FETCHED_SUCCESSFULLY = "5100033";

    //Aggregate Tax
    public static final String AGGREGATE_TAX_ADDED_SUCCESSFULLY = "6100001";
    public static final String AGGREGATE_TAX_ADDITION_FAILURE = "6100002";
    public static final String AGGREGATE_TAX_FETCHED_SUCCESSFULLY = "6100003";
    public static final String AGGREGATE_TAX_UPDATION_FAILURE = "6100004";
    public static final String AGGREGATE_TAX_UPDATED_SUCCESSFULLY = "6100005";

    //ItemAggregateTax
    public static final String ITEM_AGGREGATE_TAX_ADDED_SUCCESSFULLY = "6110001";
    public static final String ITEM_AGGREGATE_TAX_ADDITION_FAILURE = "6110002";
    public static final String ITEM_AGGREGATE_TAX_UPDATED_SUCCESSFULLY = "6110003";
    public static final String ITEM_AGGREGATE_TAX_UPDATION_FAILURE = "6110004";
    public static final String ITEM_AGGREGATE_TAX_FETCHED_SUCCESSFULLY = "6110005";


    //Single Tax
    public static final String SINGLE_TAX_ADDED_SUCCESSFULLY = "6200001";
    public static final String SINGLE_TAX_ADDITION_FAILURE = "6200002";
    public static final String SINGLE_TAX_FETCHED_SUCCESSFULLY = "6200003";
    public static final String SINGLE_TAX_UPDATION_FAILURE = "6200004";
    public static final String SINGLE_TAX_UPDATED_SUCCESSFULLY = "6200005";


    //TaxLayer
    public static final String TAX_LAYER_ADDED_SUCCESSFULLY = "6300001";
    public static final String TAX_LAYER_ADDITION_FAILURE = "6300002";
    public static final String TAX_LAYER_FETCHED_SUCCESSFULLY = "6300003";
    public static final String TAX_LAYER_UPDATION_FAILURE = "6300004";
    public static final String TAX_LAYER_UPDATED_SUCCESSFULLY = "6300005";


    public static final String INVOICE_CREATED_SUCCESSFULLY = "6100013";
    public static final String INVOICE_CREATION_FAILURE = "6100014";

    public static final String INVOICE_LINE_FETCHED_SUCCESSFULLY = "6100015";

    public static final String INVOICE_FETCHED_SUCCESSFULLY = "6100016";
    public static final String TAX_CATEGORY_FETCHED_SUCCESSFULLY = "6100017";
    public static final String TAX_FETCHED_SUCCESSFULLY = "6100018";
    public static final String TAX_UPDATION_FAILURE = "6100019";
    public static final String TAX_UPDATED_SUCCESSFULLY = "6100020";
    public static final String TAX_CATEGORY_UPDATION_FAILURE = "6100021";
    public static final String TAX_CATEGORY_UPDATED_SUCCESSFULLY = "6100022";

    public static final String INVOICE_TXN_GUID_UPDATION_FAILURE = "6100023";
    public static final String INVOICE_GENERATED_SUCCESSFULLY = "6100024";
    public static final String INVOICE_TAX_FETCHED_SUCCESSFULLY = "6100025";

    //Payment
    public static final String USER_DETAILS_ENCRYPTED_SUCCESSFULLY = "7100001";
    public static final String USER_DETAILS_ENCRYPTION_FAILURE = "7100002";
    public static final String RESTAURANT_EVENT_PROMOTION_MESSAGES_FETCHED_SUCCESSFULLY = "7100003";
    public static final String PAYMENT_SUCCESSFULL = "7100004";
    public static final String PAYMENT_UPDATION_FAILURE = "7100005";
    public static final String PAYMENT_FAILED = "7100006";


    //item
    public static final String ITEM_ADDED_SUCCESSFULLY = "1200001";
    public static final String ITEM_ADDITION_FAILURE = "1200002";
    public static final String ITEM_FETCHED_SUCCESSFULLY = "1200003";
    public static final String ITEM_UPDATED_SUCCESSFULLY = "1200004";
    public static final String ITEM_UPDATION_FAILURE = "1200005";


    //plan
    public static final String PLAN_ADDED_SUCCESSFULLY = "1200011";
    public static final String PLAN_ADDITION_FAILURE = "1200012";
    public static final String PLAN_FETCHED_SUCCESSFULLY = "1200013";
    public static final String PLAN_UPDATED_SUCCESSFULLY = "1200014";
    public static final String PLAN_UPDATION_FAILURE = "1200015";

    //plan item
    public static final String PLAN_ITEM_ADDED_SUCCESSFULLY = "1200021";
    public static final String PLAN_ITEM_ADDITION_FAILURE = "1200022";
    public static final String PLAN_ITEM_FETCHED_SUCCESSFULLY = "1200023";

    //rest plan
    public static final String REST_PLAN_ADDED_SUCCESSFULLY = "1200031";
    public static final String REST_PLAN_ADDITION_FAILURE = "1200032";
    public static final String REST_PLAN_FETCHED_SUCCESSFULLY = "1200033";
    public static final String REST_PLAN_FETCH_FAILURE = "1200034";
    public static final String REST_PLAN_UPDATED_SUCCESSFULLY = "1200035";
    public static final String REST_PLAN_UPDATION_FAILURE = "1200036";
    
    //TransactionHistory
	public static final String TXN_HISTORY_ADDITION_SUCCESSFUL = "123211";
	public static final String TXN_HISTORY_ADDITION_FAILURE = "123212";
	public static final String TXN_HISTORY_FETCH_SUCCESSFUL = "123213";
    public static final String TXN_HISTORY_FETCH_FAILURE = "123214";

}
