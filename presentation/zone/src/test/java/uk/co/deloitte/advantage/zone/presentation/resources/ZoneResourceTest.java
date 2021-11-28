package uk.co.deloitte.advantage.zone.presentation.resources;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import uk.co.deloitte.advantage.zone.presentation.PresentationTestFactory;
import uk.co.deloitte.test.support.EqualityVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZoneResourceTest implements EqualityVerifier<ZoneResource> {

    public static final String ZONE_AS_JSON = "{\"id\":\"359dfe3f-aaad-461c-87a7-08d9368584f1\",\"facilities\":[{\"id\":\"a3b6fbea-8fc6-4233-94a7-dd60e175c02c\"}]}";

    private final ObjectMapper mapper = new ObjectMapper();

    private final ZoneResource actual = PresentationTestFactory.createTennisZoneResource();

    @Test
    void toJsonTest() throws JsonProcessingException, JSONException {
        JSONAssert.assertEquals(ZONE_AS_JSON,
                mapper.writeValueAsString(actual), JSONCompareMode.STRICT);
    }

    @Test
    void fromJsonTest() throws JsonProcessingException {
        ZoneResource actual = mapper.readValue(ZONE_AS_JSON, ZoneResource.class);
        assertEquals(PresentationTestFactory.createTennisZoneResource(), actual);
    }

    @Test
    void toStringTest() {
        assertEquals("ZoneResource[id=359dfe3f-aaad-461c-87a7-08d9368584f1, " +
                        "facilities=[FacilityResource[id=a3b6fbea-8fc6-4233-94a7-dd60e175c02c]]]",
                actual.toString());
    }


    @Override
    public Class<? extends ZoneResource> typeClass() {
        return actual.getClass();
    }
}