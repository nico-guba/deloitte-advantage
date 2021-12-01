package uk.co.deloitte.domain.ddd;

import java.util.Objects;

public abstract class Identity<T> {

    private final T value;

    protected Identity(T value) {
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
        Identity<?> that = (Identity<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
