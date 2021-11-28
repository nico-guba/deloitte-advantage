package uk.co.deloitte.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.zone.ZoneId;
import uk.co.deloitte.test.support.EqualityVerifier;

import static org.junit.jupiter.api.Assertions.*;

class OrganisationIdTest implements EqualityVerifier<OrganisationId> {

    private final OrganisationId actual = DomainTestFactory.createTennisClubOrganisationId();

    @Test
    @DisplayName("Create OrganisationId with non-present value.")
    void createWithNullValueTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, OrganisationIdTest::createInvalidOrganisationId);
        assertEquals("OrganisationId must contain a present value.", actual.getMessage());
    }

    @Test
    @DisplayName("Create OrganisationId with present value.")
    void createWithValidValueTest() {
        assertEquals(DomainTestFactory.createTennisClubOrganisationId(), actual);
    }

    @Test
    @DisplayName("Create OrganisationId with random UUID.")
    void createWithRandomUUIDTest() {
        assertNotNull(ZoneId.randomId().toUUID());
    }

    @Test
    @DisplayName("String representation of class equals as expected.")
    void toStringTest() {
        assertEquals("d50faed6-9982-43d2-9dff-9bb41859ddcf", actual.toString());
    }

    @Test
    @DisplayName("toUUID returns correct UUID value.")
    void toUUIDTest() {
        assertEquals(DomainTestFactory.TENNIS_CLUB_ORGANIZATION_ID, actual.toUUID());
    }

    @Override
    public Class<? extends OrganisationId> typeClass() {
        return actual.getClass();
    }

    private static void createInvalidOrganisationId() {
        OrganisationId.valueOf(null);
    }

}