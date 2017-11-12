package com.forsrc.gwt.client.application;

import com.forsrc.gwt.client.event.MyEvent;
import com.forsrc.gwt.client.event.SetPageTitleEvent;
import com.forsrc.gwt.client.event.MyEvent.MyEventData;
import com.forsrc.gwt.client.event.MyEvent.MyEventUiHandlers;
import com.forsrc.gwt.client.event.SetPageTitleEvent.SetPageTitleHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.LockInteractionEvent;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.client.proxy.Proxy;

import gwt.material.design.client.ui.MaterialLoader;

public class ApplicationPresenter
        extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements NavigationHandler, SetPageTitleHandler, MyEventUiHandlers {
 

    public interface MyView extends View, HasUiHandlers<MyEventUiHandlers> {
        void showLoading(boolean visibile);
        void setPageTitle(String title, String description, String link, String specification);
    }

    @ProxyStandard
    @NoGatekeeper
    public interface MyProxy extends Proxy<ApplicationPresenter> {

    }

    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    @Inject
    public ApplicationPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, RevealType.Root);
        MaterialLoader.progress(true);
        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        super.onBind();
        addRegisteredHandler(NavigationEvent.getType(), this);
        addRegisteredHandler(SetPageTitleEvent.TYPE, this);
    }

    /**
     * We display a short lock message whenever navigation is in progress.
     *
     * @param event The {@link LockInteractionEvent}.
     */
    @ProxyEvent
    public void onLockInteraction(LockInteractionEvent event) {
        getView().showLoading(event.shouldLock());
    }

    @Override
    public void onSetPageTitle(SetPageTitleEvent event) {
        getView().setPageTitle(event.getTitle(), event.getDescription(), event.getLink(), event.getSpecification());
    }

    @Override
    public void onNavigation(NavigationEvent navigationEvent) {
        Window.scrollTo(0, 0);
    }


    @Override
    public void onMyEventUiHandlers(MyEventData data) {
        MyEvent.fire(data, this);
    }

}
