package helloworld;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import deloitte.advantage.lambda.EventFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The intelliJ template for an AWS project isn't great.  This does not test what is on the server,
 * but it illustrates on how one can test events coming from other AWS services.  It's left in here
 * for illustration purposes.
 */
class AppTest {
    static final App app = new App();


    final APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();

    EventFactory requestFactory = new EventFactory(new ObjectMapper());

    @Test
    void completesSuccessfully() {
        APIGatewayProxyResponseEvent result = executeHandler();

        Assertions.assertEquals(200, result.getStatusCode());
    }

    private APIGatewayProxyResponseEvent executeHandler() {
        event.setBody("{\"key\":\"value\"}");
        return app.handleRequest(event, null);
    }

    @Test
    void hasCorrectContentType() {
        APIGatewayProxyResponseEvent result = executeHandler();

        Assertions.assertEquals("application/json", result.getHeaders().get("Content-Type"));
    }
}
