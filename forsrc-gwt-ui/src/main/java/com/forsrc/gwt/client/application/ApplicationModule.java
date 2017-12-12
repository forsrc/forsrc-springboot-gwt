package com.forsrc.gwt.client.application;

import com.forsrc.gwt.client.application.codemirror.CodemirrorModule;
import com.forsrc.gwt.client.application.demo.DemoModule;
import com.forsrc.gwt.client.application.error.ErrorModule;
import com.forsrc.gwt.client.application.home.HomeModule;
import com.forsrc.gwt.client.application.login.LoginModule;
import com.forsrc.gwt.client.application.plantuml.PlantumlModule;
import com.forsrc.gwt.client.application.table.TableModule;
import com.forsrc.gwt.client.application.websocket.chat.ChatModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {


        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        install(new ErrorModule());
        install(new HomeModule());
        install(new LoginModule());
        install(new ChatModule());
        install(new TableModule());
        install(new CodemirrorModule());
        install(new PlantumlModule());
        install(new DemoModule());
    }
}
