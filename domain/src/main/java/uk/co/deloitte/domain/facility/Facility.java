package uk.co.deloitte.domain.facility;

import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.ddd.Entity;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A {@link Facility} is a place where a certain activity is running, a facility can be defined by the type of
 * room or ground an activity/session is running at.
 *
 * For example,
 * A Zone can be called Gym however the Gym may have 6 facilities, all running different equipment or activities such as
 * Boxing.
 */
public final class Facility implements Entity<Identity> {

    private final Identity id;

    private Facility(Identity id) {
        this.id = id;
    }

    public static Facility create(Identity id) {
        if(id == null) {
            throw new IllegalArgumentException("Facility must contain a present value.");
        }
        return new Facility(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return Objects.equals(id, facility.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", Facility.class.getSimpleName()), "]")
                .add(String.format("id=%s", id))
                .toString();
    }

    @Override
    public Identity id() {
        return id;
    }
}
