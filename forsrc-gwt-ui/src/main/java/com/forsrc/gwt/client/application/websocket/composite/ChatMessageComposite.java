package com.forsrc.gwt.client.application.websocket.composite;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import gwt.material.design.client.ui.MaterialLabel;

public class ChatMessageComposite extends Composite {
    
    interface MyUiBinder extends UiBinder<HTMLPanel, ChatMessageComposite> {
    }

    private static MyUiBinder myUiBinder = GWT.create(MyUiBinder.class);

    private String msg;
    private long timeLong;
    public ChatMessageComposite(String msg, long time) {
        this.msg = msg;
        this.timeLong = time;
        initWidget(myUiBinder.createAndBindUi(this));
    }

    @UiField
    MaterialLabel message;
    @UiField
    MaterialLabel time;
    @Override
    protected void onLoad() {
        super.onLoad();
        this.message.setText(this.msg);
        DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy/MM/dd HH:mm:ss");
        this.time.setText(fmt.format(new Date(this.timeLong)));
    }
}
