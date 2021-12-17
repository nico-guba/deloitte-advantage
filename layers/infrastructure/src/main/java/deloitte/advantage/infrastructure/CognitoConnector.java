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
        LOGGER.info("Connected to user pool: {}", poolId);
    }

    public static CognitoConnector with(final CognitoIdentityProviderClient client, String poolId) {
        return new CognitoConnector(client, poolId);
    }

    public UUID createUser(final String email) {
        AttributeType userAttrs = AttributeType.builder()
                .name("email")
                .value(email)
                .build();

        AdminCreateUserRequest userRequest = AdminCreateUserRequest.builder()
                .userPoolId(poolId)
                .username(email)
                .temporaryPassword("Foobar123!")
                .userAttributes(userAttrs)
                .messageAction(MessageActionType.SUPPRESS)
                .build();

        AdminCreateUserResponse response = client.adminCreateUser(userRequest);

        AdminSetUserPasswordRequest passwordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(poolId)
                .username(email)
                .password("Advantage123!")
                .permanent(true)
                .build();

        AdminSetUserPasswordResponse adminSetUserPasswordResponse = client.adminSetUserPassword(passwordRequest);

        LOGGER.info("Registered user={},  status={}", response.user().username(), response.user().userStatus());

//        AdminAddUserToGroupRequest request = AdminAddUserToGroupRequest.builder()
//                .groupName("member")
//                .userPoolId(poolId)
//                .username(response.user().username())
//                .build();
//
//        client.adminAddUserToGroup(request);

        return UUID.fromString(response.user().username());
    }

    public UserType findUser(final String email) {
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
