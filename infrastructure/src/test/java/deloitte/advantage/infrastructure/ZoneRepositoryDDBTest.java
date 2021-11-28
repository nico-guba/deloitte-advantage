package deloitte.advantage.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZoneRepositoryDDBTest {

    private static ZoneRepositoryDDBMapped repo;
    private static final DynamoDBFactory factory = new DynamoDBFactory();
    private static DynamoDBMapper db;

    @BeforeAll
    static void beforeAll() {
        db = factory.createMapper();
        repo = ZoneRepositoryDDBMapped.with(db);
    }

    @Test
    void create_uses_given_ZoneId() {
        ZoneId zoneId = ZoneId.randomId();
        Zone zone = repo.create(zoneId);

        assertEquals(zoneId, zone.id());
    }

    @Test
    void create_persists_on_backend() {
        ZoneId zoneId = ZoneId.randomId();
        Zone zone = repo.create(zoneId);

        ZoneTable actual = db.load(ZoneTable.class, zoneId.toString());

        assertEquals(zone.id().toString(), actual.getZoneId());
    }
}