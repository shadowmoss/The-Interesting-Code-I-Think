package com.ltx.event;



/**
 * @author ltx
 * @description:
 * @date 2022/9/29 15:10
 */
// 事件中心保存事件监听器
public interface EventCenter {
    // 重载具有返回值类型的事件监听器
    void addEventListener(EventListener eventListener);
    void removeEventListener(EventListener eventListener);
    void notifyNoResponseEventListener(AbstractEvent<?> event);
}
