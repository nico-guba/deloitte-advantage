package deloitte.advantage.application;

import uk.co.deloitte.domain.ddd.Aggregate;
import uk.co.deloitte.domain.ddd.Identity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository<K extends Identity<?>, V extends Aggregate<?>> {

    private final Map<K, V> aggregates = new HashMap<>();

    @SuppressWarnings("unchecked")
    public K create(V aggregate) {
        K id = (K) aggregate.id();
        aggregates.put(id, aggregate);
        return id;
    }

    public void delete(K id) {
        aggregates.remove(id);
    }

    public Optional<V> read(K id) {
        return Optional.ofNullable(aggregates.get(id));
    }

    @SuppressWarnings("unchecked")
    public void update(V aggregate) {
        aggregates.put((K) aggregate.id(), aggregate);
    }
}
