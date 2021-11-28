package deloitte.advantage.infrastructure;

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

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public ZoneTable(String zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * I am not sure I like this mapping stuff.  Too much in the middle way, ie writing an entirely new class just
     * to be able to do the same thing as the zone already does.  I leave this in for now and am implementing
     * one which does the same thing a bit more low-level.
     */
    public ZoneTable() {
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
