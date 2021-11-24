package uk.co.deloitte.domain;

import uk.co.deloitte.domain.ddd.Aggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * A {@link Zone} is a location for a given sport/activity. Within a {@link Zone} multiple {@link Facility}s can be present
 * to depict different field types ect.
 *
 * For example a Zone may be for Tennis whereby there are 4 facilities, each with a different field type or activity
 * playing at a given time.
 */
public final class Zone implements Aggregate<ZoneId> {

    private final ZoneId id;

    private final Map<FacilityId, Facility> facilities = new HashMap<>();

    private Zone(ZoneId id) {
        this.id = id;
    }

    public static Zone create(ZoneId id) {
        if(id == null) {
            throw new IllegalArgumentException("Zone must contain a present value.");
        }
        return new Zone(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zone zone = (Zone) o;
        return Objects.equals(id, zone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", Zone.class.getSimpleName()), "]")
                .add(String.format("id=%s", id))
                .add(String.format("facilities=%s", facilities))
                .toString();
    }

    public Facility getFacility(FacilityId id) {
        return facilities.get(id);
    }

    public void addFacility(Facility facility) {
        facilities.put(facility.id(), facility);
    }

    public void removeFacility(FacilityId id) {
        facilities.remove(id);
    }

    public int totalFacilities() {
        return facilities.size();
    }

    @Override
    public ZoneId id() {
        return id;
    }
}
