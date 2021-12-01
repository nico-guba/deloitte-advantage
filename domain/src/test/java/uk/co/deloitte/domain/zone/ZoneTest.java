package uk.co.deloitte.domain.zone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.DomainTestFactory;
import uk.co.deloitte.domain.ddd.support.EntityEqualityVerifier;
import uk.co.deloitte.domain.facility.Facility;

import static org.junit.jupiter.api.Assertions.*;

class ZoneTest implements EntityEqualityVerifier<Zone> {

    private final Zone actual = DomainTestFactory.createBlankTennisZone();

    @Test
    @DisplayName("Create Zone with invalid id.")
    void createZoneWithInValidIdTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, ZoneTest::createInvalidZone);
        assertEquals("Zone must contain a present value.", actual.getMessage());
    }

    @Test
    @DisplayName("Create Zone with valid id.")
    void createZoneWithValidIdTest() {
        assertEquals(DomainTestFactory.createBlankTennisZone(), actual);
    }

    @Test
    @DisplayName("Add facility test.")
    void addFacilityTest() {
        assertAddFacility();
    }

    @Test
    @DisplayName("Remove facility test.")
    void removeFacilityTest() {
        assertAddFacility();
        actual.removeFacility(DomainTestFactory.createA1TennisFacilityId());
        assertEquals(0, actual.totalFacilities());
    }

    @Test
    @DisplayName("Get facility by facility id test.")
    void getFacilityByIdTest() {
        assertAddFacility();
        Facility facility = actual.getFacility(DomainTestFactory.createA1TennisFacilityId());
        assertEquals(DomainTestFactory.createA1TennisFacility(), facility);
    }

    @Test
    @DisplayName("ToString with empty facilities.")
    void toStringWithEmptyFacilitiesTest() {
        assertEquals("Zone[id=359dfe3f-aaad-461c-87a7-08d9368584f1, siteId=c690449a-eab4-49a9-981c-42ba6afe35e6, facilities={}]", actual.toString());
    }

    @Test
    @DisplayName("ToString with a single facilities.")
    void toStringWithSingleFacilitiesTest() {
        addA1TennisFacility();
        assertEquals("Zone[id=359dfe3f-aaad-461c-87a7-08d9368584f1, " +
                "siteId=c690449a-eab4-49a9-981c-42ba6afe35e6, " +
                "facilities={FacilityId[value=a3b6fbea-8fc6-4233-94a7-dd60e175c02c]=" +
                "Facility[id=FacilityId[value=a3b6fbea-8fc6-4233-94a7-dd60e175c02c]]}]", actual.toString());
    }

    @Override
    public Class<? extends Zone> typeClass() {
        return actual.getClass();
    }

    private void addA1TennisFacility() {
        actual.addFacility(DomainTestFactory.createA1TennisFacility());
    }

    private void assertAddFacility() {
        addA1TennisFacility();
        assertEquals(1, actual.totalFacilities());
    }

    private static void createInvalidZone() {
        Zone.create(null, DomainTestFactory.createManchesterTennisSiteId(), "Tennis");
    }
}