package com.forsrc.gwt.client.application.plantuml;


import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PlantumlModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(PlantumlPresenter.class, PlantumlPresenter.MyView.class, PlantumlView.class,
                PlantumlPresenter.MyProxy.class);
    }
}
