package uk.co.deloitte.domain.zone;

import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.ddd.Aggregate;

import java.util.*;

/**
 * A {@link Zone} is a location for a given sport/activity. Within a {@link Zone} multiple {@link Facility}s can be present
 * to depict different field types ect.
 *
 * For example a Zone may be for Tennis whereby there are 4 facilities, each with a different field type or activity
 * playing at a given time.
 */
public final class Zone implements Aggregate<Identity> {

    private final Identity id;

    private final Identity siteId;

    private final Map<Identity, Facility> facilities = new HashMap<>();

    private Zone(Identity id, Identity siteId) {
        this.id = id;
        this.siteId = siteId;
    }

    public static Zone create(Identity id, Identity siteId) {
        if(id == null) {
            throw new IllegalArgumentException("Zone Identity must contain a present value.");
        }
        return new Zone(id, siteId);
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
                .add(String.format("siteId=%s", siteId))
                .add(String.format("facilities=%s", facilities))
                .toString();
    }

    public Facility getFacility(Identity id) {
        return facilities.get(id);
    }

    public void addFacility(Facility facility) {
        facilities.put(facility.id(), facility);
    }

    public void removeFacility(Identity id) {
        facilities.remove(id);
    }

    public int totalFacilities() {
        return facilities.size();
    }

    public Map<Identity, Facility> getFacilities() {
        return Collections.unmodifiableMap(facilities);
    }

    public Identity getSiteId() {
        return siteId;
    }

    @Override
    public Identity id() {
        return id;
    }
}
