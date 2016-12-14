// @SOURCE:C:/Users/j.yadav/R4/clicktable-app/support/conf/routes
// @HASH:f10c5ce287f9005658cbb7af92d14dcc068095dc
// @DATE:Fri Jul 15 14:34:21 IST 2016


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val com_clicktable_support_controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val com_clicktable_support_controllers_Application_index0_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.Application]).index(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:7
private[this] lazy val com_clicktable_support_controllers_Application_preflight1_route = Route("OPTIONS", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),DynamicPart("all", """.+""",false))))
private[this] lazy val com_clicktable_support_controllers_Application_preflight1_invoker = createInvoker(
com.clicktable.support.controllers.Application.preflight(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.Application", "preflight", Seq(classOf[String]),"OPTIONS", """""", Routes.prefix + """$all<.+>"""))
        

// @LINE:9
private[this] lazy val com_clicktable_support_controllers_ConversationController_getGuestConversation2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("conversation"))))
private[this] lazy val com_clicktable_support_controllers_ConversationController_getGuestConversation2_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).getGuestConversation,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "getGuestConversation", Nil,"GET", """""", Routes.prefix + """conversation"""))
        

// @LINE:11
private[this] lazy val com_clicktable_support_controllers_ConversationController_insertGuestConversation3_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("conversation"))))
private[this] lazy val com_clicktable_support_controllers_ConversationController_insertGuestConversation3_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).insertGuestConversation,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "insertGuestConversation", Nil,"POST", """GET    /conversation/history    @com.clicktable.support.controllers.ConversationController.getGuestConversationCount""", Routes.prefix + """conversation"""))
        

// @LINE:12
private[this] lazy val com_clicktable_support_controllers_ConversationController_insertMultipleGuestConversation4_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("conversation/multiple"))))
private[this] lazy val com_clicktable_support_controllers_ConversationController_insertMultipleGuestConversation4_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).insertMultipleGuestConversation,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "insertMultipleGuestConversation", Nil,"POST", """""", Routes.prefix + """conversation/multiple"""))
        

// @LINE:13
private[this] lazy val com_clicktable_support_controllers_ConversationController_updateConversation5_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("conversation"))))
private[this] lazy val com_clicktable_support_controllers_ConversationController_updateConversation5_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).updateConversation,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "updateConversation", Nil,"PUT", """""", Routes.prefix + """conversation"""))
        

// @LINE:14
private[this] lazy val com_clicktable_support_controllers_ConversationController_getResaturantEventPromotion6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("report/promotion"))))
private[this] lazy val com_clicktable_support_controllers_ConversationController_getResaturantEventPromotion6_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).getResaturantEventPromotion,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "getResaturantEventPromotion", Nil,"GET", """""", Routes.prefix + """report/promotion"""))
        

// @LINE:15
private[this] lazy val com_clicktable_support_controllers_ConversationController_delivery7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("conversation/delivery"))))
private[this] lazy val com_clicktable_support_controllers_ConversationController_delivery7_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).delivery,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "delivery", Nil,"GET", """""", Routes.prefix + """conversation/delivery"""))
        

// @LINE:20
private[this] lazy val controllers_Assets_versioned8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_versioned8_invoker = createInvoker(
controllers.Assets.versioned(fakeValue[String], fakeValue[Asset]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "versioned", Seq(classOf[String], classOf[Asset]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:22
private[this] lazy val com_clicktable_support_controllers_InvoiceController_getInvoicePdf9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/export/download"))))
private[this] lazy val com_clicktable_support_controllers_InvoiceController_getInvoicePdf9_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.InvoiceController]).getInvoicePdf,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.InvoiceController", "getInvoicePdf", Nil,"GET", """""", Routes.prefix + """support/export/download"""))
        

// @LINE:23
private[this] lazy val com_clicktable_support_controllers_InvoiceController_getInvoice10_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/invoice"))))
private[this] lazy val com_clicktable_support_controllers_InvoiceController_getInvoice10_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.InvoiceController]).getInvoice(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.InvoiceController", "getInvoice", Nil,"GET", """""", Routes.prefix + """support/invoice"""))
        

// @LINE:25
private[this] lazy val com_clicktable_support_controllers_EnumController_getAllEnums11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/enum"))))
private[this] lazy val com_clicktable_support_controllers_EnumController_getAllEnums11_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.EnumController]).getAllEnums,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.EnumController", "getAllEnums", Nil,"GET", """GET    /delivery               		    @com.clicktable.support.controllers.ConversationController.delivery""", Routes.prefix + """support/enum"""))
        

// @LINE:28
private[this] lazy val com_clicktable_support_controllers_SingleTaxController_getSingleTaxes12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/singleTax"))))
private[this] lazy val com_clicktable_support_controllers_SingleTaxController_getSingleTaxes12_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).getSingleTaxes,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.SingleTaxController", "getSingleTaxes", Nil,"GET", """Single tax""", Routes.prefix + """support/singleTax"""))
        

// @LINE:29
private[this] lazy val com_clicktable_support_controllers_SingleTaxController_addSingleTax13_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/singleTax"))))
private[this] lazy val com_clicktable_support_controllers_SingleTaxController_addSingleTax13_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).addSingleTax,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.SingleTaxController", "addSingleTax", Nil,"POST", """""", Routes.prefix + """support/singleTax"""))
        

// @LINE:30
private[this] lazy val com_clicktable_support_controllers_SingleTaxController_disableSingleTax14_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/singleTax/disable/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_clicktable_support_controllers_SingleTaxController_disableSingleTax14_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).disableSingleTax(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.SingleTaxController", "disableSingleTax", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """support/singleTax/disable/$id<[^/]+>"""))
        

// @LINE:33
private[this] lazy val com_clicktable_support_controllers_AggregateTaxController_getAggregateTaxes15_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/aggregateTax"))))
private[this] lazy val com_clicktable_support_controllers_AggregateTaxController_getAggregateTaxes15_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).getAggregateTaxes,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.AggregateTaxController", "getAggregateTaxes", Nil,"GET", """Aggregate Tax""", Routes.prefix + """support/aggregateTax"""))
        

// @LINE:34
private[this] lazy val com_clicktable_support_controllers_AggregateTaxController_addAggregateTax16_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/aggregateTax"))))
private[this] lazy val com_clicktable_support_controllers_AggregateTaxController_addAggregateTax16_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).addAggregateTax,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.AggregateTaxController", "addAggregateTax", Nil,"POST", """""", Routes.prefix + """support/aggregateTax"""))
        

// @LINE:35
private[this] lazy val com_clicktable_support_controllers_AggregateTaxController_disableAggregateTax17_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/aggregateTax/disable/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_clicktable_support_controllers_AggregateTaxController_disableAggregateTax17_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).disableAggregateTax(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.AggregateTaxController", "disableAggregateTax", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """support/aggregateTax/disable/$id<[^/]+>"""))
        

// @LINE:38
private[this] lazy val com_clicktable_support_controllers_StateController_getState18_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/state"))))
private[this] lazy val com_clicktable_support_controllers_StateController_getState18_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).getState,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.StateController", "getState", Nil,"GET", """state""", Routes.prefix + """support/state"""))
        

// @LINE:39
private[this] lazy val com_clicktable_support_controllers_StateController_addState19_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/state"))))
private[this] lazy val com_clicktable_support_controllers_StateController_addState19_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).addState,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.StateController", "addState", Nil,"POST", """""", Routes.prefix + """support/state"""))
        

// @LINE:40
private[this] lazy val com_clicktable_support_controllers_StateController_disableState20_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/state/disable/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_clicktable_support_controllers_StateController_disableState20_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).disableState(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.StateController", "disableState", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """support/state/disable/$id<[^/]+>"""))
        

// @LINE:44
private[this] lazy val com_clicktable_support_controllers_PaymentController_startPayment21_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/payment"))))
private[this] lazy val com_clicktable_support_controllers_PaymentController_startPayment21_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PaymentController]).startPayment(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PaymentController", "startPayment", Nil,"POST", """payment""", Routes.prefix + """support/payment"""))
        

// @LINE:45
private[this] lazy val com_clicktable_support_controllers_PaymentController_updatePaymentDetail22_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/payment/callback"))))
private[this] lazy val com_clicktable_support_controllers_PaymentController_updatePaymentDetail22_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PaymentController]).updatePaymentDetail(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PaymentController", "updatePaymentDetail", Nil,"POST", """""", Routes.prefix + """support/payment/callback"""))
        

// @LINE:48
private[this] lazy val com_clicktable_support_controllers_ItemController_createItem23_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/item"))))
private[this] lazy val com_clicktable_support_controllers_ItemController_createItem23_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).createItem(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ItemController", "createItem", Nil,"POST", """item""", Routes.prefix + """support/item"""))
        

// @LINE:49
private[this] lazy val com_clicktable_support_controllers_ItemController_getItems24_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/item"))))
private[this] lazy val com_clicktable_support_controllers_ItemController_getItems24_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).getItems(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ItemController", "getItems", Nil,"GET", """""", Routes.prefix + """support/item"""))
        

// @LINE:50
private[this] lazy val com_clicktable_support_controllers_ItemController_updateItem25_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/item/update"))))
private[this] lazy val com_clicktable_support_controllers_ItemController_updateItem25_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).updateItem(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ItemController", "updateItem", Nil,"PUT", """""", Routes.prefix + """support/item/update"""))
        

// @LINE:53
private[this] lazy val com_clicktable_support_controllers_PlanController_createPlan26_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/plan"))))
private[this] lazy val com_clicktable_support_controllers_PlanController_createPlan26_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).createPlan(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PlanController", "createPlan", Nil,"POST", """plan""", Routes.prefix + """support/plan"""))
        

// @LINE:54
private[this] lazy val com_clicktable_support_controllers_PlanController_getPlans27_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/plan"))))
private[this] lazy val com_clicktable_support_controllers_PlanController_getPlans27_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).getPlans(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PlanController", "getPlans", Nil,"GET", """""", Routes.prefix + """support/plan"""))
        

// @LINE:55
private[this] lazy val com_clicktable_support_controllers_PlanController_disablePlan28_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/plan/disable/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_clicktable_support_controllers_PlanController_disablePlan28_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).disablePlan(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PlanController", "disablePlan", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """support/plan/disable/$id<[^/]+>"""))
        

// @LINE:58
private[this] lazy val com_clicktable_support_controllers_RestaurantPlanController_createRestaurantPlan29_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/restaurantPlan"))))
private[this] lazy val com_clicktable_support_controllers_RestaurantPlanController_createRestaurantPlan29_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).createRestaurantPlan(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.RestaurantPlanController", "createRestaurantPlan", Nil,"POST", """restaurant plan""", Routes.prefix + """support/restaurantPlan"""))
        

// @LINE:59
private[this] lazy val com_clicktable_support_controllers_RestaurantPlanController_getRestaurantPlan30_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/restaurantPlan"))))
private[this] lazy val com_clicktable_support_controllers_RestaurantPlanController_getRestaurantPlan30_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).getRestaurantPlan(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.RestaurantPlanController", "getRestaurantPlan", Nil,"GET", """""", Routes.prefix + """support/restaurantPlan"""))
        

// @LINE:60
private[this] lazy val com_clicktable_support_controllers_RestaurantPlanController_getRestaurantPlanByPlan31_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/restaurantPlan/plan"))))
private[this] lazy val com_clicktable_support_controllers_RestaurantPlanController_getRestaurantPlanByPlan31_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).getRestaurantPlanByPlan(),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.RestaurantPlanController", "getRestaurantPlanByPlan", Nil,"GET", """""", Routes.prefix + """support/restaurantPlan/plan"""))
        

// @LINE:61
private[this] lazy val com_clicktable_support_controllers_RestaurantPlanController_disableRestaurantPlan32_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/restaurantPlan/disable/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_clicktable_support_controllers_RestaurantPlanController_disableRestaurantPlan32_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).disableRestaurantPlan(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.RestaurantPlanController", "disableRestaurantPlan", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """support/restaurantPlan/disable/$id<[^/]+>"""))
        

// @LINE:64
private[this] lazy val com_clicktable_support_controllers_TransactionHistoryController_addTransactionHistory33_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/transactionHistory"))))
private[this] lazy val com_clicktable_support_controllers_TransactionHistoryController_addTransactionHistory33_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.TransactionHistoryController]).addTransactionHistory,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.TransactionHistoryController", "addTransactionHistory", Nil,"POST", """Transaction History""", Routes.prefix + """support/transactionHistory"""))
        

// @LINE:65
private[this] lazy val com_clicktable_support_controllers_TransactionHistoryController_getTransactionHistory34_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/transactionHistory"))))
private[this] lazy val com_clicktable_support_controllers_TransactionHistoryController_getTransactionHistory34_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.TransactionHistoryController]).getTransactionHistory,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.TransactionHistoryController", "getTransactionHistory", Nil,"GET", """""", Routes.prefix + """support/transactionHistory"""))
        

// @LINE:68
private[this] lazy val com_clicktable_support_controllers_SchedulerController_runJob35_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("support/invoiceGenScheduler"))))
private[this] lazy val com_clicktable_support_controllers_SchedulerController_runJob35_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SchedulerController]).runJob,
HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.SchedulerController", "runJob", Nil,"GET", """Run Invoice Generation Scheduler""", Routes.prefix + """support/invoiceGenScheduler"""))
        
def documentation = List(("""GET""", prefix,"""@com.clicktable.support.controllers.Application@.index()"""),("""OPTIONS""", prefix + (if(prefix.endsWith("/")) "" else "/") + """$all<.+>""","""com.clicktable.support.controllers.Application.preflight(all:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conversation""","""@com.clicktable.support.controllers.ConversationController@.getGuestConversation"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conversation""","""@com.clicktable.support.controllers.ConversationController@.insertGuestConversation"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conversation/multiple""","""@com.clicktable.support.controllers.ConversationController@.insertMultipleGuestConversation"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conversation""","""@com.clicktable.support.controllers.ConversationController@.updateConversation"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """report/promotion""","""@com.clicktable.support.controllers.ConversationController@.getResaturantEventPromotion"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """conversation/delivery""","""@com.clicktable.support.controllers.ConversationController@.delivery"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.versioned(path:String = "/public", file:Asset)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/export/download""","""@com.clicktable.support.controllers.InvoiceController@.getInvoicePdf"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/invoice""","""@com.clicktable.support.controllers.InvoiceController@.getInvoice()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/enum""","""@com.clicktable.support.controllers.EnumController@.getAllEnums"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/singleTax""","""@com.clicktable.support.controllers.SingleTaxController@.getSingleTaxes"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/singleTax""","""@com.clicktable.support.controllers.SingleTaxController@.addSingleTax"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/singleTax/disable/$id<[^/]+>""","""@com.clicktable.support.controllers.SingleTaxController@.disableSingleTax(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/aggregateTax""","""@com.clicktable.support.controllers.AggregateTaxController@.getAggregateTaxes"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/aggregateTax""","""@com.clicktable.support.controllers.AggregateTaxController@.addAggregateTax"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/aggregateTax/disable/$id<[^/]+>""","""@com.clicktable.support.controllers.AggregateTaxController@.disableAggregateTax(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/state""","""@com.clicktable.support.controllers.StateController@.getState"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/state""","""@com.clicktable.support.controllers.StateController@.addState"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/state/disable/$id<[^/]+>""","""@com.clicktable.support.controllers.StateController@.disableState(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/payment""","""@com.clicktable.support.controllers.PaymentController@.startPayment()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/payment/callback""","""@com.clicktable.support.controllers.PaymentController@.updatePaymentDetail()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/item""","""@com.clicktable.support.controllers.ItemController@.createItem()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/item""","""@com.clicktable.support.controllers.ItemController@.getItems()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/item/update""","""@com.clicktable.support.controllers.ItemController@.updateItem()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/plan""","""@com.clicktable.support.controllers.PlanController@.createPlan()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/plan""","""@com.clicktable.support.controllers.PlanController@.getPlans()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/plan/disable/$id<[^/]+>""","""@com.clicktable.support.controllers.PlanController@.disablePlan(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/restaurantPlan""","""@com.clicktable.support.controllers.RestaurantPlanController@.createRestaurantPlan()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/restaurantPlan""","""@com.clicktable.support.controllers.RestaurantPlanController@.getRestaurantPlan()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/restaurantPlan/plan""","""@com.clicktable.support.controllers.RestaurantPlanController@.getRestaurantPlanByPlan()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/restaurantPlan/disable/$id<[^/]+>""","""@com.clicktable.support.controllers.RestaurantPlanController@.disableRestaurantPlan(id:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/transactionHistory""","""@com.clicktable.support.controllers.TransactionHistoryController@.addTransactionHistory"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/transactionHistory""","""@com.clicktable.support.controllers.TransactionHistoryController@.getTransactionHistory"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """support/invoiceGenScheduler""","""@com.clicktable.support.controllers.SchedulerController@.runJob""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case com_clicktable_support_controllers_Application_index0_route(params) => {
   call { 
        com_clicktable_support_controllers_Application_index0_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.Application]).index())
   }
}
        

