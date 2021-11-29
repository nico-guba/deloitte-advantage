package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.DynamoDBMapperFactory;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ZoneRepositoryTest {

    private static DynamoDBMapper dynamoDBMapper = DynamoDBMapperFactory.createMapper();

    @DualTest
    void create_returns_expected_aggregate_id(IZoneRepository repo) {
        ZoneId zoneId = ZoneId.randomId();

        ZoneId id = makeAggregate(repo, zoneId);

        assertEquals(zoneId, id);
    }

    @DualTest
    void delete(IZoneRepository repo) {
        ZoneId id = makeAggregate(repo);

        repo.delete(id);

        Optional<Zone> actual = readAggregate(repo, id);
        assertTrue(actual.isEmpty());
    }

    @DualTest
    void read_saved_aggregate(IZoneRepository repo) {
        ZoneId id = makeAggregate(repo);

        Optional<Zone> actual = readAggregate(repo, id);

        assertEquals(id, actual.get().id());
    }


    @DualTest
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

    static Stream<IZoneRepository> implementationProvider() {
        return Stream.of(new InMemoryZoneRepository(), new ZoneRepositoryDDB(dynamoDBMapper));
    }

    private ZoneId makeAggregate(IZoneRepository repo, ZoneId zoneId) {
        Zone aggregate = Zone.create(zoneId, SiteId.randomId(), "Beach Volleyball");
        repo.create(aggregate);
        return aggregate.id();
    }

    private ZoneId makeAggregate(IZoneRepository repo) {
        return makeAggregate(repo, ZoneId.randomId());
    }
}