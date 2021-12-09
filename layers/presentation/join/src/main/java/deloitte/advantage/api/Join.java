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
           // context.getLogger().log(input.getBody());
            switch (input.getHttpMethod()) {
                case "POST":
                    return factory.makeSuccessResponse(input);
                default:
                    throw new IllegalStateException("Unexpected value: " + input.getHttpMethod());
            }
        } catch (Exception e) {
            return factory.makeErrorResponse(e);
        }
    }
}
