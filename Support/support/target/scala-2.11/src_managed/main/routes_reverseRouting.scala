// @SOURCE:C:/Users/j.yadav/R4/clicktable-app/support/conf/routes
// @HASH:f10c5ce287f9005658cbb7af92d14dcc068095dc
// @DATE:Fri Jul 15 14:34:21 IST 2016

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:68
// @LINE:65
// @LINE:64
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:6
package com.clicktable.support.controllers {

// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
class ReverseRestaurantPlanController {


// @LINE:60
def getRestaurantPlanByPlan(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/restaurantPlan/plan")
}
                        

// @LINE:59
def getRestaurantPlan(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/restaurantPlan")
}
                        

// @LINE:58
def createRestaurantPlan(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/restaurantPlan")
}
                        

// @LINE:61
def disableRestaurantPlan(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "support/restaurantPlan/disable/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

}
                          

// @LINE:30
// @LINE:29
// @LINE:28
class ReverseSingleTaxController {


// @LINE:29
def addSingleTax(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/singleTax")
}
                        

// @LINE:28
def getSingleTaxes(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/singleTax")
}
                        

// @LINE:30
def disableSingleTax(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "support/singleTax/disable/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

}
                          

// @LINE:23
// @LINE:22
class ReverseInvoiceController {


// @LINE:23
def getInvoice(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/invoice")
}
                        

// @LINE:22
def getInvoicePdf(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/export/download")
}
                        

}
                          

// @LINE:45
// @LINE:44
class ReversePaymentController {


// @LINE:44
def startPayment(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/payment")
}
                        

// @LINE:45
def updatePaymentDetail(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/payment/callback")
}
                        

}
                          

// @LINE:35
// @LINE:34
// @LINE:33
class ReverseAggregateTaxController {


// @LINE:33
def getAggregateTaxes(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/aggregateTax")
}
                        

// @LINE:34
def addAggregateTax(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/aggregateTax")
}
                        

// @LINE:35
def disableAggregateTax(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "support/aggregateTax/disable/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

}
                          

// @LINE:65
// @LINE:64
class ReverseTransactionHistoryController {


// @LINE:65
def getTransactionHistory(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/transactionHistory")
}
                        

// @LINE:64
def addTransactionHistory(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/transactionHistory")
}
                        

}
                          

// @LINE:68
class ReverseSchedulerController {


// @LINE:68
def runJob(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/invoiceGenScheduler")
}
                        

}
                          

// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:7
def preflight(all:String): Call = {
   import ReverseRouteContext.empty
   Call("OPTIONS", _prefix + { _defaultPrefix } + implicitly[PathBindable[String]].unbind("all", all))
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          

// @LINE:50
// @LINE:49
// @LINE:48
class ReverseItemController {


// @LINE:50
def updateItem(): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "support/item/update")
}
                        

// @LINE:49
def getItems(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/item")
}
                        

// @LINE:48
def createItem(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/item")
}
                        

}
                          

// @LINE:40
// @LINE:39
// @LINE:38
class ReverseStateController {


// @LINE:40
def disableState(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "support/state/disable/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:39
def addState(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/state")
}
                        

// @LINE:38
def getState(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/state")
}
                        

}
                          

// @LINE:25
class ReverseEnumController {


// @LINE:25
def getAllEnums(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/enum")
}
                        

}
                          

// @LINE:55
// @LINE:54
// @LINE:53
class ReversePlanController {


// @LINE:53
def createPlan(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "support/plan")
}
                        

// @LINE:54
def getPlans(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "support/plan")
}
                        

// @LINE:55
def disablePlan(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "support/plan/disable/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

}
                          

// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
class ReverseConversationController {


// @LINE:9
def getGuestConversation(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "conversation")
}
                        

// @LINE:12
def insertMultipleGuestConversation(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "conversation/multiple")
}
                        

// @LINE:11
def insertGuestConversation(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "conversation")
}
                        

// @LINE:13
def updateConversation(): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "conversation")
}
                        

// @LINE:14
def getResaturantEventPromotion(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "report/promotion")
}
                        

// @LINE:15
def delivery(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "conversation/delivery")
}
                        

}
                          
}
                  

// @LINE:20
package controllers {

// @LINE:20
class ReverseAssets {


// @LINE:20
def versioned(file:Asset): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[Asset]].unbind("file", file))
}
                        

}
                          
}
                  


// @LINE:68
// @LINE:65
// @LINE:64
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:6
package com.clicktable.support.controllers.javascript {
import ReverseRouteContext.empty

// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
class ReverseRestaurantPlanController {


// @LINE:60
def getRestaurantPlanByPlan : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.RestaurantPlanController.getRestaurantPlanByPlan",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/restaurantPlan/plan"})
      }
   """
)
                        

// @LINE:59
def getRestaurantPlan : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.RestaurantPlanController.getRestaurantPlan",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/restaurantPlan"})
      }
   """
)
                        

