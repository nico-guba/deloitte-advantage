package uk.co.deloitte.domain.zone;

import uk.co.deloitte.domain.AbstractIdentity;

import java.util.UUID;

public class ZoneId extends AbstractIdentity<UUID> {

    private ZoneId(UUID value) {
        super(value);
    }

    public static ZoneId unique() {
        return ZoneId.with(UUID.randomUUID());
    }

    public static ZoneId with(UUID value) {
        return new ZoneId(value);
    }

    public static ZoneId fromString(final String id) {
       return ZoneId.with(UUID.fromString(id));
    }
}
