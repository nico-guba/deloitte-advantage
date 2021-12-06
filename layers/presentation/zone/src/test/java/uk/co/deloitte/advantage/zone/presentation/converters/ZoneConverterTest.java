package uk.co.deloitte.advantage.zone.presentation.converters;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.TypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.advantage.zone.presentation.DomainTestFactory;
import uk.co.deloitte.advantage.zone.presentation.PresentationTestFactory;
import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.zone.Zone;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ZoneConverterTest {

    private final ZoneConverter actual = ZoneConverter.create(ConverterRegistry.create());

    @Test
    @DisplayName("Convert an empty Zone Domain Object to ZoneResource.")
    void convertZoneTest() {
        ZoneResource actual = convert(DomainTestFactory.createTennisZoneWithFacility());
        assertEquals(PresentationTestFactory.createTennisZoneResource(), actual);
    }

    @Test
    void convertWithNullZoneTest() {
        assertNull(convert(null));
    }

    private ZoneResource convert(Zone zone) {
        return actual.convert(zone, TypeFactory.valueOf(ZoneResource.class),
                new MappingContext(new HashMap<>()));
    }
}