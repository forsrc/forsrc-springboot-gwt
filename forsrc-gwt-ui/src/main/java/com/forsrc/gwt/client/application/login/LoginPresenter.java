package com.forsrc.gwt.client.application.login;

import com.forsrc.gwt.client.application.ApplicationPresenter;
import com.forsrc.gwt.client.application.ApplicationView;
import com.forsrc.gwt.client.event.MyEvent;
import com.forsrc.gwt.client.event.MyEvent.MyEventData;
import com.forsrc.gwt.client.event.MyEvent.MyEventHandler;
import com.forsrc.gwt.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;


public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements MyEventHandler {

    interface MyView extends View {
        public void onSearch(String text);
        public void onSideNavEvent(int w);
    }

    @ProxyStandard
    @NameToken(NameTokens.LOGIN)
    interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    @Inject
    LoginPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onBind() {
        super.onBind();
        addRegisteredHandler(MyEvent.TYPE, this);
    }
    
    @Override
    protected void onReveal() {
        super.onReveal();
    }


    @Override
    public void onMyEventHandler(MyEvent event) {
        MyEventData data = event.getData();
        if (ApplicationView.TYPE_SEARECH.equals(data.getType())) {
            getView().onSearch((String)data.getData());
        } else if (ApplicationView.TYPE_SIDEBAR.equals(data.getType())) {
            getView().onSideNavEvent((int)data.getData());
        }
    }
}
