package deloitte.advantage.lambda;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestContextTest {

    private TestContext context = new TestContext();

    @Test
    void getAwsRequestId_Different_After_Every_Invocation() {
        Set<String> results = new HashSet<>();
        int expected = 100;
        for(int i = 0; i < 100; i++) {
            results.add(context.getAwsRequestId());
        }
        assertEquals(expected, results.size());
    }

    @Test
    void getLogGroupName() {
        assertEquals("/aws/lambda/test-function", context.getLogGroupName());
    }

    @Test
    void getLogStreamName() {
        assertEquals("2020/08/25/[$LATEST]704f8dxmpla04097b9134246b8438f1a", context.getLogStreamName());
    }

    @Test
    void getFunctionName() {
        assertEquals("test-function", context.getFunctionName());
    }

    @Test
    void getFunctionVersion() {
        assertEquals("$LATEST", context.getFunctionVersion());
    }

    @Test
    void getInvokedFunctionArn() {
        assertEquals("arn:aws:lambda:eu-west-2:123456789012:function:test-function", context.getInvokedFunctionArn());
    }

    @Test
    void getIdentity() {
        assertNull(context.getIdentity());
    }

    @Test
    void getClientContext() {
        assertNull(context.getClientContext());
    }

    @Test
    void getRemainingTimeInMillis() {
        assertEquals(300000, context.getRemainingTimeInMillis());
    }

    @Test
    void getMemoryLimitInMB() {
        assertEquals(500, context.getMemoryLimitInMB());
    }

    @Test
    void getLogger() {
        assertEquals(TestContext.class, context.getLogger().getClass());
    }
}