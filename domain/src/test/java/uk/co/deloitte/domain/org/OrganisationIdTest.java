package uk.co.deloitte.domain.org;

import uk.co.deloitte.domain.DomainTestFactory;
import uk.co.deloitte.domain.IdentityFixture;
import uk.co.deloitte.domain.org.OrganisationId;

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