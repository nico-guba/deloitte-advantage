package uk.co.deloitte.domain.ddd.support;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.ddd.Identity;
import uk.co.deloitte.test.support.EqualityVerifier;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public interface IdentityVerifier<I extends Identity<?>> extends EqualityVerifier<I> {

    /**
     * supply the implementation attempting to initialize the Identity with a null value
     *
     * @return Identity
     */
    I invalidInstance();

    /**
     * Supply the implementation initialized with a valid value
     *
     * @return Identity
     */
    I validInstance();

    @Test
    @DisplayName("Create Identity with present value.")
    default void createWithValidValueTest() {
        I impl = validInstance();
        assertNotNull(impl.value());
    }

    @Test
    @DisplayName("String representation of class equals as expected.")
    default void toStringTest() {
        I impl = validInstance();
        assertEquals(impl.value().toString(), impl.toString());
    }

    @Test
    @DisplayName("Create OrganisationId with non-present value.")
    default void createWithNullValueTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, this::invalidInstance);
        assertEquals("Identity must contain a present value.", actual.getMessage());
    }

    @SuppressWarnings("unchecked")
    @Override
    default Class<? extends I> typeClass() {
        return (Class<? extends I>) validInstance().getClass();
    }

    @Test
    @Disabled("not sure if it belongs in here or is even necessary.  It would make sense having this " +
            "test in a specialised fixture focussing on uuids")
    @DisplayName("Create Identity with random UUID.")
    default void createWithRandomUUIDTest() {
        Set<I> created = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            created.add(validInstance());
        }
        assertEquals(100, created.size());
    }
}
