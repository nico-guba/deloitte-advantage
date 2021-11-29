package uk.co.deloitte.domain;

import uk.co.deloitte.domain.site.Site;
import uk.co.deloitte.domain.zone.Facility;
import uk.co.deloitte.domain.zone.Zone;

import java.util.UUID;

public final class DomainTestFactory {

    public static final UUID MANCHESTER_TENNIS_CLUB_SITE = UUID.fromString("c690449a-eab4-49a9-981c-42ba6afe35e6");
    public static UUID TENNIS_CLUB_ORGANIZATION_ID = UUID.fromString("d50faed6-9982-43d2-9dff-9bb41859ddcf");

    public static final UUID TENNIS_ZONE_ID_AS_UUID = UUID.fromString("359dfe3f-aaad-461c-87a7-08d9368584f1");

    public static final UUID TENNIS_FACILITY_ID_A1_AS_UUID = UUID.fromString("a3b6fbea-8fc6-4233-94a7-dd60e175c02c");

    private DomainTestFactory() {

    }

    public static Identity createTennisClubOrganisationId() {
        return Identity.valueOf(TENNIS_CLUB_ORGANIZATION_ID);
    }

    public static Identity createTennisZoneId() {
        return Identity.valueOf(TENNIS_ZONE_ID_AS_UUID);
    }

    public static Zone createBlankTennisZone() {
        return Zone.create(createTennisZoneId(), createManchesterTennisSiteId(), "Tennis");
    }

    public static Zone createTennisZoneWithFacility() {
        Zone zone = createBlankTennisZone();
        zone.addFacility(createA1TennisFacility());
        return zone;
    }

    public static Identity createA1TennisFacilityId() {
        return Identity.valueOf(TENNIS_FACILITY_ID_A1_AS_UUID);
    }

    public static Facility createA1TennisFacility() {
        return Facility.create(createA1TennisFacilityId());
    }

    public static Organisation createTennisClubOrganisation() {
        return Organisation.create(createTennisClubOrganisationId());
    }

    public static Identity createManchesterTennisSiteId() {
        return Identity.valueOf(MANCHESTER_TENNIS_CLUB_SITE);
    }

    public static Site createManchesterTennisSite() {
        return Site.create(createManchesterTennisSiteId(), createTennisClubOrganisationId());
    }
}
