package uk.co.deloitte.domain.site;

import uk.co.deloitte.domain.ddd.ValueObject;

import java.util.Objects;
import java.util.UUID;

public final class SiteId implements ValueObject {

    private final UUID value;

    private SiteId(UUID value) {
        this.value = value;
    }

    public static SiteId valueOf(UUID value) {
        if(value == null) {
            throw new IllegalArgumentException("SiteId must contain a present value.");
        }
        return new SiteId(value);
    }

    public static SiteId randomId() {
        return valueOf(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteId zoneId = (SiteId) o;
        return Objects.equals(value, zoneId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public UUID toUUID() {
        return value;
    }
}
