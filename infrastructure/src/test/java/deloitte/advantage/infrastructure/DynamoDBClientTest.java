package deloitte.advantage.infrastructure;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.junit.jupiter.api.Test;
import uk.co.deloitte.domain.site.SiteId;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

class DynamoDBClientTest {

    /**
     * discovery test to figure out how to insert data into dynamodb
     */
    @Test
    void insertIntoDynamoDB() {
        AmazonDynamoDB client = AmazonDynamoDBClient.builder().withRegion(Regions.EU_WEST_2).build();
        DynamoDBMapper mapper = new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);

        Zone zone = Zone.create(ZoneId.randomId(), SiteId.randomId());

        mapper.save(ZoneTable.with(zone));
    }
}