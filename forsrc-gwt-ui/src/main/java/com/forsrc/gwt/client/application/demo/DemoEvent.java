package com.forsrc.gwt.client.application.demo;

import java.io.Serializable;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.gwtplatform.mvp.client.UiHandlers;

public class DemoEvent extends GwtEvent<DemoEvent.DemoEventHandler> {

    public interface DemoEventHandler extends EventHandler {
        void onDemoHandler(DemoEvent event);
    }

//    public interface DemoUiHandlers extends UiHandlers {
//        void onDemoUiHandlers();
//    }

    public static Type<DemoEventHandler> TYPE = new Type<>();

    public DemoEvent() {
    }

    public static <T extends Serializable> void fire(HasHandlers source) {
        source.fireEvent(new DemoEvent());
    }

    @Override
    public Type<DemoEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DemoEventHandler handler) {
        handler.onDemoHandler(this);
    }

}
