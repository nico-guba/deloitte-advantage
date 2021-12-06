package uk.co.deloitte.domain.site;

import uk.co.deloitte.domain.ddd.support.IdentityVerifier;

class SiteIdTest implements IdentityVerifier<SiteId> {

    @Override
    public SiteId invalidInstance() {
        return SiteId.with(null);
    }

    @Override
    public SiteId validInstance() {
        return SiteId.unique();
    }
}