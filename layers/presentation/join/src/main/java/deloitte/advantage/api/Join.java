package deloitte.advantage.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class Join implements RequestHandler<Map<String, String>, String> {
    @Override
    public String handleRequest(final Map<String, String> input, final Context context) {
        return "Received " + input;
    }
}