// @LINE:58
def createRestaurantPlan : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.RestaurantPlanController.createRestaurantPlan",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/restaurantPlan"})
      }
   """
)
                        

// @LINE:61
def disableRestaurantPlan : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.RestaurantPlanController.disableRestaurantPlan",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "support/restaurantPlan/disable/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

}
              

// @LINE:30
// @LINE:29
// @LINE:28
class ReverseSingleTaxController {


// @LINE:29
def addSingleTax : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.SingleTaxController.addSingleTax",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/singleTax"})
      }
   """
)
                        

// @LINE:28
def getSingleTaxes : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.SingleTaxController.getSingleTaxes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/singleTax"})
      }
   """
)
                        

// @LINE:30
def disableSingleTax : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.SingleTaxController.disableSingleTax",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "support/singleTax/disable/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

}
              

// @LINE:23
// @LINE:22
class ReverseInvoiceController {


// @LINE:23
def getInvoice : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.InvoiceController.getInvoice",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/invoice"})
      }
   """
)
                        

// @LINE:22
def getInvoicePdf : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.InvoiceController.getInvoicePdf",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/export/download"})
      }
   """
)
                        

}
              

// @LINE:45
// @LINE:44
class ReversePaymentController {


// @LINE:44
def startPayment : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.PaymentController.startPayment",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/payment"})
      }
   """
)
                        

// @LINE:45
def updatePaymentDetail : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.PaymentController.updatePaymentDetail",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/payment/callback"})
      }
   """
)
                        

}
              

// @LINE:35
// @LINE:34
// @LINE:33
class ReverseAggregateTaxController {


// @LINE:33
def getAggregateTaxes : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.AggregateTaxController.getAggregateTaxes",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/aggregateTax"})
      }
   """
)
                        

// @LINE:34
def addAggregateTax : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.AggregateTaxController.addAggregateTax",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/aggregateTax"})
      }
   """
)
                        

// @LINE:35
def disableAggregateTax : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.AggregateTaxController.disableAggregateTax",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "support/aggregateTax/disable/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

}
              

// @LINE:65
// @LINE:64
class ReverseTransactionHistoryController {


// @LINE:65
def getTransactionHistory : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.TransactionHistoryController.getTransactionHistory",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/transactionHistory"})
      }
   """
)
                        

// @LINE:64
def addTransactionHistory : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.TransactionHistoryController.addTransactionHistory",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/transactionHistory"})
      }
   """
)
                        

}
              

// @LINE:68
class ReverseSchedulerController {


// @LINE:68
def runJob : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.SchedulerController.runJob",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/invoiceGenScheduler"})
      }
   """
)
                        

}
              

// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:7
def preflight : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.Application.preflight",
   """
      function(all) {
      return _wA({method:"OPTIONS", url:"""" + _prefix + { _defaultPrefix } + """" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("all", all)})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              

// @LINE:50
// @LINE:49
// @LINE:48
class ReverseItemController {


// @LINE:50
def updateItem : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ItemController.updateItem",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "support/item/update"})
      }
   """
)
                        

// @LINE:49
def getItems : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ItemController.getItems",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/item"})
      }
   """
)
                        

// @LINE:48
def createItem : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ItemController.createItem",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/item"})
      }
   """
)
                        

}
              

// @LINE:40
// @LINE:39
// @LINE:38
class ReverseStateController {


// @LINE:40
def disableState : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.StateController.disableState",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "support/state/disable/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:39
def addState : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.StateController.addState",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/state"})
      }
   """
)
                        

// @LINE:38
def getState : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.StateController.getState",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/state"})
      }
   """
)
                        

}
              

// @LINE:25
class ReverseEnumController {


// @LINE:25
def getAllEnums : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.EnumController.getAllEnums",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/enum"})
      }
   """
)
                        

}
              

// @LINE:55
// @LINE:54
// @LINE:53
class ReversePlanController {


// @LINE:53
def createPlan : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.PlanController.createPlan",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "support/plan"})
      }
   """
)
                        

// @LINE:54
def getPlans : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.PlanController.getPlans",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "support/plan"})
      }
   """
)
                        

// @LINE:55
def disablePlan : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.PlanController.disablePlan",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "support/plan/disable/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

}
              

// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
class ReverseConversationController {


// @LINE:9
def getGuestConversation : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ConversationController.getGuestConversation",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conversation"})
      }
   """
)
                        

// @LINE:12
def insertMultipleGuestConversation : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ConversationController.insertMultipleGuestConversation",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "conversation/multiple"})
      }
   """
)
                        

// @LINE:11
def insertGuestConversation : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ConversationController.insertGuestConversation",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "conversation"})
      }
   """
)
                        

