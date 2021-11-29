package deloitte.advantage.application;

import uk.co.deloitte.domain.IOrganisationRepository;
import uk.co.deloitte.domain.Organisation;
import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.ddd.ApplicationService;
import uk.co.deloitte.domain.site.ISiteRepository;
import uk.co.deloitte.domain.site.Site;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;

import java.util.Optional;


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
        Optional<Zone> zone = zoneRepository.read(zoneId);
        if(zone.isEmpty()) {
            throw new IllegalArgumentException(String.format("Zone not present for zone id=%s", zoneId));
        }
        Identity siteId = zone.get().getSiteId();
        Optional<Site> site = siteRepository.read(siteId);
        if(site.isEmpty()) {
            throw new IllegalArgumentException(String.format("Site not present for site id=%s", siteId));
        }

        Identity organisationId = site.get().getOrganisationId();
        Optional<Organisation> organisation = organisationRepository.read(organisationId);
        if(organisation.isEmpty()) {
            throw new IllegalArgumentException(String.format("Organisation not present for Organisation id=%s", organisationId));
        }
        return organisation.get();
    }

}
