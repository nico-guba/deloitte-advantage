package uk.co.deloitte.domain.org;

import uk.co.deloitte.domain.ddd.Identity;

import java.util.UUID;

public class OrganisationId extends Identity<UUID> {

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
