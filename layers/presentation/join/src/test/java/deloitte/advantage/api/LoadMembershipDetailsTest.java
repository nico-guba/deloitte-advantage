package deloitte.advantage.api;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import deloitte.advantage.lambda.TestContext;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LoadMembershipDetailsTest {

    APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent().withHttpMethod("GET");

    TestContext context = new TestContext();

    Join handler = new Join();

    @Test
    void loadMembershipDetails() {
        Map<String, String> params = new HashMap<>();
        params.put("subscription_id", "social");
    }
}
