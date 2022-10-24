package com.food.ordering.system.domain.event;

/**
 * @author kany
 */
public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);

}
