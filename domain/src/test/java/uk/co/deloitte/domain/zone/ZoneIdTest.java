package uk.co.deloitte.domain.zone;

import uk.co.deloitte.domain.IdentityFixture;

public class ZoneIdTest implements IdentityFixture<ZoneId> {
    @Override
    public ZoneId invalidInstance() {
        return ZoneId.with(null);
    }

    @Override
    public ZoneId validInstance() {
        return ZoneId.unique();
    }
}
