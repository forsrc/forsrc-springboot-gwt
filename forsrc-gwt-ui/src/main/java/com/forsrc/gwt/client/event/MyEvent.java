package com.forsrc.gwt.client.event;

import java.io.Serializable;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.gwtplatform.mvp.client.UiHandlers;

public class MyEvent extends GwtEvent<MyEvent.MyEventHandler> {

    public interface MyEventHandler extends EventHandler {
        void onMyEventHandler(MyEvent event);
    }

    public interface MyEventUiHandlers extends UiHandlers {
        void onMyEventUiHandlers(MyEventData data);
    }

    public static Type<MyEventHandler> TYPE = new Type<>();


    private final MyEventData data;


    public MyEvent(MyEventData data) {
        this.data = data;
    }

    public static <T extends Serializable> void fire(MyEventData data, HasHandlers source) {
        source.fireEvent(new MyEvent(data));
    }

    public MyEventData getData() {
        return this.data;
    }
 
    @Override
    public Type<MyEventHandler> getAssociatedType() {
        return TYPE;
    }

 
    @Override
    protected void dispatch(MyEventHandler handler) {
        handler.onMyEventHandler(this);
    }

    public static class MyEventData implements Serializable{
        private static final long serialVersionUID = 8918874508621448576L;
        private String type;
        private Object data;
        public MyEventData(String type, Object data) {
            super();
            this.type = type;
            this.data = data;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public Object getData() {
            return data;
        }
        public void setData(Object data) {
            this.data = data;
        }
        @Override
        public String toString() {
            return "MyEventData [type=" + type + ", data=" + data + "]";
        }
        
    }




}
