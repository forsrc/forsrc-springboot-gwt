package com.forsrc.gwt.client.application.websocket;


import javax.inject.Inject;

import com.forsrc.gwt.client.application.websocket.composite.ChatMessageComposite;
import com.forsrc.gwt.client.websocket.MessageEvent;
import com.forsrc.gwt.client.websocket.WebSocket;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;

public class WebSocketView extends ViewImpl implements WebSocketPresenter.MyView {

    interface Binder extends UiBinder<Widget, WebSocketView> {
    }

    @UiField
    MaterialLabel searchLabel;

    public WebSocket socket;

    @UiField
    MaterialPanel chatPanel;

    @Inject
    WebSocketView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

    }

    @Override
    public void onSearch(String text) {

        searchLabel.setText("Home search -> " + text);
    }

    @Override
    protected void onAttach() {
        socket = new WebSocket("ws://localhost:8088/ws-myhtmlshell");

        socket.onmessage = (evt) -> {
            MessageEvent event = evt.cast();
            searchLabel.setText("socket onmessage: " + event.getData());
            return evt;
        };

        socket.onopen = (evt) -> {
            searchLabel.setText("socket open");
            return evt;
        };

        socket.onclose = (evt) -> {
            searchLabel.setText("socket closed");
            return evt;
        };


        chatPanel.add(new ChatMessageComposite("test", System.currentTimeMillis()));
    }

    @Override
    protected void onDetach() {
        if (socket != null) {
            socket.close();
            socket = null;
        }
    }
}
