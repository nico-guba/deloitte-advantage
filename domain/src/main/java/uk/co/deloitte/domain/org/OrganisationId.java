package uk.co.deloitte.domain.org;

import uk.co.deloitte.domain.AbstractIdentity;

import java.util.UUID;

public class OrganisationId extends AbstractIdentity<UUID> {

    private OrganisationId(UUID id) {
        super(id);
    }

    public static OrganisationId with(UUID id) {
        return new OrganisationId(id);
    }

    public static OrganisationId unique() {
        return new OrganisationId(UUID.randomUUID());
    }
}
