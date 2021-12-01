package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.DynamoDBMapperFactory;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("OptionalGetWithoutIsPresent")
class ZoneRepositoryTest {

    private static DynamoDBMapper dynamoDBMapper = DynamoDBMapperFactory.createMapper();

    @TestImplementations
    void create_returns_expected_aggregate_id(IZoneRepository repo) {
        ZoneId zoneId = ZoneId.unique();

        ZoneId id = makeAggregate(repo, zoneId);

        assertEquals(zoneId, id);
    }

    @TestImplementations
    void delete(IZoneRepository repo) {
        ZoneId id = makeAggregate(repo);

        repo.delete(id);

        Optional<Zone> actual = readAggregate(repo, id);
        assertTrue(actual.isEmpty());
    }

    @TestImplementations
    void read_saved_aggregate(IZoneRepository repo) {
        ZoneId id = makeAggregate(repo);

        Optional<Zone> actual = readAggregate(repo, id);

        assertEquals(id, actual.get().id());
    }


    @TestImplementations
    void update(IZoneRepository repo) {
        ZoneId id = makeAggregate(repo);
        Zone zone = readAggregate(repo, id).get();
        assertEquals("Beach Volleyball", zone.getName());

        zone.setName("Women's Beach Volleyball");
        repo.update(zone);

        Zone actual = readAggregate(repo, id).get();
        assertEquals("Women's Beach Volleyball", actual.getName());
    }

    private Optional<Zone> readAggregate(IZoneRepository repo, ZoneId id) {
        return repo.read(id);
    }

    /**
     * Add the various implementations of the interface here, so they can all be examined against the same specification
     * @return the implementations to test
     */
    static Stream<IZoneRepository> implementationProvider() {
        return Stream.of(new InMemoryZoneRepository(), new ZoneRepositoryDDB(dynamoDBMapper));
    }

    private ZoneId makeAggregate(IZoneRepository repo, ZoneId zoneId) {
        Zone aggregate = Zone.create(zoneId, SiteId.unique(), "Beach Volleyball");
        repo.create(aggregate);
        return aggregate.id();
    }

    private ZoneId makeAggregate(IZoneRepository repo) {
        return makeAggregate(repo, ZoneId.unique());
    }
}