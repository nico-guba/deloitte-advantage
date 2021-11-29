package deloitte.advantage.application;

import uk.co.deloitte.domain.IOrganisationRepository;
import uk.co.deloitte.domain.Organisation;
import uk.co.deloitte.domain.OrganisationId;
import uk.co.deloitte.domain.ddd.ApplicationService;
import uk.co.deloitte.domain.site.ISiteRepository;
import uk.co.deloitte.domain.site.Site;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;


/**
 * WARNING! THIS NEEDS TESTS, IT IS JUST AN EXAMPLE TO SEE IF DOMAIN MODEL MAKES SENSE
 */
public final class ZoneService implements ApplicationService {

    private final ISiteRepository siteRepository;

    private final IZoneRepository zoneRepository;

    private final IOrganisationRepository organisationRepository;

    private ZoneService(ISiteRepository siteRepository, IZoneRepository zoneRepository, IOrganisationRepository organisationRepository) {
        this.siteRepository = siteRepository;
        this.zoneRepository = zoneRepository;
        this.organisationRepository = organisationRepository;
    }

    public static ZoneService create(ISiteRepository siteRepository, IZoneRepository zoneRepository, IOrganisationRepository organisationRepository) {
        return new ZoneService(siteRepository, zoneRepository, organisationRepository);
    }

    /**
     * TODO!! From the looks of it, the OrganisationId can also live within Zone, this will optimise
     * the query as it removes the need to then query the {@link SiteRepository}.
     */
    public Organisation findOrganisationFromZone(ZoneId zoneId) {
        Zone zone = zoneRepository.read(zoneId);
        if(zone == null) {
            throw new IllegalArgumentException(String.format("Zone not present for zone id=%s", zoneId));
        }
        SiteId siteId = zone.getSiteId();
        Site site = siteRepository.read(siteId);
        if(site == null) {
            throw new IllegalArgumentException(String.format("Site not present for site id=%s", siteId));
        }

        OrganisationId organisationId = site.getOrganisationId();
        Organisation organisation = organisationRepository.read(organisationId);
        if(organisation == null) {
            throw new IllegalArgumentException(String.format("Organisation not present for Organisation id=%s", organisationId));
        }
        return organisation;
    }

}
