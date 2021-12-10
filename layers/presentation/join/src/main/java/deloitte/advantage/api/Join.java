package deloitte.advantage.api;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import deloitte.advantage.lambda.EventFactory;

public class Join implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    EventFactory factory = new EventFactory(new ObjectMapper());

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        try {
            context.getLogger().log("METHOD: " + input.getHttpMethod());
            final String message = "Received subscription for id=" + getSubscriptionId(input);

            switch (input.getHttpMethod()) {
                case "POST":
                    return factory.makeSuccessResponse(message);
                default:
                    context.getLogger().log("METHOD NOT HANDLED: " + input.getHttpMethod());
                    return factory.makeErrorResponse("Unexpected method: " + input.getHttpMethod());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return factory.makeErrorResponse(e);
        }
    }

    private String getSubscriptionId(final APIGatewayProxyRequestEvent input) {
        if(input != null && input.getPathParameters() != null) {
            return input.getPathParameters().get("subscription_id");
        }
        return null;
    }
}
