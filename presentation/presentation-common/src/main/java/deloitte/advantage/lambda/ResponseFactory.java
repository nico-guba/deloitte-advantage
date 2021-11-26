package deloitte.advantage.lambda;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class ResponseFactory {
    private final ObjectMapper mapper;

    public ResponseFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public APIGatewayProxyResponseEvent makeSuccessResponse(Map<String, Object> body) throws JsonProcessingException {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "Hello Happy Taxpayers!");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(headers);
        response.withStatusCode(200).withBody(mapper.writeValueAsString(body));

        return response;
    }

    public APIGatewayProxyResponseEvent makeErrorResponse(Exception e) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(headers);
        response.withBody("{ \"message\":\"" + e.getMessage() + "\"}").withStatusCode(500);
        return response;
    }
}
