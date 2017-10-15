package com.forsrc.gwt.client.application.websocket.chat;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ChatUiHandlers extends UiHandlers {

    public void startWebSocket();

    public void sendMessage(String message);

    public void stopWebSocket();
}
