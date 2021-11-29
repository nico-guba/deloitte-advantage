package deloitte.advantage.application;

import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.site.Site;
import uk.co.deloitte.domain.site.ISiteRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemorySiteRepository implements ISiteRepository {

    private final Map<Identity, Site> aggregates = new HashMap<>();

    public static InMemoryZoneRepository create() {
        return new InMemoryZoneRepository();
    }

    @Override
    public Identity create(Site aggregate) {
        aggregates.put(aggregate.id(), aggregate);
        return aggregate.id();
    }

    @Override
    public void delete(Identity id) {
        aggregates.remove(id);
    }

    @Override
    public Optional<Site> read(Identity id) {
        return Optional.ofNullable(aggregates.get(id));
    }

    @Override
    public void update(Site aggregate) {
        aggregates.put(aggregate.id(), aggregate);
    }
}
