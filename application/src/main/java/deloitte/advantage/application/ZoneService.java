package deloitte.advantage.application;

import uk.co.deloitte.domain.org.IOrganisationRepository;
import uk.co.deloitte.domain.org.Organisation;
import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.ddd.ApplicationService;
import uk.co.deloitte.domain.site.ISiteRepository;
import uk.co.deloitte.domain.site.Site;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;

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
     * the query as it removes the need to then query the {@link ISiteRepository}.
     */
    public Organisation findOrganisationFromZone(Identity zoneId) {
        Zone zone = zoneRepository.read(zoneId)
                .orElseThrow(() -> new IllegalArgumentException("Zone not present for zone id=" + zoneId));

        Site site = siteRepository.read(zone.getSiteId())
                .orElseThrow(() -> new IllegalArgumentException("Site not present for site id=" + zone.getSiteId()));

        return organisationRepository.read(site.getOrganisationId())
                .orElseThrow(() -> new IllegalArgumentException("Organisation not present for Organisation id=" + site.getOrganisationId()));
    }

}
