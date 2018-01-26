package com.forsrc.gwt.client.application.demo;


import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class DemoModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(DemoPresenter.class, DemoPresenter.MyView.class, DemoView.class,
                DemoPresenter.MyProxy.class);
    }
}