// @LINE:13
def updateConversation : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ConversationController.updateConversation",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "conversation"})
      }
   """
)
                        

// @LINE:14
def getResaturantEventPromotion : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ConversationController.getResaturantEventPromotion",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "report/promotion"})
      }
   """
)
                        

// @LINE:15
def delivery : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.clicktable.support.controllers.ConversationController.delivery",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "conversation/delivery"})
      }
   """
)
                        

}
              
}
        

// @LINE:20
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:20
class ReverseAssets {


// @LINE:20
def versioned : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.versioned",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              
}
        


// @LINE:68
// @LINE:65
// @LINE:64
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:50
// @LINE:49
// @LINE:48
// @LINE:45
// @LINE:44
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:30
// @LINE:29
// @LINE:28
// @LINE:25
// @LINE:23
// @LINE:22
// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:7
// @LINE:6
package com.clicktable.support.controllers.ref {


// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:58
class ReverseRestaurantPlanController {


// @LINE:60
def getRestaurantPlanByPlan(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).getRestaurantPlanByPlan(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.RestaurantPlanController", "getRestaurantPlanByPlan", Seq(), "GET", """""", _prefix + """support/restaurantPlan/plan""")
)
                      

// @LINE:59
def getRestaurantPlan(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).getRestaurantPlan(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.RestaurantPlanController", "getRestaurantPlan", Seq(), "GET", """""", _prefix + """support/restaurantPlan""")
)
                      

// @LINE:58
def createRestaurantPlan(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).createRestaurantPlan(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.RestaurantPlanController", "createRestaurantPlan", Seq(), "POST", """restaurant plan""", _prefix + """support/restaurantPlan""")
)
                      

// @LINE:61
def disableRestaurantPlan(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.RestaurantPlanController]).disableRestaurantPlan(id), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.RestaurantPlanController", "disableRestaurantPlan", Seq(classOf[Integer]), "PUT", """""", _prefix + """support/restaurantPlan/disable/$id<[^/]+>""")
)
                      

}
                          

// @LINE:30
// @LINE:29
// @LINE:28
class ReverseSingleTaxController {


// @LINE:29
def addSingleTax(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).addSingleTax(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.SingleTaxController", "addSingleTax", Seq(), "POST", """""", _prefix + """support/singleTax""")
)
                      

// @LINE:28
def getSingleTaxes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).getSingleTaxes(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.SingleTaxController", "getSingleTaxes", Seq(), "GET", """Single tax""", _prefix + """support/singleTax""")
)
                      

// @LINE:30
def disableSingleTax(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SingleTaxController]).disableSingleTax(id), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.SingleTaxController", "disableSingleTax", Seq(classOf[Integer]), "PUT", """""", _prefix + """support/singleTax/disable/$id<[^/]+>""")
)
                      

}
                          

// @LINE:23
// @LINE:22
class ReverseInvoiceController {


// @LINE:23
def getInvoice(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.InvoiceController]).getInvoice(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.InvoiceController", "getInvoice", Seq(), "GET", """""", _prefix + """support/invoice""")
)
                      

// @LINE:22
def getInvoicePdf(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.InvoiceController]).getInvoicePdf(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.InvoiceController", "getInvoicePdf", Seq(), "GET", """""", _prefix + """support/export/download""")
)
                      

}
                          

// @LINE:45
// @LINE:44
class ReversePaymentController {


// @LINE:44
def startPayment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PaymentController]).startPayment(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PaymentController", "startPayment", Seq(), "POST", """payment""", _prefix + """support/payment""")
)
                      

// @LINE:45
def updatePaymentDetail(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PaymentController]).updatePaymentDetail(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PaymentController", "updatePaymentDetail", Seq(), "POST", """""", _prefix + """support/payment/callback""")
)
                      

}
                          

// @LINE:35
// @LINE:34
// @LINE:33
class ReverseAggregateTaxController {


// @LINE:33
def getAggregateTaxes(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).getAggregateTaxes(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.AggregateTaxController", "getAggregateTaxes", Seq(), "GET", """Aggregate Tax""", _prefix + """support/aggregateTax""")
)
                      

// @LINE:34
def addAggregateTax(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).addAggregateTax(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.AggregateTaxController", "addAggregateTax", Seq(), "POST", """""", _prefix + """support/aggregateTax""")
)
                      

// @LINE:35
def disableAggregateTax(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.AggregateTaxController]).disableAggregateTax(id), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.AggregateTaxController", "disableAggregateTax", Seq(classOf[Integer]), "PUT", """""", _prefix + """support/aggregateTax/disable/$id<[^/]+>""")
)
                      

}
                          

// @LINE:65
// @LINE:64
class ReverseTransactionHistoryController {


// @LINE:65
def getTransactionHistory(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.TransactionHistoryController]).getTransactionHistory(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.TransactionHistoryController", "getTransactionHistory", Seq(), "GET", """""", _prefix + """support/transactionHistory""")
)
                      

// @LINE:64
def addTransactionHistory(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.TransactionHistoryController]).addTransactionHistory(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.TransactionHistoryController", "addTransactionHistory", Seq(), "POST", """Transaction History""", _prefix + """support/transactionHistory""")
)
                      

}
                          

// @LINE:68
class ReverseSchedulerController {


// @LINE:68
def runJob(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.SchedulerController]).runJob(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.SchedulerController", "runJob", Seq(), "GET", """Run Invoice Generation Scheduler""", _prefix + """support/invoiceGenScheduler""")
)
                      

}
                          

// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:7
def preflight(all:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   com.clicktable.support.controllers.Application.preflight(all), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.Application", "preflight", Seq(classOf[String]), "OPTIONS", """""", _prefix + """$all<.+>""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.Application]).index(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

}
                          

