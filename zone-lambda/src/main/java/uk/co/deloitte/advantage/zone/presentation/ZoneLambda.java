package uk.co.deloitte.advantage.zone.presentation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.deloitte.advantage.application.InMemoryZoneRepository;
import uk.co.deloitte.advantage.zone.presentation.converters.ConverterRegistry;
import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.IZoneRepository;
import uk.co.deloitte.domain.Zone;
import uk.co.deloitte.domain.ZoneId;

import java.util.UUID;

public final class ZoneLambda implements RequestHandler<ZoneIdMessage, ZoneResource> {

    private final IZoneRepository zoneRepository = new InMemoryZoneRepository();

    private final ConverterRegistry converterRegistry = ConverterRegistry.create();

    public ZoneLambda() {
        ZoneId zoneId = ZoneId.valueOf(UUID.fromString("359dfe3f-aaad-461c-87a7-08d9368584f1"));
        zoneRepository.create(Zone.create(zoneId));
    }

    @Override
    public ZoneResource handleRequest(ZoneIdMessage msg, Context context) {
        context.getLogger().log(String.format("testing incoming msg %s ", msg));
        Zone zone = zoneRepository.read(ZoneId.valueOf(msg.id));
        return converterRegistry.toZoneResource(zone);
    }
}
