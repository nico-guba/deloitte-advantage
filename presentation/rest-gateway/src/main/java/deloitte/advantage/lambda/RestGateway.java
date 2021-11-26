package deloitte.advantage.lambda;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.Map;

public class RestGateway implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private OtherService service = new OtherService();

    private final ObjectMapper mapper = new ObjectMapper();
    private RequestFactory factory = new RequestFactory(mapper);
    private ResponseFactory responseFactory = new ResponseFactory(mapper);

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        try {
            System.out.println(input.getBody());
            Map<String, Object> body = factory.readBody(input.getBody());
            switch (input.getHttpMethod()) {
                case "POST":
                    service.onPost(Integer.valueOf((Integer) body.get("id")));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + input.getHttpMethod());
            }
            return responseFactory.makeSuccessResponse(body);
        } catch (Exception e) {
            return responseFactory.makeErrorResponse(e);
        }
    }


}
