package deloitte.advantage.api;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import deloitte.advantage.lambda.TestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class JoinTest {

    APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent().withHttpMethod("POST");

    TestContext context = new TestContext();

    Join handler = new Join();

    static Set<String> validMethods = new HashSet<>();
    static Set<String> allMethods = new HashSet<>();

    @BeforeAll
    static void beforeAll() {
        // could move into a factory
        allMethods.add("GET");
        allMethods.add("POST");
        allMethods.add("PUT");
        allMethods.add("DELETE");
        allMethods.add("PATH");
        allMethods.add("OPTIONS");
        allMethods.add("HEAD");
        allMethods = Collections.unmodifiableSet(allMethods);

        validMethods.add("POST");
        validMethods = Collections.unmodifiableSet(validMethods);
    }

    // candidate for test utility
    @Test
    void onlyHandleValidHttpMethods() {

        for (String method : allMethods) {
            // ensure the http method is set in the request, everything else should be null in the event
            // to test that the handler can terminate in a known state
            APIGatewayProxyResponseEvent response = handler.handleRequest(event.clone().withHttpMethod(method), context);
            Integer statusCode = Integer.valueOf(response.getStatusCode());
            if (validMethods.contains(method)) {
                assertThat(statusCode).isGreaterThanOrEqualTo(200).isLessThan(400);
            } else {
                assertThat(statusCode).isGreaterThanOrEqualTo(400);
            }
        }
    }



}