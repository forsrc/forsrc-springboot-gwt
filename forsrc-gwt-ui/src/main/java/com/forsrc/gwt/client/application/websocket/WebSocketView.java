package com.forsrc.gwt.client.application.websocket;


import javax.inject.Inject;

import com.forsrc.gwt.client.application.websocket.composite.ChatMessageComposite;
import com.forsrc.gwt.client.commons.websocket.MessageEvent;
import com.forsrc.gwt.client.commons.websocket.WebSocket;
import com.forsrc.gwt.client.resources.i18n.Messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.storage.client.Storage;
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

    Messages messages = GWT.create(Messages.class);

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
        Storage storage = Storage.getLocalStorageIfSupported();
        String token = null;
        if (storage != null) {
            token = storage.getItem("/oauth/token/access_token");
        }
        socket = new WebSocket(messages.app_url_resource_ws() + "/ws/chat?access_token=" + token);

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
