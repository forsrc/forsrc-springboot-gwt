package com.forsrc.gwt.client.application.websocket;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class WebSocketModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(WebSocketPresenter.class, WebSocketPresenter.MyView.class, WebSocketView.class,
                WebSocketPresenter.MyProxy.class);
    }
}
