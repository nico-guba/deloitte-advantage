package uk.co.deloitte.domain.site;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.DomainTestFactory;
import uk.co.deloitte.domain.ddd.support.EntityEqualityVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SiteTest implements EntityEqualityVerifier<Site> {

    private final Site actual = DomainTestFactory.createManchesterTennisSite();

    @Test
    @DisplayName("Create Facility with non-present value.")
    void createWithNullValueTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, SiteTest::createInvalidSiteId);
        assertEquals("SiteId must contain a present value.", actual.getMessage());
    }

    @Test
    @DisplayName("Create Facility with present value.")
    void createWithValidValue() {
        assertEquals(DomainTestFactory.createManchesterTennisSite(), actual);
    }


    @Test
    void toStringTest() {
        assertEquals("Site[id=c690449a-eab4-49a9-981c-42ba6afe35e6]", actual.toString());
    }

    @Test
    @DisplayName("Organization Id set is as expected.")
    void isOrganizationIdAsExpectedTest() {
        assertEquals(DomainTestFactory.createTennisClubOrganisationId(), actual.getOrganisationId());
    }

    @Override
    public Class<? extends Site> typeClass() {
        return actual.getClass();
    }

    private static void createInvalidSiteId() {
        Site.create(null, DomainTestFactory.createTennisClubOrganisationId());
    }
}