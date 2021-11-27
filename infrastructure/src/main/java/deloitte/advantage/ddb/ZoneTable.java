package deloitte.advantage.ddb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import uk.co.deloitte.domain.Zone;

import java.util.Objects;
import java.util.StringJoiner;

@DynamoDBTable(tableName = "Zone")
public class ZoneTable {

    @DynamoDBHashKey(attributeName = "Id")
    private String zoneId;

    public String getZoneId() {
        return zoneId;
    }

    private ZoneTable(String zoneId) {
        this.zoneId = zoneId;
    }

    public static final ZoneTable with(Zone zone) {
        return new ZoneTable(zone.id().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZoneTable zoneTable = (ZoneTable) o;
        return Objects.equals(zoneId, zoneTable.zoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ZoneTable.class.getSimpleName() + "[", "]")
                .add("zoneId='" + zoneId + "'")
                .toString();
    }
}
