package uk.co.deloitte.domain;

import uk.co.deloitte.domain.ddd.ValueObject;

import java.util.Objects;
import java.util.UUID;

@Deprecated
public final class Identity implements ValueObject {

    private final UUID value;

    private Identity(UUID value) {
        this.value = value;
    }

    public static Identity valueOf(UUID value) {
        if(value == null) {
            throw new IllegalArgumentException("OrganisationId must contain a present value.");
        }
        return new Identity(value);
    }

    public static Identity unique() {
        return valueOf(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity zoneId = (Identity) o;
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
