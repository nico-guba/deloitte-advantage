package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.DynamoDBMapperFactory;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZoneRepositoryTest {

    private static DynamoDBMapper dynamoDBMapper = DynamoDBMapperFactory.createMapper();

    @TestImplementations
    void create_returns_expected_aggregate_id(IZoneRepository repo) {
        ZoneId expected = ZoneId.unique();
        ZoneId id = createAndSave(repo, expected);

        execute(id, repo, (zoneId) -> assertEquals(expected, zoneId));
    }

    @TestImplementations
    void delete(IZoneRepository repo) {
        ZoneId id = createAndSave(repo);

        execute(id, repo, (zoneId) -> {
            repo.delete(zoneId);
            Exception e = assertThrows(NoSuchElementException.class, () -> readAggregate(repo, id));
            assertEquals("No value present", e.getMessage());
        });
    }

    @TestImplementations
    void read_saved_aggregate(IZoneRepository repo) {
        ZoneId id = createAndSave(repo);
        execute(id, repo, (zoneId) -> {
            Zone actual = readAggregate(repo, zoneId);
            assertEquals(zoneId, actual.id());
        });
    }

    @TestImplementations
    void update(IZoneRepository repo) {
        ZoneId id = createAndSave(repo);
        execute(id, repo, (zoneId) -> {
            Zone zone = readAggregate(repo, zoneId);
            assertEquals("Beach Volleyball", zone.getName());

            zone.setName("Women's Beach Volleyball");
            repo.update(zone);

            Zone actual = readAggregate(repo, zoneId);
            assertEquals("Women's Beach Volleyball", actual.getName());
        });
    }

    private Zone readAggregate(IZoneRepository repo, ZoneId id) {
        return repo.read(id).orElseThrow();
    }

    /**
     * Add the various implementations of the interface here, so they can all be examined against the same specification
     *
     * @return the implementations to test
     */
    static Stream<IZoneRepository> implementationProvider() {
        return Stream.of(new ZoneRepositoryDouble(), new ZoneRepositoryDDB(dynamoDBMapper));
    }

    private ZoneId createAndSave(IZoneRepository repo, ZoneId zoneId) {
        Zone aggregate = Zone.create(zoneId, SiteId.unique(), "Beach Volleyball");
        repo.create(aggregate);
        return aggregate.id();
    }

    private ZoneId createAndSave(IZoneRepository repo) {
        return createAndSave(repo, ZoneId.unique());
    }

    private void execute(ZoneId id, IZoneRepository repo, Consumer<ZoneId> consumer) {
        try {
            consumer.accept(id);
        } finally {
            if (id != null) {
                repo.delete(id);
            }
        }
    }
}