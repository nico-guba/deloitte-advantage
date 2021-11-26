package uk.co.deloitte.advantage.zone.presentation;

import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.core.JsonProcessingException;
import com.amazonaws.lambda.thirdparty.com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.deloitte.advantage.application.InMemoryZoneRepository;
import uk.co.deloitte.domain.IZoneRepository;
import uk.co.deloitte.domain.Zone;
import uk.co.deloitte.domain.ZoneId;

import java.util.UUID;

public final class ZoneLambda implements RequestHandler<ZoneIdMessage, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    private final IZoneRepository zoneRepository = new InMemoryZoneRepository();

    public ZoneLambda() {
        ZoneId zoneId = ZoneId.valueOf(UUID.fromString("359dfe3f-aaad-461c-87a7-08d9368584f1"));
        zoneRepository.create(Zone.create(zoneId));
    }

    @Override
    public String handleRequest(ZoneIdMessage msg, Context context) {
        try {
            context.getLogger().log(String.format("msg %s ", msg));
            ZoneId zoneId = ZoneId.valueOf(msg.id);
            return mapper.writeValueAsString(zoneId);
        } catch (JsonProcessingException e) {
            return "Error parsing json.";
        }
    }
}
