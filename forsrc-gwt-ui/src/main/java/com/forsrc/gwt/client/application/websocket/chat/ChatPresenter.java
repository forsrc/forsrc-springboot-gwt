package com.forsrc.gwt.client.application.websocket.chat;

import com.forsrc.gwt.client.application.ApplicationPresenter;
import com.forsrc.gwt.client.application.ApplicationView;
import com.forsrc.gwt.client.application.websocket.chat.vo.ChatMessage;
import com.forsrc.gwt.client.commons.event.JsErrorEvent;
import com.forsrc.gwt.client.commons.event.MessageEvent;
import com.forsrc.gwt.client.commons.websocket.Socket;
import com.forsrc.gwt.client.event.MyEvent;
import com.forsrc.gwt.client.event.MyEvent.MyEventData;
import com.forsrc.gwt.client.event.MyEvent.MyEventHandler;
import com.forsrc.gwt.client.gatekeeper.HasRolesGatekeeper;
import com.forsrc.gwt.client.gatekeeper.LoginedGatekeeper;
import com.forsrc.gwt.client.place.NameTokens;
import com.forsrc.gwt.client.resources.i18n.Messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.storage.client.Storage;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.GatekeeperParams;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.ui.MaterialToast;

public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy>
    implements MyEventHandler, ChatUiHandlers {

    interface MyView extends View, HasUiHandlers<ChatUiHandlers>{
        public void onSearch(String text);
        public void onMessage(ChatMessage chatMessage);
    }

    @ProxyStandard
    @NameToken(NameTokens.CHAT)
    //@UseGatekeeper(LoginedGatekeeper.class)
    @UseGatekeeper(HasRolesGatekeeper.class)
    @GatekeeperParams({"CHAT_ROLE"})
    interface MyProxy extends ProxyPlace<ChatPresenter> {
    }

    @Inject
    ChatPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        getView().setUiHandlers(this);
    }
    
    @Override
    protected void onBind() {
        super.onBind();
        addRegisteredHandler(MyEvent.TYPE, this);
    }


    @Override
    public void onMyEventHandler(MyEvent event) {
        MyEventData data = event.getData();
        if (ApplicationView.TYPE_SEARECH.equals(data.getType())) {
            getView().onSearch((String)data.getData());
        }
    }

    private Messages messages = GWT.create(Messages.class);
    private Socket socket = null;

    @Override
    public void startWebSocket() {
        if (socket != null) {
            return;
        }
        Storage storage = Storage.getLocalStorageIfSupported();
        String token = null;
        if (storage != null) {
            token = storage.getItem("/oauth/token/access_token");
        }
        socket = new Socket(messages.app_url_resource_ws() + "/ws/chat?access_token=" + token);

        socket.onmessage = (evt) -> {
            MessageEvent event = evt.cast();
            MaterialToast.fireToast("socket onmessage: " + event.getData());
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMessage(event.getData());
            chatMessage.setTime(System.currentTimeMillis());
            chatMessage.setMyself(false);
            getView().onMessage(chatMessage);
            return evt;
        };

        socket.onopen = (evt) -> {
            MaterialToast.fireToast("socket onopen: " + JsonUtils.stringify(evt.cast()).toString());
            return evt;
        };

        socket.onclose = (evt) -> {
            MaterialToast.fireToast("socket onclose: " + JsonUtils.stringify(evt.cast()).toString());
            return evt;
        };

        socket.onerror = (evt) -> {
            JsErrorEvent error = evt.cast();
            MaterialToast.fireToast("socket onerror: " + error.getType());
            return evt;
        };
    }

    @Override
    public void stopWebSocket() {
        if (socket == null) {
            return;
        }
        socket.close();
        socket = null;
    }
    @Override
    public void sendMessage(String message) {
        if (socket == null) {
            return;
        }
        socket.send(message);
    }
}
