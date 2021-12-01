package uk.co.deloitte.domain.facility;

import uk.co.deloitte.domain.ddd.AbstractIdentity;

import java.util.UUID;

public class FacilityId extends AbstractIdentity<UUID> {
    private FacilityId(final UUID value) {
        super(value);
    }

    public static FacilityId unique() {
        return FacilityId.with(UUID.randomUUID());
    }

    public static FacilityId with(UUID value) {return new FacilityId(value);}

    public static FacilityId fromString(final String id) {
        return FacilityId.with(UUID.fromString(id));
    }
}
