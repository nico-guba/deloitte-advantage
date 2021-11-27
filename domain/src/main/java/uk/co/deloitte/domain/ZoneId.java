package uk.co.deloitte.domain;

import uk.co.deloitte.domain.ddd.ValueObject;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public final class ZoneId implements ValueObject {

    private final UUID value;

    private ZoneId(UUID value) {
        this.value = value;
    }

    public static ZoneId valueOf(UUID value) {
        if(value == null) {
            throw new IllegalArgumentException("ZoneId must contain a present value.");
        }
        return new ZoneId(value);
    }

    public static ZoneId randomId() {
        return valueOf(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneId zoneId = (ZoneId) o;
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
