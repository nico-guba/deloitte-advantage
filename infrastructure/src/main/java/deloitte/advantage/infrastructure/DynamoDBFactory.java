package deloitte.advantage.infrastructure;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class DynamoDBFactory {

    public DynamoDBMapper createMapper() {
        AmazonDynamoDB client = AmazonDynamoDBClient.builder().withRegion(Regions.EU_WEST_2).build();
        return new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
    }
}
