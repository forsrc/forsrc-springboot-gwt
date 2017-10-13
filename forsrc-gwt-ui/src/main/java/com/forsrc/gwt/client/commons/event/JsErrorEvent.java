package com.forsrc.gwt.client.commons.event;

import com.google.gwt.core.client.JavaScriptObject;

public class JsErrorEvent extends JavaScriptObject {

    protected JsErrorEvent() {
    }

    public native final String getDescription() /*-{
        return this.description;
    }-*/;

    public native final String getMessage() /*-{
        return this.message;
    }-*/;

    public native final String getName() /*-{
        return this.name;
    }-*/;

    public native final boolean isTrusted() /*-{
        return this.isTrusted;
    }-*/;

    public native final String getType() /*-{
        return this.type;
    }-*/;

    public native final JavaScriptObject getTarget() /*-{
        return this.target;
    }-*/;

    public native final String getTimeStamp() /*-{
        return this.timeStamp;
    }-*/;


}
