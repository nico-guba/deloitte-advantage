package uk.co.deloitte.advantage.zone.presentation.converters;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.deloitte.advantage.zone.presentation.resources.FacilityResource;
import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.zone.Facility;
import uk.co.deloitte.domain.zone.Zone;

import java.util.Optional;

/**
 * Currently using the <b>orika library</b> for converting domain objects to presentation objects.
 * Not sure if this is going to stay, need to figure out if this is ok or find a better one.
 */
public final class ConverterRegistry {

    private final MapperFactory mapperFactory;

    private ConverterRegistry(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    public static ConverterRegistry create() {
        return new ConverterRegistry(new DefaultMapperFactory.Builder().build()).initialize();
    }

    private ConverterRegistry initialize() {
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(ZoneConverter.create(this));
        converterFactory.registerConverter(FacilityConverter.create());
        return this;
    }

    public ZoneResource toZoneResource(Zone zone) {
        return convertTo(zone, ZoneResource.class);
    }

    public FacilityResource toFacilityResource(Facility zone) {
        return convertTo(zone, FacilityResource.class);
    }

    private <T> T convertTo(Object source, Class<? extends T> type) {
        return mapperFactory.getMapperFacade().map(source, type);
    }
}
