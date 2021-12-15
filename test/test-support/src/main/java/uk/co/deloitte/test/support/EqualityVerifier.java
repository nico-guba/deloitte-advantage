package uk.co.deloitte.test.support;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface EqualityVerifier<T> {

    @Test
    @DisplayName("Generic Implementation for Equality Verification.")
    default void equalityVerification()
    {
        EqualsVerifier.forClass(typeClass()).usingGetClass().verify();
    }

    Class<? extends T> typeClass();

}
