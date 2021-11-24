package uk.co.deloitte.domain;

import uk.co.deloitte.domain.ddd.ValueObject;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public final class FacilityId implements ValueObject {

    private final UUID value;

    private FacilityId(UUID value) {
        this.value = value;
    }

    public static FacilityId valueOf(UUID value) {
        if(value == null) {
            throw new IllegalArgumentException("FacilityId must contain a present value.");
        }
        return new FacilityId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityId facilityId = (FacilityId) o;
        return Objects.equals(value, facilityId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", FacilityId.class.getSimpleName()), "]")
                .add(String.format("value=%s", value))
                .toString();
    }

    public UUID toUUID() {
        return value;
    }
}
