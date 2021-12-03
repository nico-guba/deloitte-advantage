package deloitte.advantage.application;

import uk.co.deloitte.domain.org.IOrganisationRepository;
import uk.co.deloitte.domain.org.Organisation;
import uk.co.deloitte.domain.org.OrganisationId;

public final class InMemoryOrganisationRepository extends InMemoryRepository<OrganisationId, Organisation> implements IOrganisationRepository {

    public static InMemoryZoneRepository empty() {
        return new InMemoryZoneRepository();
    }
}
