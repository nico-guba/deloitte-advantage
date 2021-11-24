package uk.co.deloitte.domain;

import uk.co.deloitte.domain.ddd.DomainRepository;

import java.util.Set;

public interface IZoneRepository extends DomainRepository<ZoneId, Zone> {

    Set<Zone> findAll();

}
