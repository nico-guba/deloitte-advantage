package deloitte.advantage.application;

import uk.co.deloitte.domain.site.Site;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.site.ISiteRepository;
import uk.co.deloitte.domain.zone.Zone;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemorySiteRepository implements ISiteRepository {

    private final Map<SiteId, Site> aggregates = new HashMap<>();

    public static InMemoryZoneRepository create() {
        return new InMemoryZoneRepository();
    }

    @Override
    public SiteId create(Site aggregate) {
        aggregates.put(aggregate.id(), aggregate);
        return aggregate.id();
    }

    @Override
    public void delete(SiteId id) {
        aggregates.remove(id);
    }

    @Override
    public Optional<Site> read(SiteId id) {
        return Optional.ofNullable(aggregates.get(id));
    }

    @Override
    public void update(Site aggregate) {
        aggregates.put(aggregate.id(), aggregate);
    }
}
