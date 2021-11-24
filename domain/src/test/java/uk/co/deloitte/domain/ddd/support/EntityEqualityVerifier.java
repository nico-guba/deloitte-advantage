package uk.co.deloitte.domain.ddd.support;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.ddd.Entity;
import uk.co.deloitte.test.support.EqualityVerifier;

public interface EntityEqualityVerifier<T extends Entity<?>> extends EqualityVerifier<T> {

    @Test
    @Override
    @DisplayName("Equality Verification for Aggregate.")
    default void equalityVerification()
    {
        EqualsVerifier.forClass(typeClass()).usingGetClass().withOnlyTheseFields("id").verify();
    }
}