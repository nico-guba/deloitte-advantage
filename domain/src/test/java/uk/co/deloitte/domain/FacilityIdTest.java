package uk.co.deloitte.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.test.support.EqualityVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FacilityIdTest implements EqualityVerifier<FacilityId> {

    private final FacilityId actual = DomainTestFactory.createA1TennisFacilityId();

    @Test
    @DisplayName("Create FacilityId with non-present value.")
    void createWithNullValueTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, FacilityIdTest::createInvalidFacilityId);
        assertEquals("FacilityId must contain a present value.", actual.getMessage());
    }

    @Test
    @DisplayName("Create FacilityId with present value.")
    void createWithValidValue() {
        assertEquals(DomainTestFactory.createA1TennisFacilityId(), actual);
    }

    @Test
    @DisplayName("String representation of class equals as expected.")
    void toStringTest() {
        assertEquals("FacilityId[value=a3b6fbea-8fc6-4233-94a7-dd60e175c02c]", actual.toString());
    }

    @Test
    @DisplayName("toUUID returns correct UUID value.")
    void toUUIDTest() {
        assertEquals(DomainTestFactory.TENNIS_FACILITY_ID_A1_AS_UUID, actual.toUUID());
    }

    @Override
    public Class<? extends FacilityId> typeClass() {
        return actual.getClass();
    }

    private static void createInvalidFacilityId() {
        FacilityId.valueOf(null);
    }
}