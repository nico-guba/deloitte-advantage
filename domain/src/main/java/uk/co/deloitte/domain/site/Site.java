package uk.co.deloitte.domain.site;

import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.ddd.Aggregate;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * An {@link Organisation} may have one or more sites that they own and conduct business. This class
 * represents a singular site.
 */
public final class Site implements Aggregate<Identity> {

    private final Identity id;

    private final Identity organisationId;

    private Site(Identity id, Identity organisationId) {
        this.id = id;
        this.organisationId = organisationId;
    }

    public static Site create(Identity id, Identity organisationId) {
        if(id == null) {
            throw new IllegalArgumentException("SiteId must contain a present value.");
        }
        return new Site(id, organisationId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site that = (Site) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", Site.class.getSimpleName()), "]")
                .add(String.format("id=%s", id))
                .toString();
    }

    @Override
    public Identity id() {
        return id;
    }

    public Identity getOrganisationId() {
        return organisationId;
    }
}
