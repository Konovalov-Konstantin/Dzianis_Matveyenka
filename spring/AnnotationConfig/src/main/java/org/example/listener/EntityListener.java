package org.example.listener;

import org.example.listener.entity.EntityEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    // на основании аннотации @EventListener будет создан listener событий EntityEvent. Событие публикуется вызовом publishEvent (см.CompanyService.findById)
    @EventListener(condition = "#root.args[0].accessType == 'READ'")    // условия фильтрации, на какие события будет срабатывать отправка событий (здесь событие отправится подписчикам, если у первого арумента метода (entityEvent) accessType будет 'READ')
    public void acceptEntity(EntityEvent entityEvent) {
        System.out.println("*** Called event: " + entityEvent + ": " + entityEvent.getAccessType());
    }
}
