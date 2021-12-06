package deloitte.advantage.application.zone;

import deloitte.advantage.application.InMemoryRepository;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

/**
 * This is just a fake inmemory repository atm, it doesn't mimic any behavior from http/rest or database errors
 * currently. We also rely on the {@link ZoneId} to be generated before added to this repository.
 */
public final class ZoneRepositoryInMemory extends InMemoryRepository<ZoneId, Zone> implements IZoneRepository {

    public static IZoneRepository empty() {
        return new ZoneRepositoryInMemory();
    }

}
