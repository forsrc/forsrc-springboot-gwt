package com.forsrc.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class SetPageTitleEvent extends GwtEvent<SetPageTitleEvent.SetPageTitleHandler> {

    public interface SetPageTitleHandler extends EventHandler {
        void onSetPageTitle(SetPageTitleEvent event);
    }

    public static final Type<SetPageTitleHandler> TYPE = new Type<>();

    private final String title;
    private final String description;
    private final String link;
    private final String specification;

    public SetPageTitleEvent(String title, String description, String link, String specification) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.specification = specification;
    }

    public static void fire(String title, String description, String link, String specification, HasHandlers source) {
        source.fireEvent(new SetPageTitleEvent(title, description, link, specification));
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getSpecification() {
        return specification;
    }

    @Override
    public Type<SetPageTitleHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SetPageTitleHandler handler) {
        handler.onSetPageTitle(this);
    }
}