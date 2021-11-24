package uk.co.deloitte.domain.ddd;

public interface DomainEventDispatcher {

    <D extends DomainEvent> void publish(D event);

}
