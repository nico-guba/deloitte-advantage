package deloitte.advantage.lambda;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.type.TypeReference;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import java.util.HashMap;
import java.util.Map;

public class RequestFactory {
    private final ObjectMapper mapper;
    private final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
    };

    public RequestFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Map<String, Object> readBody(String body) throws JsonProcessingException {
        return mapper.readValue(body, typeRef);
    }
}
