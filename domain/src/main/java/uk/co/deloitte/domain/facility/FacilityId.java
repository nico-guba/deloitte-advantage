package uk.co.deloitte.domain.facility;

import uk.co.deloitte.domain.ddd.Identity;

import java.util.UUID;

public class FacilityId extends Identity<UUID> {
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
