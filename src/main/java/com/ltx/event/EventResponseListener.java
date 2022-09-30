package com.ltx.event;

/**
 * @author ltx
 * @description:
 * @date 2022/9/29 15:35
 */
public interface EventResponseListener<T>{
    T handleEvent(AbstractEvent<?> eventSource);
}
