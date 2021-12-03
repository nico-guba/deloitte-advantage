package uk.co.deloitte.domain.ddd;

import java.util.Optional;

public interface DomainRepository<K extends Identity<?>, T extends Aggregate<K>> {

    K create(T aggregate);

    void delete(K identifier);

    Optional<T> read(K identifier);

    void update(T aggregate);
}