// @LINE:7
case com_clicktable_support_controllers_Application_preflight1_route(params) => {
   call(params.fromPath[String]("all", None)) { (all) =>
        com_clicktable_support_controllers_Application_preflight1_invoker.call(com.clicktable.support.controllers.Application.preflight(all))
   }
}
        

// @LINE:9
case com_clicktable_support_controllers_ConversationController_getGuestConversation2_route(params) => {
   call { 
        com_clicktable_support_controllers_ConversationController_getGuestConversation2_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).getGuestConversation)
   }
}
        

// @LINE:11
case com_clicktable_support_controllers_ConversationController_insertGuestConversation3_route(params) => {
   call { 
        com_clicktable_support_controllers_ConversationController_insertGuestConversation3_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).insertGuestConversation)
   }
}
        

// @LINE:12
case com_clicktable_support_controllers_ConversationController_insertMultipleGuestConversation4_route(params) => {
   call { 
        com_clicktable_support_controllers_ConversationController_insertMultipleGuestConversation4_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).insertMultipleGuestConversation)
   }
}
        

// @LINE:13
case com_clicktable_support_controllers_ConversationController_updateConversation5_route(params) => {
   call { 
        com_clicktable_support_controllers_ConversationController_updateConversation5_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).updateConversation)
   }
}
        

