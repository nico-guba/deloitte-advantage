package uk.co.deloitte.domain;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractIdentity<T> {

    private final T value;

    protected  AbstractIdentity(T value) {
        if(value == null)
            throw new IllegalArgumentException("Identity must contain a present value.");
        this.value = value;
    }

    public T value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdentity<?> that = (AbstractIdentity<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
