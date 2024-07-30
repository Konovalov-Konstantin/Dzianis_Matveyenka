package org.example.listener;

import org.example.listener.entity.EntityEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @EventListener
    public void acceptEntity(EntityEvent entityEvent){
        System.out.println("*** Вызвалось событие event: " + entityEvent);
    }
}
