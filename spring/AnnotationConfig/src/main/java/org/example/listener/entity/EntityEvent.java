package org.example.listener.entity;

import org.springframework.context.ApplicationEvent;

public class EntityEvent extends ApplicationEvent { // объект 'событие'. Нужно унаследоваться от ApplicationEvent

    private final AccessType accessType;

    public EntityEvent(Object entity, AccessType accessType) {
        super(entity);
        this.accessType = accessType;
    }

    public AccessType getAccessType() {
        return accessType;
    }
}
