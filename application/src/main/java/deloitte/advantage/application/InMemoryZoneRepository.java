package deloitte.advantage.application;

import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

/**
 * This is just a fake inmemory repository atm, it doesn't mimic any behavior from http/rest or database errors
 * currently. We also rely on the {@link ZoneId} to be generated before added to this repository.
 */
public final class InMemoryZoneRepository extends InMemoryRepository<ZoneId, Zone> implements IZoneRepository {

    public static InMemoryZoneRepository empty() {
        return new InMemoryZoneRepository();
    }

}
