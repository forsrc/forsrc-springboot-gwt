package com.forsrc.gwt.client.commons.websocket;

import static jsinterop.annotations.JsPackage.GLOBAL;

import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = GLOBAL)
public class EventTarget {

    public native void addEventListener(String type, Function listener);

}