// @LINE:50
// @LINE:49
// @LINE:48
class ReverseItemController {


// @LINE:50
def updateItem(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).updateItem(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ItemController", "updateItem", Seq(), "PUT", """""", _prefix + """support/item/update""")
)
                      

// @LINE:49
def getItems(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).getItems(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ItemController", "getItems", Seq(), "GET", """""", _prefix + """support/item""")
)
                      

// @LINE:48
def createItem(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ItemController]).createItem(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ItemController", "createItem", Seq(), "POST", """item""", _prefix + """support/item""")
)
                      

}
                          

// @LINE:40
// @LINE:39
// @LINE:38
class ReverseStateController {


// @LINE:40
def disableState(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).disableState(id), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.StateController", "disableState", Seq(classOf[Integer]), "PUT", """""", _prefix + """support/state/disable/$id<[^/]+>""")
)
                      

// @LINE:39
def addState(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).addState(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.StateController", "addState", Seq(), "POST", """""", _prefix + """support/state""")
)
                      

// @LINE:38
def getState(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.StateController]).getState(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.StateController", "getState", Seq(), "GET", """state""", _prefix + """support/state""")
)
                      

}
                          

// @LINE:25
class ReverseEnumController {


// @LINE:25
def getAllEnums(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.EnumController]).getAllEnums(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.EnumController", "getAllEnums", Seq(), "GET", """GET    /delivery               		    @com.clicktable.support.controllers.ConversationController.delivery""", _prefix + """support/enum""")
)
                      

}
                          

// @LINE:55
// @LINE:54
// @LINE:53
class ReversePlanController {


// @LINE:53
def createPlan(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).createPlan(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PlanController", "createPlan", Seq(), "POST", """plan""", _prefix + """support/plan""")
)
                      

// @LINE:54
def getPlans(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).getPlans(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PlanController", "getPlans", Seq(), "GET", """""", _prefix + """support/plan""")
)
                      

// @LINE:55
def disablePlan(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.PlanController]).disablePlan(id), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.PlanController", "disablePlan", Seq(classOf[Integer]), "PUT", """""", _prefix + """support/plan/disable/$id<[^/]+>""")
)
                      

}
                          

// @LINE:15
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
class ReverseConversationController {


// @LINE:9
def getGuestConversation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).getGuestConversation(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "getGuestConversation", Seq(), "GET", """""", _prefix + """conversation""")
)
                      

// @LINE:12
def insertMultipleGuestConversation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).insertMultipleGuestConversation(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "insertMultipleGuestConversation", Seq(), "POST", """""", _prefix + """conversation/multiple""")
)
                      

// @LINE:11
def insertGuestConversation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).insertGuestConversation(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "insertGuestConversation", Seq(), "POST", """GET    /conversation/history    @com.clicktable.support.controllers.ConversationController.getGuestConversationCount""", _prefix + """conversation""")
)
                      

// @LINE:13
def updateConversation(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).updateConversation(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "updateConversation", Seq(), "PUT", """""", _prefix + """conversation""")
)
                      

// @LINE:14
def getResaturantEventPromotion(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).getResaturantEventPromotion(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "getResaturantEventPromotion", Seq(), "GET", """""", _prefix + """report/promotion""")
)
                      

// @LINE:15
def delivery(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.clicktable.support.controllers.ConversationController]).delivery(), HandlerDef(this.getClass.getClassLoader, "", "com.clicktable.support.controllers.ConversationController", "delivery", Seq(), "GET", """""", _prefix + """conversation/delivery""")
)
                      

}
                          
}
        

// @LINE:20
package controllers.ref {


// @LINE:20
class ReverseAssets {


// @LINE:20
def versioned(path:String, file:Asset): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.versioned(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "versioned", Seq(classOf[String], classOf[Asset]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          
}
        
    