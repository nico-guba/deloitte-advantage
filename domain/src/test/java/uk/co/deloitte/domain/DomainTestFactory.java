package uk.co.deloitte.domain;

import java.util.UUID;

public final class DomainTestFactory {

    public static final UUID TENNIS_ZONE_ID_AS_UUID = UUID.fromString("359dfe3f-aaad-461c-87a7-08d9368584f1");

    public static final UUID TENNIS_FACILITY_ID_A1_AS_UUID = UUID.fromString("a3b6fbea-8fc6-4233-94a7-dd60e175c02c");

    private DomainTestFactory() {

    }

    public static ZoneId createTennisZoneId() {
        return ZoneId.valueOf(TENNIS_ZONE_ID_AS_UUID);
    }

    public static Zone createBlankTennisZone() {
        return Zone.create(createTennisZoneId());
    }

    public static FacilityId createA1TennisFacilityId() {
        return FacilityId.valueOf(TENNIS_FACILITY_ID_A1_AS_UUID);
    }

    public static Facility createA1TennisFacility() {
        return Facility.create(createA1TennisFacilityId());
    }
}
