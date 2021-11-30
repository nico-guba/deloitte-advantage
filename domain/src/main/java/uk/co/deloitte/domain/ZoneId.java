package uk.co.deloitte.domain;

import java.util.Optional;
import java.util.UUID;

public class ZoneId extends AbstractIdentity<UUID> {

    private ZoneId(UUID value) {
        super(value);
    }

    public static ZoneId unique() {
        return new ZoneId(UUID.randomUUID());
    }

}
