package com.forsrc.gwt.client.application.demo;

import com.forsrc.gwt.client.application.ApplicationPresenter;
import com.forsrc.gwt.client.application.ApplicationView;
import com.forsrc.gwt.client.application.demo.DemoEvent.DemoEventHandler;
import com.forsrc.gwt.client.event.MyEvent;
import com.forsrc.gwt.client.event.MyEvent.MyEventData;
import com.forsrc.gwt.client.event.MyEvent.MyEventHandler;
import com.forsrc.gwt.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.ui.MaterialToast;

public class DemoPresenter extends Presenter<DemoPresenter.MyView, DemoPresenter.MyProxy>
        implements MyEventHandler, DemoEventHandler, DemoUiHandlers {

    interface MyView extends View, HasUiHandlers<DemoUiHandlers>  {
        public void onSearch(String text);
    }

    @ProxyStandard
    @NameToken(NameTokens.DEMO)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<DemoPresenter> {
    }

    @Inject
    DemoPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        super.onBind();
        addRegisteredHandler(MyEvent.TYPE, this);
    }

    @Override
    public void onMyEventHandler(MyEvent event) {
        MyEventData data = event.getData();
        if (ApplicationView.TYPE_SEARECH.equals(data.getType())) {
            getView().onSearch((String) data.getData());
        }
    }

    @Override
    public void onDemoHandler(DemoEvent event) {
        MaterialToast.fireToast("onDemoHandler: " + event);
    }
}
