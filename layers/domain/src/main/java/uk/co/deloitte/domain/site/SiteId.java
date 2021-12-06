package uk.co.deloitte.domain.site;

import uk.co.deloitte.domain.ddd.Identity;

import java.util.UUID;

public class SiteId extends Identity<UUID> {

    private SiteId(final UUID value) {
        super(value);
    }

    public static SiteId unique() {
        return SiteId.with(UUID.randomUUID());
    }

    public static SiteId with(final UUID id) {
        return new SiteId(id);
    }

    public static SiteId fromString(final String id) {
        return SiteId.with(UUID.fromString(id));
    }
}
