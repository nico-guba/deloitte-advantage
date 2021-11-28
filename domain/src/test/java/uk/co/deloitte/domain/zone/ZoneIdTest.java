package uk.co.deloitte.domain.zone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.DomainTestFactory;
import uk.co.deloitte.test.support.EqualityVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ZoneIdTest implements EqualityVerifier<ZoneId> {

    private final ZoneId actual = DomainTestFactory.createTennisZoneId();

    @Test
    @DisplayName("Create ZoneId with non-present value.")
    void createWithNullValueTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, ZoneIdTest::createInvalidZoneId);
        assertEquals("ZoneId must contain a present value.", actual.getMessage());
    }

    @Test
    @DisplayName("Create ZoneId with present value.")
    void createWithValidValueTest() {
        assertEquals(DomainTestFactory.createTennisZoneId(), actual);
    }

    @Test
    @DisplayName("Create ZoneId with random UUID.")
    void createWithRandomUUIDTest() {
        assertNotNull(ZoneId.randomId().toUUID());
    }

    @Test
    @DisplayName("String representation of class equals as expected.")
    //TODO! any better way of handling this? as this looks shit????
    void toStringTest() {
        assertEquals("ZoneId[value=359dfe3f-aaad-461c-87a7-08d9368584f1]", actual.toString());
    }

    @Test
    @DisplayName("toUUID returns correct UUID value.")
    void toUUIDTest() {
        assertEquals(DomainTestFactory.TENNIS_ZONE_ID_AS_UUID, actual.toUUID());
    }

    @Override
    public Class<? extends ZoneId> typeClass() {
        return actual.getClass();
    }

    private static void createInvalidZoneId() {
        ZoneId.valueOf(null);
    }
}