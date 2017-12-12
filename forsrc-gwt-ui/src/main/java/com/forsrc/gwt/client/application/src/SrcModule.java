package com.forsrc.gwt.client.application.src;


import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class SrcModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(SrcPresenter.class, SrcPresenter.MyView.class, SrcView.class,
                SrcPresenter.MyProxy.class);
    }
}
