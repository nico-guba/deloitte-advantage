package deloitte.advantage.application;

import uk.co.deloitte.domain.facility.Facility;
import uk.co.deloitte.domain.facility.FacilityId;
import uk.co.deloitte.domain.facility.IFacilityRepository;

public class FacilityRepositoryDouble extends InMemoryRepository<FacilityId, Facility> implements IFacilityRepository {

    public static IFacilityRepository empty() {return new FacilityRepositoryDouble();}
}
