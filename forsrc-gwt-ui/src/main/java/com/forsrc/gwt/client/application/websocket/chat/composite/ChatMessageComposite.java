package com.forsrc.gwt.client.application.websocket.chat.composite;

import java.util.Date;

import com.forsrc.gwt.client.application.websocket.chat.vo.ChatMessage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

import gwt.material.design.addins.client.bubble.MaterialBubble;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;

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
    MaterialPanel message;
    @UiField
    MaterialLabel time;
    @UiField
    MaterialBubble bubble;
    @UiField
    MaterialImage image;
    @Override
    protected void onLoad() {
        super.onLoad();
        this.message.add(new HTML(new SafeHtmlBuilder().appendEscapedLines(this.chatMessage.getMessage()).toSafeHtml()));
        DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy/MM/dd HH:mm:ss");
        this.time.setText(fmt.format(new Date(this.chatMessage.getTime())));
        boolean myself = this.chatMessage.isMyself();
        this.bubble.setFloat(myself ? Float.RIGHT : Float.LEFT);
        this.bubble.setPosition(myself ? Position.RIGHT : Position.LEFT);
        this.image.setFloat(myself ? Float.RIGHT : Float.LEFT);
        if (myself) {
            this.image.setBackgroundColor(Color.BLUE);
            this.bubble.setBackgroundColor(Color.BLUE);
            this.image.setMarginLeft(12);
        } else {
            this.image.setBackgroundColor(Color.RED);
            this.bubble.setBackgroundColor(Color.RED);
            this.image.setMarginRight(12);
        }
        if (this.chatMessage.getImage() != null) {
            this.image.setUrl(this.chatMessage.getImage());
        }
        this.bubble.reinitialize();
    }
}
