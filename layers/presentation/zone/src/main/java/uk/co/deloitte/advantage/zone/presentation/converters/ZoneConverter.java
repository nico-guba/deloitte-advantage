package uk.co.deloitte.advantage.zone.presentation.converters;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import uk.co.deloitte.advantage.zone.presentation.resources.FacilityResource;
import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.zone.Zone;

import java.util.Set;
import java.util.stream.Collectors;

public final class ZoneConverter extends CustomConverter<Zone, ZoneResource> {

    private final ConverterRegistry converterRegistry;

    public ZoneConverter(ConverterRegistry converterRegistry) {
        this.converterRegistry = converterRegistry;
    }

    public static ZoneConverter create(ConverterRegistry converterRegistry) {
        return new ZoneConverter(converterRegistry);
    }

    @Override
    public ZoneResource convert(Zone zone, Type<? extends ZoneResource> type, MappingContext mappingContext) {
        return zone != null ? ZoneResource.builder()
                .withId(zone.id().value())
                .withFacilities(convertFacilities(zone))
                .build()
                : null;
    }

    private Set<FacilityResource> convertFacilities(Zone zone) {
        return zone.getFacilities()
                .values()
                .stream()
                .map(converterRegistry::toFacilityResource)
                .collect(Collectors.toSet());
    }
}
