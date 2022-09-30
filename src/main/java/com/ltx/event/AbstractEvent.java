package com.ltx.event;

/**
 * @author ltx
 * @description:
 * @date 2022/9/29 15:11
 */
public abstract class AbstractEvent<T> {
    protected T eventSource;
    public AbstractEvent(T eventSource){
        this.eventSource = eventSource;
    }
    public T getEventSource(){
        return this.eventSource;
    }
}
