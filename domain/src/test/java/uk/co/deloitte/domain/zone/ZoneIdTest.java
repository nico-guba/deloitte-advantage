package uk.co.deloitte.domain.zone;

import uk.co.deloitte.domain.ddd.support.IdentityVerifier;

public class ZoneIdTest implements IdentityVerifier<ZoneId> {
    @Override
    public ZoneId invalidInstance() {
        return ZoneId.with(null);
    }

    @Override
    public ZoneId validInstance() {
        return ZoneId.unique();
    }
}
