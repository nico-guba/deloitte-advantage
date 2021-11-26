package helloworld;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import deloitte.advantage.lambda.RequestFactory;
import deloitte.advantage.lambda.ResponseFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final RequestFactory requestFactory = new RequestFactory(mapper);
    private final ResponseFactory responseFactory = new ResponseFactory(mapper);

    private String getPageLocation() throws IOException {
        URL url = new URL("https://checkip.amazonaws.com");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        System.out.println(event);

        try {
            Map<String, Object> body = requestFactory.readBody(event.getBody());
            body.put("location", this.getPageLocation());
            return responseFactory.makeSuccessResponse(body);
        } catch (IOException e) {
            return responseFactory.makeErrorResponse(e);
        }
    }
}