// @LINE:14
case com_clicktable_support_controllers_ConversationController_getResaturantEventPromotion6_route(params) => {
   call { 
        com_clicktable_support_controllers_ConversationController_getResaturantEventPromotion6_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).getResaturantEventPromotion)
   }
}
        

// @LINE:15
case com_clicktable_support_controllers_ConversationController_delivery7_route(params) => {
   call { 
        com_clicktable_support_controllers_ConversationController_delivery7_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).delivery)
   }
}
        

// @LINE:20
case controllers_Assets_versioned8_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned8_invoker.call(controllers.Assets.versioned(path, file))
   }
}
        

// @LINE:22
case com_clicktable_support_controllers_InvoiceController_getInvoicePdf9_route(params) => {
   call { 
        com_clicktable_support_controllers_InvoiceController_getInvoicePdf9_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.InvoiceController]).getInvoicePdf)
   }
}
        

// @LINE:23
case com_clicktable_support_controllers_InvoiceController_getInvoice10_route(params) => {
   call { 
        com_clicktable_support_controllers_InvoiceController_getInvoice10_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.InvoiceController]).getInvoice())
   }
}
        

// @LINE:25
case com_clicktable_support_controllers_EnumController_getAllEnums11_route(params) => {
   call { 
        com_clicktable_support_controllers_EnumController_getAllEnums11_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.EnumController]).getAllEnums)
   }
}
        

