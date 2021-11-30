package uk.co.deloitte.domain.org;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.DomainTestFactory;
import uk.co.deloitte.domain.ddd.support.EntityEqualityVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrganisationTest implements EntityEqualityVerifier<Organisation> {

    private final Organisation actual = DomainTestFactory.createTennisClubOrganisation();

    @Test
    @DisplayName("Create Facility with non-present value.")
    void createWithNullValueTest() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> Organisation.create(null));
        assertEquals("OrganisationId must contain a present value.", actual.getMessage());
    }

    @Test
    @DisplayName("Create Facility with present value.")
    void createWithValidValue() {
        assertEquals(DomainTestFactory.createTennisClubOrganisation(), actual);
    }


    @Test
    void toStringTest() {
        assertEquals("Organisation[id=d50faed6-9982-43d2-9dff-9bb41859ddcf]", actual.toString());
    }

    @Override
    public Class<? extends Organisation> typeClass() {
        return actual.getClass();
    }

}