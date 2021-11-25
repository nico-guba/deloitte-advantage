package uk.co.deloitte.advantage.zone.presentation;

import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.DomainTestFactory;

import java.util.HashSet;

public final class PresentationTestFactory {

    public static ZoneResource createTennisZoneResource() {
        return ZoneResource
                .builder()
                .withId(DomainTestFactory.TENNIS_ZONE_ID_AS_UUID)
                .withFacilities(new HashSet<>())
                .build();
    }

}