// @LINE:28
case com_clicktable_support_controllers_SingleTaxController_getSingleTaxes12_route(params) => {
   call { 
        com_clicktable_support_controllers_SingleTaxController_getSingleTaxes12_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).getSingleTaxes)
   }
}
        

// @LINE:29
case com_clicktable_support_controllers_SingleTaxController_addSingleTax13_route(params) => {
   call { 
        com_clicktable_support_controllers_SingleTaxController_addSingleTax13_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).addSingleTax)
   }
}
        

// @LINE:30
case com_clicktable_support_controllers_SingleTaxController_disableSingleTax14_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_clicktable_support_controllers_SingleTaxController_disableSingleTax14_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).disableSingleTax(id))
   }
}
        

// @LINE:33
case com_clicktable_support_controllers_AggregateTaxController_getAggregateTaxes15_route(params) => {
   call { 
        com_clicktable_support_controllers_AggregateTaxController_getAggregateTaxes15_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).getAggregateTaxes)
   }
}
        

// @LINE:34
case com_clicktable_support_controllers_AggregateTaxController_addAggregateTax16_route(params) => {
   call { 
        com_clicktable_support_controllers_AggregateTaxController_addAggregateTax16_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).addAggregateTax)
   }
}
        

// @LINE:35
case com_clicktable_support_controllers_AggregateTaxController_disableAggregateTax17_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_clicktable_support_controllers_AggregateTaxController_disableAggregateTax17_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).disableAggregateTax(id))
   }
}
        

