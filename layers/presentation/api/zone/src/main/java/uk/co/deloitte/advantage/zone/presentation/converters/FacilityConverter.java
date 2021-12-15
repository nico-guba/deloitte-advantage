package uk.co.deloitte.advantage.zone.presentation.converters;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import uk.co.deloitte.advantage.zone.presentation.resources.FacilityResource;
import uk.co.deloitte.domain.facility.Facility;

public final class FacilityConverter extends CustomConverter<Facility, FacilityResource> {

    public static FacilityConverter create() {
        return new FacilityConverter();
    }

    @Override
    public FacilityResource convert(Facility facility, Type<? extends FacilityResource> type, MappingContext mappingContext) {
        return facility != null ? FacilityResource.builder().withId(facility.id().value()).build() : null;
    }
}
