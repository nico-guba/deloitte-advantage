package deloitte.advantage.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import deloitte.advantage.infrastructure.dynamodb.ZoneTable;
import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class ZoneRepositoryDDB implements IZoneRepository {

    public ZoneRepositoryDDB(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    private final DynamoDBMapper mapper;

    @Override
    public Identity create(Zone aggregate) {
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
    public void delete(Identity identifier) {
        ZoneTable table = mapper.load(ZoneTable.class, identifier.toString());
        mapper.delete(table);
    }

    @Override
    public Optional<Zone> read(Identity identifier) {
        ZoneTable table = mapper.load(ZoneTable.class, identifier.toString());
        if (table == null) return Optional.empty();

        Zone zone = Zone.create(Identity.valueOf(UUID.fromString(table.getId())),
                Identity.valueOf(UUID.fromString(table.getSiteId())), table.getName());

        return Optional.of(zone);
    }

    @Override
    public void update(Zone aggregate) {
        ZoneTable table = convert(aggregate);
        mapper.save(table);
    }
}
