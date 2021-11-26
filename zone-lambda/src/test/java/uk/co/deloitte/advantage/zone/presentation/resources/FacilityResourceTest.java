package uk.co.deloitte.advantage.zone.presentation.resources;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import uk.co.deloitte.advantage.zone.presentation.PresentationTestFactory;
import uk.co.deloitte.test.support.EqualityVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FacilityResourceTest implements EqualityVerifier<FacilityResource> {

    private final ObjectMapper mapper = new ObjectMapper();

    private final FacilityResource actual = PresentationTestFactory.createA1TennisFacilityResource();

    private static final String FACILITY_AS_JSON = "{\"id\":\"a3b6fbea-8fc6-4233-94a7-dd60e175c02c\"}";

    @Test
    void toJsonTest() throws JsonProcessingException, JSONException {
        JSONAssert.assertEquals(FACILITY_AS_JSON,
                mapper.writeValueAsString(actual), JSONCompareMode.STRICT);
    }

    @Test
    void fromJsonTest() throws JsonProcessingException {
        FacilityResource actual = mapper.readValue(FACILITY_AS_JSON, FacilityResource.class);
        assertEquals(PresentationTestFactory.createA1TennisFacilityResource(), actual);
    }

    @Test
    void toStringTest() {
        assertEquals("FacilityResource[id=a3b6fbea-8fc6-4233-94a7-dd60e175c02c]", actual.toString());
    }

    @Override
    public Class<? extends FacilityResource> typeClass() {
        return actual.getClass();
    }
}