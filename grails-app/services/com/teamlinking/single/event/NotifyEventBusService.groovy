package com.teamlinking.single.event

import com.google.common.eventbus.AsyncEventBus
import org.springframework.beans.factory.InitializingBean

import java.util.concurrent.Executors

class NotifyEventBusService implements InitializingBean {

    AsyncEventBus bus
    RelationChainAddListenerService relationChainAddListenerService
    UserRegisterListenerService userRegisterListenerService

    def post(Object event){
        bus.post(event)
    }

    @Override
    void afterPropertiesSet() throws Exception {
        bus = new AsyncEventBus(Executors.newFixedThreadPool(10))
        //注册关系链上传事件处理
        bus.register(relationChainAddListenerService)
        //注册新用户注册事件处理
        bus.register(userRegisterListenerService)
    }
\
}
