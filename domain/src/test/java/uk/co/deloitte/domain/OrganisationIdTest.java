package uk.co.deloitte.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import uk.co.deloitte.test.support.EqualityVerifier;

import static org.junit.jupiter.api.Assertions.*;

class OrganisationIdTest implements IdentityFixture<OrganisationId> {

    private final OrganisationId actual = DomainTestFactory.createTennisClubOrganisationId();

    @Override
    public OrganisationId invalidInstance() {
        return OrganisationId.with(null);
    }

    @Override
    public OrganisationId validInstance() {
        return OrganisationId.unique();
    }
}