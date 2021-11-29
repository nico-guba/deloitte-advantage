package deloitte.advantage.application;

import uk.co.deloitte.domain.Organisation;
import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.IOrganisationRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryOrganisationRepository implements IOrganisationRepository {

    private final Map<Identity, Organisation> aggregates = new HashMap<>();

    public static InMemoryZoneRepository create() {
        return new InMemoryZoneRepository();
    }

    @Override
    public Identity create(Organisation aggregate) {
        aggregates.put(aggregate.id(), aggregate);
        return aggregate.id();
    }

    @Override
    public void delete(Identity id) {
        aggregates.remove(id);
    }

    @Override
    public Optional<Organisation> read(Identity id) {
        return Optional.ofNullable(aggregates.get(id));
    }

    @Override
    public void update(Organisation aggregate) {
        aggregates.put(aggregate.id(), aggregate);
    }
}
