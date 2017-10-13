package com.forsrc.gwt.client.commons.websocket;

import jsinterop.annotations.JsType;

import com.forsrc.gwt.client.commons.js.Function;
import com.google.gwt.core.client.JavaScriptObject;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;

@JsType(isNative = false, namespace = JsPackage.GLOBAL, name = "Socket")
public class Socket {

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

    @JsProperty
    private JavaScriptObject websocket;


    @JsConstructor
    public Socket(String url) {
        this.websocket = __getWebsocket(this, url);
    }

    private native JavaScriptObject __getWebsocket(Socket socket, String url) /*-{
        var ws = null;
        try {
            ws = $wnd.WebSocket ? new WebSocket(url) : new MozWebSocket(url);
        } catch (e) {
            socket.@com.forsrc.gwt.client.commons.websocket.Socket::onError(Lcom/google/gwt/core/client/JavaScriptObject;)(e.message);
            return;
        } finally {

        }
        var self = socket;
        //var socket = self.@com.forsrc.gwt.client.commons.websocket.Socket::websocket;
        ws.onopen = function (e) {
            socket.@com.forsrc.gwt.client.commons.websocket.Socket::onOpen(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
        }
        ws.onmessage = function (data) {
            socket.@com.forsrc.gwt.client.commons.websocket.Socket::onMessage(Lcom/google/gwt/core/client/JavaScriptObject;)(data);
        }
        ws.onclose = function (e) {
            socket.@com.forsrc.gwt.client.commons.websocket.Socket::onClose(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
        }
        ws.onerror = function (e) {
            socket.@com.forsrc.gwt.client.commons.websocket.Socket::onError(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
        }
        return ws;
    }-*/;

    @JsMethod
    public native void send(String data) /*-{
        var self = this;
        self.@com.forsrc.gwt.client.commons.websocket.Socket::websocket.send(data);
    }-*/;

    @JsMethod
    public native void close() /*-{
        var self = this;
        self.@com.forsrc.gwt.client.commons.websocket.Socket::websocket.close();
    }-*/;

    @JsMethod
    private void onOpen(JavaScriptObject event) {
         onopen.call(event);
    };

    @JsMethod
    private void onMessage(JavaScriptObject event) {
        onmessage.call(event);
    };

    @JsMethod
    private void onClose(JavaScriptObject event) {
        onclose.call(event);
    };

    @JsMethod
    private void onError(JavaScriptObject event) {
        onerror.call(event);
    };
}
