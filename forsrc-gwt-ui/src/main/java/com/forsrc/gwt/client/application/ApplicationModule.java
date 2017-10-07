package com.forsrc.gwt.client.application;

import com.forsrc.gwt.client.application.home.HomeModule;
import com.forsrc.gwt.client.application.login.LoginModule;
import com.forsrc.gwt.client.application.table.TableModule;
import com.forsrc.gwt.client.application.websocket.WebSocketModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {


        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        install(new HomeModule());
        install(new LoginModule());
        install(new WebSocketModule());
        install(new TableModule());
    }
}
