package uk.co.deloitte.domain.org;

import uk.co.deloitte.domain.DomainTestFactory;
import uk.co.deloitte.domain.ddd.support.IdentityVerifier;

class OrganisationIdTest implements IdentityVerifier<OrganisationId> {

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