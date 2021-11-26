package uk.co.deloitte.advantage.zone.presentation.converters;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.Zone;

public final class ZoneConverter extends CustomConverter<Zone, ZoneResource> {

    public static ZoneConverter create() {
        return new ZoneConverter();
    }

    @Override
    public ZoneResource convert(Zone zone, Type<? extends ZoneResource> type, MappingContext mappingContext) {
        return zone != null ? ZoneResource.builder().withId(zone.id().toUUID()).build() : null;
    }
}
