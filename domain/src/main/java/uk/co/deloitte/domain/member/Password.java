package uk.co.deloitte.domain.member;

import uk.co.deloitte.domain.ddd.ValueObject;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Password implements ValueObject {

    private final byte[] value;

    private Password(final byte[] password) {
        this.value = password;
    }

    public static Password with(final String password) {
        return new Password(password.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Password password = (Password) o;
        return Arrays.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
