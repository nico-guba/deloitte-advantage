package deloitte.advantage.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import javax.print.attribute.standard.MediaSize;

public class RestGateway implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private OtherService service = new OtherService();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        switch(input.getHttpMethod()) {
            case "POST":
                service.onPost(Integer.valueOf(3));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + input.getHttpMethod());
        }
        return null;
    }


}
