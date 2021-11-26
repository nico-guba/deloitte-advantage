package deloitte.advantage.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestGatewayTest {
    private final RestGateway api = new RestGateway();

    @Test
    void handlePost() {
        APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
        event.setHttpMethod("POST");

        api.handleRequest(event, null);
    }
}