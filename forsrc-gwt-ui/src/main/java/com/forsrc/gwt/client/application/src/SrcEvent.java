package com.forsrc.gwt.client.application.src;

import java.io.Serializable;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class SrcEvent extends GwtEvent<SrcEvent.SrcEventHandler> {

    public interface SrcEventHandler extends EventHandler {
        void onSrcHandler(SrcEvent event);
    }

    public static Type<SrcEventHandler> TYPE = new Type<>();

    public SrcEvent() {
    }

    public static <T extends Serializable> void fire(HasHandlers source) {
        source.fireEvent(new SrcEvent());
    }

    @Override
    public Type<SrcEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SrcEventHandler handler) {
        handler.onSrcHandler(this);
    }

}
