package uk.co.deloitte.advantage.presentation;

import uk.co.deloitte.advantage.application.InMemoryZoneRepository;
import uk.co.deloitte.domain.IZoneRepository;

public final class ZoneLambdaConfig {

    private final IZoneRepository zoneRepository = new InMemoryZoneRepository();

    public IZoneRepository getZoneRepository() {
        return zoneRepository;
    }
}
