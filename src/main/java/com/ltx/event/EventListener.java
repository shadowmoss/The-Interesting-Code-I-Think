package com.ltx.event;

/**
 * @author ltx
 * @description:
 * @date 2022/9/29 15:11
 */
public interface EventListener{
    void handleEvent(AbstractEvent<?> event);
}
