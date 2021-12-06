package uk.co.deloitte.advantage.zone.presentation;

import uk.co.deloitte.advantage.zone.presentation.resources.FacilityResource;
import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.DomainTestFactory;

import java.util.Set;

public final class PresentationTestFactory {

    public static ZoneResource createTennisZoneResource() {
        return ZoneResource
                .builder()
                .withId(DomainTestFactory.TENNIS_ZONE_ID_AS_UUID)
                .withFacilities(Set.of(createA1TennisFacilityResource()))
                .build();
    }

    public static FacilityResource createA1TennisFacilityResource() {
        return FacilityResource
                .builder()
                .withId(DomainTestFactory.TENNIS_FACILITY_ID_A1_AS_UUID)
                .build();
    }

}
