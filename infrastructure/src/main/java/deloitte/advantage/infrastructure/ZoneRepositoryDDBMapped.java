package deloitte.advantage.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

/**
 * Dynamo DB backed Zone Repository
 */
public class ZoneRepositoryDDBMapped {

    DynamoDBMapper db;

    private ZoneRepositoryDDBMapped(DynamoDBMapper db) {
        this.db = db;
    }

    public static ZoneRepositoryDDBMapped with(DynamoDBFactory factory) {
      return new ZoneRepositoryDDBMapped(factory.createMapper());
    }

    public static ZoneRepositoryDDBMapped with(DynamoDBMapper db) {
        return new ZoneRepositoryDDBMapped(db);
    }

    public Zone create(ZoneId id) {
        Zone zone = Zone.create(id, SiteId.randomId());
        db.save(ZoneTable.with(zone));
        return zone;
    }
}