// @LINE:38
case com_clicktable_support_controllers_StateController_getState18_route(params) => {
   call { 
        com_clicktable_support_controllers_StateController_getState18_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).getState)
   }
}
        

// @LINE:39
case com_clicktable_support_controllers_StateController_addState19_route(params) => {
   call { 
        com_clicktable_support_controllers_StateController_addState19_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).addState)
   }
}
        

// @LINE:40
case com_clicktable_support_controllers_StateController_disableState20_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_clicktable_support_controllers_StateController_disableState20_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).disableState(id))
   }
}
        

// @LINE:44
case com_clicktable_support_controllers_PaymentController_startPayment21_route(params) => {
   call { 
        com_clicktable_support_controllers_PaymentController_startPayment21_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PaymentController]).startPayment())
   }
}
        

// @LINE:45
case com_clicktable_support_controllers_PaymentController_updatePaymentDetail22_route(params) => {
   call { 
        com_clicktable_support_controllers_PaymentController_updatePaymentDetail22_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PaymentController]).updatePaymentDetail())
   }
}
        

// @LINE:48
case com_clicktable_support_controllers_ItemController_createItem23_route(params) => {
   call { 
        com_clicktable_support_controllers_ItemController_createItem23_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).createItem())
   }
}
        

// @LINE:49
case com_clicktable_support_controllers_ItemController_getItems24_route(params) => {
   call { 
        com_clicktable_support_controllers_ItemController_getItems24_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).getItems())
   }
}
        

