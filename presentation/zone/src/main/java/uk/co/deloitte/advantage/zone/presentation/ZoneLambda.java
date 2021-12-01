package uk.co.deloitte.advantage.zone.presentation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import deloitte.advantage.application.InMemoryZoneRepository;
import uk.co.deloitte.advantage.zone.presentation.converters.ConverterRegistry;
import uk.co.deloitte.advantage.zone.presentation.resources.ZoneResource;
import uk.co.deloitte.domain.Identity;
import uk.co.deloitte.domain.facility.Facility;
import uk.co.deloitte.domain.zone.IZoneRepository;
import uk.co.deloitte.domain.zone.Zone;
import uk.co.deloitte.domain.zone.ZoneId;

import java.util.UUID;

public final class ZoneLambda implements RequestHandler<ZoneIdMessage, ZoneResource> {

    private final IZoneRepository zoneRepository = new InMemoryZoneRepository();

    private final ConverterRegistry converterRegistry = ConverterRegistry.create();

    public ZoneLambda() {
        ZoneId zoneId = ZoneId.fromString("359dfe3f-aaad-461c-87a7-08d9368584f1");
        Zone zone = Zone.create(zoneId, Identity.unique(), "Poodle Boxing");
        zone.addFacility(Facility.create(Identity.valueOf(UUID.randomUUID())));
        zoneRepository.create(zone);
    }

    @Override
    public ZoneResource handleRequest(ZoneIdMessage msg, Context context) {
        //Logs out incoming message within the lambda.
        context.getLogger().log(String.format("incoming msg %s ", msg));
        /* Gets the zone queried via the lambda, if it does not exist, an exception
        gets thrown, however the lambda will translate this to a pre-defined error schema within aws.

        Example:
        Output:
        {
            "errorMessage": "Error, zone does not exist by id 359dfe3f-aaad-461c-87a7-08d9368584f2",
            "errorType": "java.lang.IllegalArgumentException",
            "stackTrace": [
                "uk.co.deloitte.advantage.zone.presentation.ZoneLambda.handleRequest(ZoneLambda.java:30)",
                "uk.co.deloitte.advantage.zone.presentation.ZoneLambda.handleRequest(ZoneLambda.java:12)"
            ]
        }
        */
       Zone zone = zoneRepository.read(ZoneId.with(msg.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Error, zone does not exist by id " + msg.getId()));
        /*
         * returns the given zone queried if present, this object gets auto translated to json by aws library.
         *
         * Example Output:
         *
         * Output:
         * {
         *   "id": "359dfe3f-aaad-461c-87a7-08d9368584f1",
         *   "facilities": [
         *     {
         *       "id": "4fd794a1-fbf9-4ed6-9b9f-82c5acdd86b1"
         *     }
         *   ]
         * }
         */
        return converterRegistry.toZoneResource(zone);
    }
}
