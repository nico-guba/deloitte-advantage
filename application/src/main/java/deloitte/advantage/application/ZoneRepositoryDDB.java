package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.ZoneTable;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.Set;
import java.util.UUID;

public class ZoneRepositoryDDB implements IZoneRepository {

    public ZoneRepositoryDDB(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    private final DynamoDBMapper mapper;

    @Override
    public ZoneId create(Zone aggregate) {
        ZoneTable table = new ZoneTable();
        table.setId(aggregate.id().toString());
        table.setSiteId(aggregate.getSiteId().toString());
        mapper.save(table);

        return aggregate.id();
    }

    @Override
    public void delete(ZoneId identifier) {

    }

    @Override
    public Zone read(ZoneId identifier) {
        ZoneTable table = mapper.load(ZoneTable.class, identifier.toString());
        return Zone.create(ZoneId.valueOf(UUID.fromString(table.getId())),
                SiteId.valueOf(UUID.fromString(table.getSiteId())));
    }

    @Override
    public void update(Zone aggregate) {

    }

    @Override
    public Set<Zone> findAll() {
        return null;
    }
}
