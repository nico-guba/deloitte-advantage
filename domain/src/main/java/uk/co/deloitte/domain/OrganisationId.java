package uk.co.deloitte.domain;

import java.util.UUID;

public class OrganisationId extends AbstractIdentity<UUID> {

    public OrganisationId(UUID id) {
        super(id);
    }

    public static OrganisationId with(UUID id) {
        return new OrganisationId(id);
    }

    public static OrganisationId unique() {
        return new OrganisationId(UUID.randomUUID());
    }
}
