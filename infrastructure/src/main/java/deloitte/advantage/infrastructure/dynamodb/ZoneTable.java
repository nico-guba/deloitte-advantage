package deloitte.advantage.infrastructure.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.UUID;

@DynamoDBTable(tableName = "Zone")
public class ZoneTable {

    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBTypeConverted(converter = UUIDTypeConverter.class)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "SiteId")
    private String siteId;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @DynamoDBAttribute(attributeName = "Name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   public static class UUIDTypeConverter implements DynamoDBTypeConverter<String, UUID> {

        @Override
        public String convert(final UUID object) {
            return object.toString();
        }

        @Override
        public UUID unconvert(final String object) {
            return UUID.fromString(object);
        }
    }
}

