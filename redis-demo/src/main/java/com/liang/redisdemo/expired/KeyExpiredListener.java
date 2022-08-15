package com.liang.redisdemo.expired;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * redis key 过期监听
 *
 * @author Mr.Liang
 * @date 2022/4/25 14:08
 */

public class KeyExpiredListener extends KeyExpirationEventMessageListener {
    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    public void onMessage(Message message, byte[] pattern) {
        System.out.println("过期key:" + message.toString());
    }
}
