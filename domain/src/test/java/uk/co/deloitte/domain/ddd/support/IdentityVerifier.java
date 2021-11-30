package uk.co.deloitte.domain.ddd.support;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.ddd.AbstractIdentity;
import uk.co.deloitte.test.support.EqualityVerifier;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public interface IdentityVerifier<I extends AbstractIdentity<?>> extends EqualityVerifier<I> {

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
    @DisplayName("Create Identity with random UUID.")
    default void createWithRandomUUIDTest() {
        Set<I> created = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            created.add(validInstance());
        }
        assertEquals(100, created.size());
    }


}
