package deloitte.advantage.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UsernameExistsException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CognitoConnectorTest {

    private static final String POOL_ID = "eu-west-2_wxRoGNwQ0";

    private CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
            .region(Region.EU_WEST_2).credentialsProvider(getCredentialsProvider())
            .build();

    private AwsCredentialsProvider getCredentialsProvider() {
        if (System.getenv("AWS_ACCESS_KEY_ID") == null) {
            // this permits us to use the credentials configured locally
            return ProfileCredentialsProvider.create("default");
        }
        return EnvironmentVariableCredentialsProvider.create();
    }

    final String email = "nicolai@btinternet.com";
    CognitoConnector cognito;

    @BeforeEach
    void beforeEach() {
        cognito = CognitoConnector.with(cognitoClient, POOL_ID);
    }

    @Test
    void createUser() {
        UUID id = null;
        try {
            id = cognito.createUser(email);
            assertThat(id).isNotNull();
        } finally {
           cognito.removeUser(id);
        }
    }

    @Test
    @Disabled
    void registeredMembersAreInMemberGroup() {
        UUID id = null;
        try {
            id = cognito.createUser(email);
            assertThat(cognito.isMember(id)).isTrue();

        } finally {
            cognito.removeUser(id);
        }
    }

    @Test
    void registerNewMemberTwice() {
        UUID id = cognito.createUser(email);
        try {
            Assertions.assertThatExceptionOfType(UsernameExistsException.class).isThrownBy(() -> cognito.createUser(email))
                    .withMessageContaining("An account with the given email already exists.");
        } finally {
            cognito.removeUser(id);
        }
    }

    @Test
    void findMember() {
        UUID id = cognito.createUser(email);
        try {
            UserType member = cognito.findUser(email);
            assertThat(member.username()).isEqualTo(id.toString());
        } finally {
            cognito.removeUser(id);
        }
    }

    @Test
    @DisplayName("attempt to delete with null parameter - fail silently")
    void deleteMemberNull() {
        cognito.removeUser(null);
    }

    @Test
    @DisplayName("attempt to delete with unknown id - fail silently")
    void deleteMemberUnknown() {
        cognito.removeUser(UUID.randomUUID());
    }
}