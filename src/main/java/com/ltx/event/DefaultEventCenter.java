package com.ltx.event;


import java.util.HashSet;
import java.util.Set;

/**
 * @author ltx
 * @description:
 * @date 2022/9/29 15:17
 */
public class DefaultEventCenter implements EventCenter{

    Set<EventListener> eventListeners = new HashSet<>();


    @Override
    public void addEventListener(EventListener eventListener) {

    }

    @Override
    public void removeEventListener(EventListener eventListener) {

    }

    @Override
    public void notifyNoResponseEventListener(AbstractEvent<?> event) {
        for(EventListener item :eventListeners){
            item.handleEvent(event);
        }
    }

}
