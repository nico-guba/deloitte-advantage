package uk.co.deloitte.advantage.zone.presentation;

import uk.co.deloitte.domain.facility.Facility;
import uk.co.deloitte.domain.facility.FacilityId;
import uk.co.deloitte.domain.org.Organisation;
import uk.co.deloitte.domain.org.OrganisationId;
import uk.co.deloitte.domain.site.Site;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.UUID;

public final class DomainTestFactory {

    public static final UUID MANCHESTER_TENNIS_CLUB_SITE = UUID.fromString("c690449a-eab4-49a9-981c-42ba6afe35e6");

    public static UUID TENNIS_CLUB_ORGANIZATION_ID = UUID.fromString("d50faed6-9982-43d2-9dff-9bb41859ddcf");

    public static final UUID TENNIS_ZONE_ID_AS_UUID = UUID.fromString("359dfe3f-aaad-461c-87a7-08d9368584f1");

    public static final UUID TENNIS_FACILITY_ID_A1_AS_UUID = UUID.fromString("a3b6fbea-8fc6-4233-94a7-dd60e175c02c");

    private DomainTestFactory() {
    }

    public static OrganisationId createTennisClubOrganisationId() {
        return OrganisationId.with(TENNIS_CLUB_ORGANIZATION_ID);
    }

    public static ZoneId createTennisZoneId() {
        return ZoneId.with(TENNIS_ZONE_ID_AS_UUID);
    }

    public static Zone createBlankTennisZone() {
        return Zone.create(createTennisZoneId(), createManchesterTennisSiteId(), "Tennis");
    }

    public static Zone createTennisZoneWithFacility() {
        Zone zone = createBlankTennisZone();
        zone.addFacility(createA1TennisFacility());
        return zone;
    }

    public static FacilityId createA1TennisFacilityId() {
        return FacilityId.with(TENNIS_FACILITY_ID_A1_AS_UUID);
    }

    public static Facility createA1TennisFacility() {
        return Facility.create(createA1TennisFacilityId());
    }

    public static Organisation createTennisClubOrganisation() {
        return Organisation.create(createTennisClubOrganisationId());
    }

    public static SiteId createManchesterTennisSiteId() {
        return SiteId.with(MANCHESTER_TENNIS_CLUB_SITE);
    }

    public static Site createManchesterTennisSite() {
        return Site.create(createManchesterTennisSiteId(), createTennisClubOrganisationId());
    }
}
