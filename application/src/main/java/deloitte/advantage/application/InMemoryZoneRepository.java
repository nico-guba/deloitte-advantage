package deloitte.advantage.application;


import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This is just a fake inmemory repository atm, it doesn't mimic any behavior from http/rest or database errors
 * currently. We also rely on the {@link ZoneId} to be generated before added to this repository.
 */
public final class InMemoryZoneRepository implements IZoneRepository {

    private final Map<ZoneId, Zone> aggregates = new HashMap<>();

    public static InMemoryZoneRepository create() {
        return new InMemoryZoneRepository();
    }

    @Override
    public ZoneId create(Zone aggregate) {
        aggregates.put(aggregate.id(), aggregate);
        return aggregate.id();
    }

    @Override
    public void delete(ZoneId id) {
        aggregates.remove(id);
    }

    @Override
    public Zone read(ZoneId id) {
        return aggregates.get(id);
    }

    @Override
    public void update(Zone aggregate) {
        aggregates.put(aggregate.id(), aggregate);
    }

    @Override
    public Set<Zone> findAll() {
        return Set.copyOf(aggregates.values());
    }
}
