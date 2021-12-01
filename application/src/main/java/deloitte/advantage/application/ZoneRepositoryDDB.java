package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.ZoneTable;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.Optional;

public class ZoneRepositoryDDB implements IZoneRepository {

    public ZoneRepositoryDDB(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    private final DynamoDBMapper mapper;

    @Override
    public ZoneId create(Zone aggregate) {
        ZoneTable table = convert(aggregate);
        mapper.save(table);

        return aggregate.id();
    }

    private ZoneTable convert(Zone aggregate) {
        ZoneTable table = new ZoneTable();
        table.setId(aggregate.id().toString());
        table.setSiteId(aggregate.getSiteId().toString());
        table.setName(aggregate.getName());
        return table;
    }

    @Override
    public void delete(ZoneId identifier) {
        ZoneTable table = mapper.load(ZoneTable.class, identifier.toString());
        mapper.delete(table);
    }

    @Override
    public Optional<Zone> read(ZoneId identifier) {
        ZoneTable table = mapper.load(ZoneTable.class, identifier.toString());
        if (table == null) return Optional.empty();

        Zone zone = Zone.create(ZoneId.fromString(table.getId()),
                SiteId.fromString(table.getSiteId()), table.getName());

        return Optional.of(zone);
    }

    @Override
    public void update(Zone aggregate) {
        ZoneTable table = convert(aggregate);
        mapper.save(table);
    }
}
