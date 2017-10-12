package com.forsrc.gwt.client.commons.websocket;

import com.google.gwt.core.client.JavaScriptObject;

public class CloseEvent extends JavaScriptObject {

    protected CloseEvent() {

    }

    public native final int getCode()/*-{
        return this.code;
    }-*/;

}
