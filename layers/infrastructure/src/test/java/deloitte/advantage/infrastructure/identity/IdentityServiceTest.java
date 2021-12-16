package deloitte.advantage.infrastructure.identity;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

class IdentityServiceTest {

    @Test
    void addUserToGroup() {
        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.EU_WEST_2).credentialsProvider(getCredentialsProvider())
                .build();

        listAllUserPools(cognitoClient);
    }

    @Test
    void createUser() {
        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.EU_WEST_2).credentialsProvider(getCredentialsProvider())
                .build();

        createNewUser(cognitoClient, "eu-west-2_wxRoGNwQ0", "nicolai@btinternet.com");
    }

    private AwsCredentialsProvider getCredentialsProvider() {
        System.out.println( System.getProperties());
        // we need to use a different credentials provider when we run in pipelines as opposed to ide
        if (System.getProperty("secret.AWS_ACCESS_KEY_ID") == null) {
            return ProfileCredentialsProvider.create("default");
        }
        // configure AWS access via environment variables from GitHub actions.
        System.setProperty("AWS_ACCESS_KEY_ID", System.getProperty("secret.AWS_ACCESS_KEY_ID"));
        System.setProperty("AWS_SECRET_ACCESS_KEY", System.getProperty("secret.AWS_SECRET_ACCESS_KEY"));
        return EnvironmentVariableCredentialsProvider.create();
    }

    public static String createPool(CognitoIdentityProviderClient cognitoClient, String userPoolName) {

        try {
            CreateUserPoolResponse response = cognitoClient.createUserPool(
                    CreateUserPoolRequest.builder()
                            .poolName(userPoolName)
                            .build()
            );
            return response.userPool().id();

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1); // not sure about this in the example...
        }
        return "";
    }

    public static void createNewUser(CognitoIdentityProviderClient cognitoClient,
                                     String userPoolId,
                                     String email){

        try{

            AttributeType userAttrs = AttributeType.builder()
                    .name("email")
                    .value(email)
                    .build();

            AdminCreateUserRequest userRequest = AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(email)
                    .userAttributes(userAttrs)
                    .build() ;

            AdminCreateUserResponse response = cognitoClient.adminCreateUser(userRequest);
            System.out.println("User " + response.user().username() + "is created. Status: " + response.user().userStatus());

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    public static void listAllUserPools(CognitoIdentityProviderClient cognitoClient) {

        try {
            ListUserPoolsRequest request = ListUserPoolsRequest.builder()
                    .maxResults(10)
                    .build();

            ListUserPoolsResponse response = cognitoClient.listUserPools(request);
            response.userPools().forEach(userpool -> {
                        System.out.println("User pool " + userpool.name() + ", User ID " + userpool.id());
                    }
            );

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}