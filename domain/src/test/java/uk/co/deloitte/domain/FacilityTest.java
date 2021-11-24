package uk.co.deloitte.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.ddd.support.EntityEqualityVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FacilityTest implements EntityEqualityVerifier<Facility> {

    private final Facility actual = DomainTestFactory.createA1TennisFacility();

    @Test
    @DisplayName("Create Facility with non-present value.")
    void createWithNullValueTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, FacilityTest::createInvalidFacility);
        assertEquals("Facility must contain a present value.", actual.getMessage());
    }

    @Test
    @DisplayName("Create Facility with present value.")
    void createWithValidValue() {
        assertEquals(DomainTestFactory.createA1TennisFacility(), actual);
    }


    @Test
    void toStringTest() {
        assertEquals("Facility[id=FacilityId[value=a3b6fbea-8fc6-4233-94a7-dd60e175c02c]]", actual.toString());
    }

    @Override
    public Class<? extends Facility> typeClass() {
        return actual.getClass();
    }

    private static void createInvalidFacility() {
        Facility.create(null);
    }
}