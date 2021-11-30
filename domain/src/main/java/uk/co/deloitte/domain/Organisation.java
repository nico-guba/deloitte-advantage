package uk.co.deloitte.domain;

import uk.co.deloitte.domain.ddd.Aggregate;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents an organisation/company of the club.
 */
public final class Organisation implements Aggregate<OrganisationId> {

    private final OrganisationId id;

    private Organisation(OrganisationId id) {
        this.id = id;
    }

    public static Organisation create(OrganisationId id) {
        if(id == null) {
            throw new IllegalArgumentException("OrganisationId must contain a present value.");
        }
        return new Organisation(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organisation that = (Organisation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", Organisation.class.getSimpleName()), "]")
                .add(String.format("id=%s", id))
                .toString();
    }

    @Override
    public OrganisationId id() {
        return id;
    }
}
