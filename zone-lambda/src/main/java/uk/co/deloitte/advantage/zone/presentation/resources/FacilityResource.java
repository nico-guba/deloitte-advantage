package uk.co.deloitte.advantage.zone.presentation.resources;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@JsonDeserialize(builder = FacilityResource.Builder.class)
public final class FacilityResource {

    private final UUID id;

    public FacilityResource(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacilityResource that = (FacilityResource) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", FacilityResource.class.getSimpleName()), "]")
                .add(String.format("id=%s", id))
                .toString();
    }

    public UUID getId() {
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public FacilityResource build() {
            return new FacilityResource(id);
        }
    }
}
