package com.forsrc.gwt.client.application.websocket.chat;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ChatModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ChatPresenter.class, ChatPresenter.MyView.class, ChatView.class,
                ChatPresenter.MyProxy.class);
    }
}
