package deloitte.advantage.application;

import uk.co.deloitte.domain.site.ISiteRepository;
import uk.co.deloitte.domain.site.Site;
import uk.co.deloitte.domain.site.SiteId;

public final class SiteRepositoryDouble extends InMemoryRepository<SiteId, Site> implements ISiteRepository {

    public static ISiteRepository empty() {
        return new SiteRepositoryDouble();
    }
}
