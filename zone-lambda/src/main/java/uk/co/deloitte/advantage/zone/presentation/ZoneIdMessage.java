package uk.co.deloitte.advantage.zone.presentation;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonCreator;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public final class ZoneIdMessage {
    private final  UUID id;

    private ZoneIdMessage(UUID id) {
        this.id = id;
    }

    @JsonCreator
    private static ZoneIdMessage create(@JsonProperty("id") UUID id) {
        return new ZoneIdMessage(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneIdMessage that = (ZoneIdMessage) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", String.format("%s[", ZoneIdMessage.class.getSimpleName()), "]")
                .add(String.format("id='%s'", id))
                .toString();
    }

    public UUID getId() {
        return id;
    }
}
