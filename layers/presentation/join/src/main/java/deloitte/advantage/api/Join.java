package deloitte.advantage.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;

import java.util.Map;

public class Join implements RequestHandler<APIGatewayV2HTTPEvent, Map<String, String>> {

    @Override
    public Map<String, String> handleRequest(final APIGatewayV2HTTPEvent input, final Context context) {
        return input.getPathParameters();
    }
}
