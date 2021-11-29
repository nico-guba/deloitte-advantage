package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.DynamoDBMapperFactory;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ZoneRepositoryTest {

    private static DynamoDBMapper  dynamoDBMapper = DynamoDBMapperFactory.createMapper();

    Identity zoneId = Identity.randomId();

    @DualTest
    void create_returns_expected_aggregate_id(IZoneRepository repo) {
        Zone aggregate = makeAggregate();

        Identity aggregateId = saveAggregate(repo, aggregate);

        assertEquals(zoneId, aggregateId);
    }

    private Identity saveAggregate(IZoneRepository repo, Zone aggregate) {
        return repo.create(aggregate);
    }

    private Zone makeAggregate() {
        return Zone.create(zoneId, Identity.randomId());
    }

    @DualTest
    void delete(IZoneRepository repo) {
        Zone aggregate = makeAggregate();
        saveAggregate(repo, aggregate);

        repo.delete(zoneId);

        Optional<Zone> actual = readAggregate(repo);
        assertTrue(actual.isEmpty());
    }

    @DualTest
    void read_saved_aggregate(IZoneRepository repo) {
        Zone expected = makeAggregate();
        saveAggregate(repo, expected);

        Optional<Zone> actual = readAggregate(repo);

        assertEquals(expected, actual.get());
    }

    private Optional<Zone> readAggregate(IZoneRepository repo) {
        return repo.read(zoneId);
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    static Stream<IZoneRepository> implementationProvider() {
        return Stream.of(new InMemoryZoneRepository(), new ZoneRepositoryDDB(dynamoDBMapper));
    }


}