package uk.co.deloitte.advantage.zone.presentation.converters;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.TypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.advantage.zone.presentation.PresentationTestFactory;
import uk.co.deloitte.advantage.zone.presentation.resources.FacilityResource;
import uk.co.deloitte.domain.DomainTestFactory;
import uk.co.deloitte.domain.Facility;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FacilityConverterTest {

    private final FacilityConverter actual = FacilityConverter.create();

    @Test
    @DisplayName("Convert Facility Domain Object to Facility Resource Presentation.")
    void convertSuccessfullyTest() {
        FacilityResource actual = convert(DomainTestFactory.createA1TennisFacility());
        assertEquals(PresentationTestFactory.createA1TennisFacilityResource(), actual);
    }

    @Test
    @DisplayName("Convert with non-present Facility Domain Object.")
    void convertWithNullFacilityTest() {
        assertNull(convert(null));
    }

    private FacilityResource convert(Facility factory) {
        return actual.convert(factory, TypeFactory.valueOf(FacilityResource.class), new MappingContext(new HashMap<>()));
    }

}