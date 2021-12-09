package deloitte.advantage.lambda;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.type.TypeReference;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class EventFactory {
    private final ObjectMapper mapper;
    private final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
    };

    public EventFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Map<String, Object> readBody(String body) throws JsonProcessingException {
        return mapper.readValue(body, typeRef);
    }

    public Map<String, Object> readBody(APIGatewayProxyRequestEvent event) throws JsonProcessingException {
        return readBody(event.getBody());
    }

    public APIGatewayProxyResponseEvent makeSuccessResponse(Map<String, Object> body) throws JsonProcessingException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return new APIGatewayProxyResponseEvent().withHeaders(headers).withStatusCode(200)
                .withBody(mapper.writeValueAsString(body));
    }

    public APIGatewayProxyResponseEvent makeSuccessResponse(Object content) throws JsonProcessingException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return new APIGatewayProxyResponseEvent().withHeaders(headers).withStatusCode(200)
                .withBody(mapper.writeValueAsString(content));
    }

    public APIGatewayProxyResponseEvent makeErrorResponse(Exception e) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
       return new APIGatewayProxyResponseEvent().withHeaders(headers)
               .withBody("{ \"message\":\"" + e.getMessage() + "\"}").withStatusCode(500);
    }
}
