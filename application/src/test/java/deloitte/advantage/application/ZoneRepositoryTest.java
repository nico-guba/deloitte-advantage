package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.DynamoDBMapperFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ZoneRepositoryTest {

    private static DynamoDBMapper  dynamoDBMapper = DynamoDBMapperFactory.createMapper();

    @ParameterizedTest
    @MethodSource("implementationProvider")
    void create_returns_expected_aggregate_id(IZoneRepository repo) {
        ZoneId zoneId = ZoneId.randomId();
        Zone aggregate = Zone.create(zoneId, SiteId.randomId());

        ZoneId aggregateId = repo.create(aggregate);

        assertEquals(zoneId, aggregateId);
    }

    @Test
    void delete() {
    }

    @Test
    void read() {
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