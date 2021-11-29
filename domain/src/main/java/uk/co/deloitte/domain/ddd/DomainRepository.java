package uk.co.deloitte.domain.ddd;

import uk.co.deloitte.domain.zone.Zone;

import java.util.Optional;

public interface DomainRepository<K, T extends Aggregate<K>> {

    K create(T aggregate);

    void delete(K identifier);

    Optional<T> read(K identifier);

    void update(T aggregate);
}
