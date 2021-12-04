package deloitte.advantage.application.zone;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import deloitte.advantage.infrastructure.dynamodb.ZoneTable;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * DynamoDB backed repository for Zone Aggregates
 */
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
        table.setId(aggregate.id().value());
        table.setSiteId(aggregate.getSiteId().toString());
        table.setName(aggregate.getName());
        return table;
    }

    @Override
    public void delete(ZoneId identifier) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(identifier.toString()));
        DynamoDBQueryExpression<ZoneTable> queryExpression = new DynamoDBQueryExpression<ZoneTable>()
                .withKeyConditionExpression("Id = :val1").withExpressionAttributeValues(eav);

        PaginatedQueryList<ZoneTable> latestReplies = mapper.query(ZoneTable.class, queryExpression);
        for(ZoneTable table : latestReplies) {
            mapper.delete(table);
        }
    }

    @Override
    public Optional<Zone> read(ZoneId identifier) {
        ZoneTable table = mapper.load(ZoneTable.class, identifier.value());
        if (table == null) return Optional.empty();

        Zone zone = Zone.create(ZoneId.with(table.getId()),
                SiteId.fromString(table.getSiteId()), table.getName());

        return Optional.of(zone);
    }

    @Override
    public void update(Zone aggregate) {
        ZoneTable table = convert(aggregate);
        mapper.save(table);
    }
}
