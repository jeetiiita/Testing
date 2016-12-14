import static com.clicktable.util.ResponseCodes.INTERNAL_SERVER_ERROR;
import static play.mvc.Results.internalServerError;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import play.Application;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.Scala;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import scala.Tuple2;
import scala.collection.Seq;

import com.clicktable.response.BaseResponse;
import com.clicktable.response.ErrorResponse;
import com.clicktable.support.service.intf.ThreadSchedulerService;
import com.clicktable.support.config.SpringConfiguration;
import com.clicktable.validate.ValidationError;


/**
 */
public class SupportGlobal extends GlobalSettings {
//private static final Logger.ALogger log = Logger.of(SupportGlobal.class);
    /**
     * Field ctx.
     */
    private ApplicationContext ctx;

    /**
     * Method onStart.
     *
     * @param app Application
     */
    @Override
    public void onStart(Application app) {
        ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ThreadSchedulerService scheduler = ctx.getBean(ThreadSchedulerService.class);
        scheduler.startThreads();

    }

    /**
     * Method getControllerInstance.
     *
     * @param clazz Class<A>
     * @return A
     */
    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        A result;
        result = ctx.getBean(clazz);
        return result;
    }

    /**
     * Method onStop.
     *
     * @param app Application
     */
    @Override
    public void onStop(Application app) {
        if (ctx != null)
            ((AnnotationConfigApplicationContext) ctx).close();
    }

    @Override
    public Action onRequest(Request arg0, Method arg1) {

        return new ActionWrapper(super.onRequest(arg0, arg1));
    }

    public Promise<Result> onError(RequestHeader request, Throwable t) {
        List<ValidationError> errorList = new ArrayList<ValidationError>();
        errorList.add(new ValidationError(null, Json.toJson(t).get("cause").get("localizedMessage")));
        BaseResponse response = new ErrorResponse(INTERNAL_SERVER_ERROR, errorList);
        return Promise.<Result>pure(new CORSResult(internalServerError(Json.toJson(response))));
        // return
        // Promise.<Result>pure(internalServerError(Json.toJson(response)));
    }

    private static class CORSResult implements Result {
        final private play.api.mvc.Result wrappedResult;
        Http.Request req = Http.Context.current().request();

        // String origin=null;

        public CORSResult(play.mvc.Results.Status status) {
            List<Tuple2<String, String>> list = new ArrayList<Tuple2<String, String>>();
            // log.info("-----------------HEADER========="+(null==req.getHeader("Origin"))?"*":req.getHeader("Origin").toString());
            // Tuple2<String, String> t = new Tuple2<String,
            // String>("Access-Control-Allow-Origin", "*");

            Tuple2<String, String> t = new Tuple2<String, String>("Access-Control-Allow-Origin", (null == req.getHeader("Origin")) ? "*" : String.valueOf(req.getHeader("Origin")));
            Tuple2<String, String> t1 = new Tuple2<String, String>("Access-Control-Allow-Credentials", "true");

            list.add(t);
            list.add(t1);
            Seq<Tuple2<String, String>> seq = Scala.toSeq(list);
            wrappedResult = status.toScala().withHeaders(seq);
        }

        public play.api.mvc.Result toScala() {
            return this.wrappedResult;
        }
    }


    private class ActionWrapper extends Action.Simple {
        public ActionWrapper(Action<?> action) {
            this.delegate = action;
        }

        @Override
        public Promise<Result> call(Http.Context ctx) throws java.lang.Throwable {
            Promise<Result> result = this.delegate.call(ctx);
            Http.Response response = ctx.response();
            response.setHeader("Access-Control-Allow-Origin", "*");
            //response.setHeader("Access-Control-Allow-Origin", (null == ctx.request().getHeader("Origin")) ? "*" : ctx.request().getHeader("Origin").toString());
            //response.setHeader("Access-Control-Allow-Credentials", "true");
            return result;

        }
    }
}
