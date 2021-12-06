package uk.co.deloitte.domain.facility;

import uk.co.deloitte.domain.ddd.support.IdentityVerifier;

class FacilityIdTest implements IdentityVerifier<FacilityId> {

    @Override
    public FacilityId invalidInstance() {
        return FacilityId.with(null);
    }

    @Override
    public FacilityId validInstance() {
        return FacilityId.unique();
    }
}