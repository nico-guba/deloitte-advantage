package uk.co.deloitte.advantage.zone.presentation.converters;

import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.deloitte.advantage.zone.presentation.resources.FacilityResource;
import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.Facility;
import uk.co.deloitte.domain.Zone;

public final class ConverterRegistry {

    private final DefaultMapperFactory mapperFactory;

    private ConverterRegistry(DefaultMapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    public static ConverterRegistry create() {
        return new ConverterRegistry(new DefaultMapperFactory.Builder().build()).initialize();
    }

    private ConverterRegistry initialize() {
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(ZoneConverter.create());
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
