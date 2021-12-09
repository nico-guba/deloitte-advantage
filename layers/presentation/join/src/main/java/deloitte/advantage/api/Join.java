package deloitte.advantage.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Join implements RequestHandler<Integer, String> {
    @Override
    public String handleRequest(final Integer input, final Context context) {
        return "Received " + input;
    }
}
