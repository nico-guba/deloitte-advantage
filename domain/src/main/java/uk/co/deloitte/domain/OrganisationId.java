package uk.co.deloitte.domain;

import uk.co.deloitte.domain.ddd.ValueObject;

import java.util.Objects;
import java.util.UUID;

public final class OrganisationId implements ValueObject {

    private final UUID value;

    private OrganisationId(UUID value) {
        this.value = value;
    }

    public static OrganisationId valueOf(UUID value) {
        if(value == null) {
            throw new IllegalArgumentException("OrganisationId must contain a present value.");
        }
        return new OrganisationId(value);
    }

    public static OrganisationId randomId() {
        return valueOf(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganisationId zoneId = (OrganisationId) o;
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