// @LINE:50
case com_clicktable_support_controllers_ItemController_updateItem25_route(params) => {
   call { 
        com_clicktable_support_controllers_ItemController_updateItem25_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).updateItem())
   }
}
        

// @LINE:53
case com_clicktable_support_controllers_PlanController_createPlan26_route(params) => {
   call { 
        com_clicktable_support_controllers_PlanController_createPlan26_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).createPlan())
   }
}
        

// @LINE:54
case com_clicktable_support_controllers_PlanController_getPlans27_route(params) => {
   call { 
        com_clicktable_support_controllers_PlanController_getPlans27_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).getPlans())
   }
}
        

// @LINE:55
case com_clicktable_support_controllers_PlanController_disablePlan28_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_clicktable_support_controllers_PlanController_disablePlan28_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).disablePlan(id))
   }
}
        

// @LINE:58
case com_clicktable_support_controllers_RestaurantPlanController_createRestaurantPlan29_route(params) => {
   call { 
        com_clicktable_support_controllers_RestaurantPlanController_createRestaurantPlan29_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).createRestaurantPlan())
   }
}
        

// @LINE:59
case com_clicktable_support_controllers_RestaurantPlanController_getRestaurantPlan30_route(params) => {
   call { 
        com_clicktable_support_controllers_RestaurantPlanController_getRestaurantPlan30_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).getRestaurantPlan())
   }
}
        

// @LINE:60
case com_clicktable_support_controllers_RestaurantPlanController_getRestaurantPlanByPlan31_route(params) => {
   call { 
        com_clicktable_support_controllers_RestaurantPlanController_getRestaurantPlanByPlan31_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).getRestaurantPlanByPlan())
   }
}
        

// @LINE:61
case com_clicktable_support_controllers_RestaurantPlanController_disableRestaurantPlan32_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_clicktable_support_controllers_RestaurantPlanController_disableRestaurantPlan32_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).disableRestaurantPlan(id))
   }
}
        

// @LINE:64
case com_clicktable_support_controllers_TransactionHistoryController_addTransactionHistory33_route(params) => {
   call { 
        com_clicktable_support_controllers_TransactionHistoryController_addTransactionHistory33_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.TransactionHistoryController]).addTransactionHistory)
   }
}
        

// @LINE:65
case com_clicktable_support_controllers_TransactionHistoryController_getTransactionHistory34_route(params) => {
   call { 
        com_clicktable_support_controllers_TransactionHistoryController_getTransactionHistory34_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.TransactionHistoryController]).getTransactionHistory)
   }
}
        

// @LINE:68
case com_clicktable_support_controllers_SchedulerController_runJob35_route(params) => {
   call { 
        com_clicktable_support_controllers_SchedulerController_runJob35_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SchedulerController]).runJob)
   }
}
        
}

}
     