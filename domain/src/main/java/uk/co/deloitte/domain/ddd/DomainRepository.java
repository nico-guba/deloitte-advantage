package uk.co.deloitte.domain.ddd;

public interface DomainRepository<K, T extends Aggregate<K>> {

    K create(T aggregate);

    void delete(K identifier);

    T read(K identifier);

    void update(T aggregate);
}
