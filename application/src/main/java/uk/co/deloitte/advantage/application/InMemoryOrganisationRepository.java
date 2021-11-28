package uk.co.deloitte.advantage.application;

import uk.co.deloitte.domain.Organisation;
import uk.co.deloitte.domain.OrganisationId;
import uk.co.deloitte.domain.IOrganisationRepository;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryOrganisationRepository implements IOrganisationRepository {

    private final Map<OrganisationId, Organisation> aggregates = new HashMap<>();

    public static InMemoryZoneRepository create() {
        return new InMemoryZoneRepository();
    }

    @Override
    public OrganisationId create(Organisation aggregate) {
        aggregates.put(aggregate.id(), aggregate);
        return aggregate.id();
    }

    @Override
    public void delete(OrganisationId id) {
        aggregates.remove(id);
    }

    @Override
    public Organisation read(OrganisationId id) {
        return aggregates.get(id);
    }

    @Override
    public void update(Organisation aggregate) {
        aggregates.put(aggregate.id(), aggregate);
    }
}
