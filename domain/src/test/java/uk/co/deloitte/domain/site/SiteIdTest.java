package uk.co.deloitte.domain.site;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.DomainTestFactory;
import uk.co.deloitte.test.support.EqualityVerifier;

import static org.junit.jupiter.api.Assertions.*;

class SiteIdTest implements EqualityVerifier<SiteId> {

    private final SiteId actual = DomainTestFactory.createManchesterTennisSiteId();

    @Test
    @DisplayName("Create OrganisationId with non-present value.")
    void createWithNullValueTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, SiteIdTest::createInvalidSiteId);
        assertEquals("SiteId must contain a present value.", actual.getMessage());
    }

    @Test
    @DisplayName("Create SiteId with present value.")
    void createWithValidValueTest() {
        assertEquals(DomainTestFactory.createManchesterTennisSiteId(), actual);
    }

    @Test
    @DisplayName("Create SiteId with random UUID of SiteId.")
    void createWithRandomUUIDTest() {
        assertNotNull(SiteId.randomId().toUUID());
    }

    @Test
    @DisplayName("String representation of SiteId equals as expected.")
    void toStringTest() {
        assertEquals("c690449a-eab4-49a9-981c-42ba6afe35e6", actual.toString());
    }

    @Test
    @DisplayName("toUUID returns correct SiteId UUID value.")
    void toUUIDTest() {
        assertEquals(DomainTestFactory.MANCHESTER_TENNIS_CLUB_SITE, actual.toUUID());
    }

    @Override
    public Class<? extends SiteId> typeClass() {
        return actual.getClass();
    }

    private static void createInvalidSiteId() {
        SiteId.valueOf(null);
    }
}