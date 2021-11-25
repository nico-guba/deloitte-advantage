package uk.co.deloitte.advantage.zone.presentation.resources;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import uk.co.deloitte.domain.Facility;

import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;

@JsonDeserialize(builder = ZoneResource.Builder.class)
public final class ZoneResource {

    private final UUID id;

    private final Set<Facility> facilities;

    public ZoneResource(UUID id, Set<Facility> facilities) {
        this.id = id;
        this.facilities = facilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneResource that = (ZoneResource) o;
        return Objects.equals(id, that.id) && Objects.equals(facilities, that.facilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facilities);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", ZoneResource.class.getSimpleName()), "]")
                .add(String.format("id=%s", id))
                .add(String.format("facilities=%s", facilities))
                .toString();
    }

    public UUID getId() {
        return id;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;
        private Set<Facility> facilities;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withFacilities(Set<Facility> facilities) {
            this.facilities = facilities;
            return this;
        }

        public ZoneResource build() {
            return new ZoneResource(id, facilities);
        }
    }
}
