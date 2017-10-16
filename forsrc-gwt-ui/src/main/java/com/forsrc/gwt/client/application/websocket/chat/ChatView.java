package com.forsrc.gwt.client.application.websocket.chat;


import java.util.logging.Logger;

import javax.inject.Inject;

import com.forsrc.gwt.client.application.websocket.chat.composite.ChatMessageComposite;
import com.forsrc.gwt.client.application.websocket.chat.vo.ChatMessage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextArea;

public class ChatView extends ViewWithUiHandlers<ChatUiHandlers> implements ChatPresenter.MyView {

    interface Binder extends UiBinder<Widget, ChatView> {
    }

    Logger logger = Logger.getLogger(ChatView.class.getSimpleName());

    @UiField
    MaterialLabel searchLabel;



    @UiField
    MaterialPanel chatPanel;

    @UiField
    MaterialTextArea msg;

    @UiField
    MaterialButton send;

    @Inject
    ChatView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

    }

    @Override
    public void onSearch(String text) {

        searchLabel.setText("Home search -> " + text);
    }

    @Override
    protected void onAttach() {
        //msg.setLength(200);
        getUiHandlers().startWebSocket();
//        ChatMessage test1 = new ChatMessage(1, "my", true, "test", System.currentTimeMillis(), null);
//        chatPanel.add(new ChatMessageComposite(test1));
//        ChatMessage test2 = new ChatMessage(1, "you", false, "test too", System.currentTimeMillis(), null);
//        chatPanel.add(new ChatMessageComposite(test2));

    }

    @Override
    protected void onDetach() {
        getUiHandlers().stopWebSocket();
    }

    
    @Override
    public void onMessage(ChatMessage chatMessage) {
        this.chatPanel.add(new ChatMessageComposite(chatMessage));
    }

    @UiHandler("send")
    public void send(ClickEvent clickEvent) {
        MaterialLoader.showProgress(true);
        String msg = this.msg.getText();
        if (msg == "") {
            MaterialLoader.showProgress(false);
            return;
        }
        getUiHandlers().sendMessage(msg);
        ChatMessage chatMessage = new ChatMessage(0, "", true, msg, System.currentTimeMillis(), null);
        chatPanel.add(new ChatMessageComposite(chatMessage));
        MaterialLoader.showProgress(false);
        this.msg.setText("");
    }
}
