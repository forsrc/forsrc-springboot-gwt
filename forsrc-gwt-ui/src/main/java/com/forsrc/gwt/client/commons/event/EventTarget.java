package com.forsrc.gwt.client.commons.event;

import static jsinterop.annotations.JsPackage.GLOBAL;

import com.forsrc.gwt.client.commons.js.Function;

import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = GLOBAL)
public class EventTarget {

    public native void addEventListener(String type, Function listener);

}
