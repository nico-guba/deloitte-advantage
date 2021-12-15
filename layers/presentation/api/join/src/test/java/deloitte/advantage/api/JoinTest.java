package deloitte.advantage.api;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import deloitte.advantage.lambda.TestContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        Map<String, String> params = new HashMap<>();
        params.put("subscription_id", "social");

        for (String method : allMethods) {
            // ensure the http method tested is set in the request
            APIGatewayProxyResponseEvent response =
                    handler.handleRequest(event.withHttpMethod(method).withPathParameters(params), context);
            if (validMethods.contains(method)) {
                assertThat(getStatusCode(response)).isGreaterThanOrEqualTo(200).isLessThan(400);
            } else {
                assertThat(getStatusCode(response)).isGreaterThanOrEqualTo(400);
            }
        }
    }

    private Integer getStatusCode(final APIGatewayProxyResponseEvent response) {
        return Integer.valueOf(response.getStatusCode());
    }

    @Test
    void failsWithNullSubscriptionId() {
        APIGatewayProxyResponseEvent response = handler.handleRequest(event, context);

        assertThat(getStatusCode(response)).isEqualTo(400);
    }

}