package deloitte.advantage.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.UUID;

public class CognitoConnector {
    private final CognitoIdentityProviderClient client;
    private final String poolId;
    private static final Logger LOGGER = LoggerFactory.getLogger(CognitoConnector.class);

    public CognitoConnector(final CognitoIdentityProviderClient client, String poolId) {
        this.client = client;
        this.poolId = poolId;

    }

    public static CognitoConnector with(final CognitoIdentityProviderClient client, String poolId) {
        LOGGER.debug("Connecting to user pool: {}", poolId);
        return new CognitoConnector(client, poolId);
    }

    public UUID createUser(final String email) {
        AttributeType userAttrs = AttributeType.builder()
                .name("email")
                .value(email)
                .build();

        //UUID id = appClientSignUp(email);

        UUID id = adminSignUp(email, userAttrs);

        updatePassword(email);

        //UserType user = findUserByEmail(email);
        //LOGGER.info("Updated user {} to status {}", user.username(), user.userStatus());
//        AdminAddUserToGroupRequest request = AdminAddUserToGroupRequest.builder()
//                .groupName("member")
//                .userPoolId(poolId)
//                .username(response.user().username())
//                .build();
//
//        client.adminAddUserToGroup(request);

        AdminGetUserResponse userResponse = getUser(id);
        String actualEmail = userResponse.userAttributes().stream().filter( p -> {
            if("email".equals(p.name())) {
                return true;
            }
            return false;
        }).findFirst().get().value();

        LOGGER.info("Registered user {} with status {} for email {}", userResponse.username(), userResponse.userStatus(), actualEmail);
        return id;
    }

    public AdminGetUserResponse getUser(UUID id) {
        AdminGetUserRequest request = AdminGetUserRequest.builder()
                .userPoolId(poolId)
                .username(id.toString())
                .build();

        return client.adminGetUser(request);
    }

    private void updatePassword(final String email) {
        AdminSetUserPasswordRequest passwordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(poolId)
                .username(email)
                .password("Advantage123!")
                .permanent(true)
                .build();

        AdminSetUserPasswordResponse adminSetUserPasswordResponse = client.adminSetUserPassword(passwordRequest);
    }

    private UUID adminSignUp(final String email, final AttributeType userAttrs) {
        AdminCreateUserRequest userRequest = AdminCreateUserRequest.builder()
                .userPoolId(poolId)
                .username(email)
                .temporaryPassword("Foobar123!")
                .userAttributes(userAttrs)
                .messageAction(MessageActionType.SUPPRESS)
                .build();

        AdminCreateUserResponse response = client.adminCreateUser(userRequest);
        UUID id = UUID.fromString(response.user().username());
        LOGGER.info("Created user {} with status {}", response.user().username(), response.user().userStatus());
        return id;
    }

    /**
     * for testing signups via app clients...  waiting on the certificate to be verified so we can register
     * a domain with the client.
     *
     * @param email
     * @return
     */
    private UUID appClientSignUp(final String email) {
        SignUpRequest signUp = SignUpRequest.builder()
                .username(email)
                .clientId("4jo7fecol833me5kf5q47824iu")
                .password("Foobar123!")
                .build();
        SignUpResponse signUpResponse = client.signUp(signUp);
        UUID id = UUID.fromString(signUpResponse.userSub());
        LOGGER.info("Signed Up via appClient for user {} with id: {} and confirmed: {}", email, id, signUpResponse.userConfirmed());
        return id;
    }

    public UserType findUserByEmail(final String email) {
        String filter = String.format("email = \"%s\"", email);
        ListUsersRequest usersRequest = ListUsersRequest.builder()
                .userPoolId(poolId)
                .filter(filter).attributesToGet("email")
                .build();

        ListUsersResponse response = client.listUsers(usersRequest);
        if (response.users().size() == 1) {
            return response.users().get(0);
        }

        return null;
    }

    public void removeUser(final UUID id) {
        if (id == null) return;
        AdminDeleteUserRequest request =
                AdminDeleteUserRequest.builder()
                        .username(id.toString())
                        .userPoolId(poolId)
                        .build();
        try {
            client.adminDeleteUser(request);
        } catch (UserNotFoundException exception) {
            // we ignore this for now
        }
    }

    /**
     * tests whether the user with this email is in the member group
     *
     * @return true if the user is in the member group
     */
    public boolean isMember(final UUID id) {
        AdminListGroupsForUserRequest request = AdminListGroupsForUserRequest.builder()
                .userPoolId(poolId)
                .username(id.toString())
                .build();

        AdminListGroupsForUserResponse response = client.adminListGroupsForUser(request);
        if (response.hasGroups()) {
            for(GroupType group : response.groups()) {
                if("member".equals(group.groupName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
