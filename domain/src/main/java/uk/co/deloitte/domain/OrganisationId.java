package uk.co.deloitte.domain;

import java.util.UUID;

public class OrganisationId extends AbstractIdentity {

    public OrganisationId(UUID id) {
        super(id);
    }

    public static OrganisationId unique() {
        return new OrganisationId(UUID.randomUUID());
    }
}
