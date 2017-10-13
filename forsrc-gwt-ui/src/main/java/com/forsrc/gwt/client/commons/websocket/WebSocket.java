package com.forsrc.gwt.client.commons.websocket;

import jsinterop.annotations.JsType;

import com.forsrc.gwt.client.commons.event.EventTarget;
import com.forsrc.gwt.client.commons.js.Function;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class WebSocket extends EventTarget {

    @JsProperty
    public Function onclose;

    @JsProperty
    public Function onerror;

    @JsProperty
    public Function onmessage;

    @JsProperty
    public Function onopen;

    @JsProperty
    public String url;

    @JsConstructor
    public WebSocket(String url) {

    }

    @JsConstructor
    public WebSocket(String url, String[] protocol) {

    }

    @JsMethod
    public native void send(String data);

    @JsMethod
    public native void close();

}
