package org.example.listener.entity;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class EntityEvent extends ApplicationEvent {

    @Getter
    private final String ACCESS_TYPE;

    public EntityEvent(Object entity, String accessType) {
        super(entity);
        ACCESS_TYPE = accessType;
    }
}
