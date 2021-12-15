package deloitte.advantage.lambda;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TestContext implements Context, LambdaLogger {

    private final String functionName = "test-function";

    private static final Logger logger = LoggerFactory.getLogger(TestContext.class);

    public String getAwsRequestId() {
        return UUID.randomUUID().toString();
    }

    public String getLogGroupName() {
        return new String("/aws/lambda/" + getFunctionName());
    }

    public String getLogStreamName() {
        return new String("2020/08/25/[" + getFunctionVersion() + "]704f8dxmpla04097b9134246b8438f1a");
    }

    public String getFunctionName() {
        return new String(functionName);
    }

    public String getFunctionVersion() {
        return new String("$LATEST");
    }

    public String getInvokedFunctionArn() {
        return new String("arn:aws:lambda:eu-west-2:123456789012:function:" + getFunctionName());
    }

    public CognitoIdentity getIdentity() {
        return null;
    }

    public ClientContext getClientContext() {
        return null;
    }

    public int getRemainingTimeInMillis() {
        return 300000;
    }

    public int getMemoryLimitInMB() {
        return 500;
    }

    public LambdaLogger getLogger() {
        return this;
    }

    @Override
    public void log(String message) {
        logger.info(message);
    }

    @Override
    public void log(byte[] message) {
        logger.info(new String(message));
    }
}
