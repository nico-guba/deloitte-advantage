package deloitte.advantage.application;

import uk.co.deloitte.domain.org.IOrganisationRepository;
import uk.co.deloitte.domain.org.Organisation;
import uk.co.deloitte.domain.org.OrganisationId;

public final class OrganisationRepositoryDouble extends InMemoryRepository<OrganisationId, Organisation> implements IOrganisationRepository {

    public static IOrganisationRepository empty() {return new OrganisationRepositoryDouble();}
}
