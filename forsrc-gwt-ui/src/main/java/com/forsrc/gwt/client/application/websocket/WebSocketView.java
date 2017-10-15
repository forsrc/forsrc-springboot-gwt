package com.forsrc.gwt.client.application.websocket;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.forsrc.gwt.client.application.websocket.composite.ChatMessageComposite;
import com.forsrc.gwt.client.application.websocket.vo.ChatMessage;
import com.forsrc.gwt.client.commons.event.JsErrorEvent;
import com.forsrc.gwt.client.commons.event.MessageEvent;
import com.forsrc.gwt.client.commons.websocket.Socket;
import com.forsrc.gwt.client.resources.i18n.Messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialToast;

public class WebSocketView extends ViewImpl implements WebSocketPresenter.MyView {

    interface Binder extends UiBinder<Widget, WebSocketView> {
    }

    Logger logger = Logger.getLogger(WebSocketView.class.getSimpleName());

    @UiField
    MaterialLabel searchLabel;

    public Socket socket;

    @UiField
    MaterialPanel chatPanel;

    @UiField
    MaterialTextArea msg;

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
        //msg.setLength(200);
        Storage storage = Storage.getLocalStorageIfSupported();
        String token = null;
        if (storage != null) {
            token = storage.getItem("/oauth/token/access_token");
        }
        socket = new Socket(messages.app_url_resource_ws() + "/ws/chat?access_token=" + token);

        socket.onmessage = (evt) -> {
            MessageEvent event = evt.cast();
            searchLabel.setText("socket onmessage: " + event.getData());
            MaterialToast.fireToast("socket onmessage: " + JsonUtils.stringify(event).toString());
            return evt;
        };

        socket.onopen = (evt) -> {
            searchLabel.setText("socket open");
            MaterialToast.fireToast("socket onopen: " + JsonUtils.stringify(evt.cast()).toString());
            return evt;
        };

        socket.onclose = (evt) -> {
            searchLabel.setText("socket closed");
            MaterialToast.fireToast("socket onopen: " + JsonUtils.stringify(evt.cast()).toString());
            return evt;
        };

        socket.onerror = (evt) -> {
            logger.log(Level.INFO, JsonUtils.stringify(evt.cast()).toString());
            searchLabel.setText("socket onerror");
            JsErrorEvent error = evt.cast();
            MaterialToast.fireToast("socket onerror: " + error.getType());
            return evt;
        };
        ChatMessage test1 = new ChatMessage(1, "my", true, "test", System.currentTimeMillis(), "");
        chatPanel.add(new ChatMessageComposite(test1));
        ChatMessage test2 = new ChatMessage(1, "you", false, "test too", System.currentTimeMillis(), "");
        chatPanel.add(new ChatMessageComposite(test2));

    }

    @Override
    protected void onDetach() {
        if (socket != null) {
            socket.close();
            socket = null;
        }
    }
}
