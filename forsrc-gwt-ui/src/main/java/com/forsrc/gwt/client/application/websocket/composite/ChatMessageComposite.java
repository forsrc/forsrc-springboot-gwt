package com.forsrc.gwt.client.application.websocket.composite;

import java.util.Date;

import com.forsrc.gwt.client.application.websocket.vo.ChatMessage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import gwt.material.design.addins.client.bubble.MaterialBubble;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;

public class ChatMessageComposite extends Composite {
    
    interface MyUiBinder extends UiBinder<HTMLPanel, ChatMessageComposite> {
    }

    private static MyUiBinder myUiBinder = GWT.create(MyUiBinder.class);

    private ChatMessage chatMessage;

    public ChatMessageComposite(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
        initWidget(myUiBinder.createAndBindUi(this));
    }

    @UiField
    MaterialLabel message;
    @UiField
    MaterialLabel time;
    @UiField
    MaterialBubble bubble;
    @UiField
    MaterialImage image;
    @Override
    protected void onLoad() {
        super.onLoad();
        this.message.setText(this.chatMessage.getMessage());
        DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy/MM/dd HH:mm:ss");
        this.time.setText(fmt.format(new Date(this.chatMessage.getTime())));
        boolean myself = this.chatMessage.isMyself();
        this.bubble.setPosition(myself ? Position.RIGHT : Position.LEFT);
        this.bubble.setFloat(myself ? Float.RIGHT : Float.LEFT);
        this.image.setFloat(myself ? Float.RIGHT : Float.LEFT);
        if (myself) {
            this.image.setMarginLeft(12);
        } else {
            this.image.setMarginRight(12);
        }

    }
}
