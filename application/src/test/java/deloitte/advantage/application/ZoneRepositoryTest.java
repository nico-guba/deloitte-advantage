package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.DynamoDBMapperFactory;
import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZoneRepositoryTest {

    private static DynamoDBMapper dynamoDBMapper = DynamoDBMapperFactory.createMapper();

    @DualTest
    void create_returns_expected_aggregate_id(IZoneRepository repo) {
        Identity zoneId = Identity.unique();

        Identity id = makeAggregate(repo, zoneId);

        assertEquals(zoneId, id);
    }

    @DualTest
    void delete(IZoneRepository repo) {
        Identity id = makeAggregate(repo);

        repo.delete(id);

        Optional<Zone> actual = readAggregate(repo, id);
        assertTrue(actual.isEmpty());
    }

    @DualTest
    void read_saved_aggregate(IZoneRepository repo) {
        Identity id = makeAggregate(repo);

        Optional<Zone> actual = readAggregate(repo, id);

        assertEquals(id, actual.get().id());
    }


    @DualTest
    void update(IZoneRepository repo) {
        Identity id = makeAggregate(repo);
        Zone zone = readAggregate(repo, id).get();
        assertEquals("Beach Volleyball", zone.getName());

        zone.setName("Women's Beach Volleyball");
        repo.update(zone);

        Zone actual = readAggregate(repo, id).get();
        assertEquals("Women's Beach Volleyball", actual.getName());
    }

    private Optional<Zone> readAggregate(IZoneRepository repo, Identity id) {
        return repo.read(id);
    }

    static Stream<IZoneRepository> implementationProvider() {
        return Stream.of(new InMemoryZoneRepository(), new ZoneRepositoryDDB(dynamoDBMapper));
    }

    private Identity makeAggregate(IZoneRepository repo, Identity zoneId) {
        Zone aggregate = Zone.create(zoneId, Identity.unique(), "Beach Volleyball");
        repo.create(aggregate);
        return aggregate.id();
    }

    private Identity makeAggregate(IZoneRepository repo) {
        return makeAggregate(repo, Identity.unique()
        );
    }
}