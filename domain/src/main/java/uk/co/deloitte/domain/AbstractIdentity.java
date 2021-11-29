package uk.co.deloitte.domain;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class AbstractIdentity {

    protected Optional<UUID> value;

    protected  AbstractIdentity(UUID value) {
        this.value = Optional.of(value);
    }

    public UUID value() {
        return value.orElseThrow();
    }

    @Override
    public String toString() {
        return value.orElseThrow().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdentity that = (AbstractIdentity) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
