package deloitte.advantage.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestGatewayTest {
    private final RestGateway api = new RestGateway();

    APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();

    TestContext context = new TestContext();

    @BeforeEach
    void setUp() {
        event.setHttpMethod("POST");
    }

    @Test
    void handlePost() {
        event.setBody("{ \"id\": 554433}");
        assertStatus(200);
    }

    @Test
    void handlePostError() {
        event.setBody("{ \"idx\": 554433}");
        assertStatus(500);
    }

    @Test
    void emptyPostError() {
        event.setBody("{}");
        assertStatus(500);
    }

    private void assertStatus(int expected) {
        APIGatewayProxyResponseEvent response = api.handleRequest(event, context);
        Assertions.assertEquals(expected, response.getStatusCode());
    }
}