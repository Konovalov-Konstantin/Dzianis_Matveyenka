package org.example.listener.entity;

import org.springframework.context.ApplicationEvent;

public class EntityEvent extends ApplicationEvent {

    private final String AccessType;

    public EntityEvent(Object entity, String accessType) {
        super(entity);
        AccessType = accessType;
    }

    public String getAccessType() {
        return AccessType;
    }
}
