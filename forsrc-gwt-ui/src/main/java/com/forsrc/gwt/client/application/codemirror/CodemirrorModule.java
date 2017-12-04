package com.forsrc.gwt.client.application.codemirror;


import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CodemirrorModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(CodemirrorPresenter.class, CodemirrorPresenter.MyView.class, CodemirrorView.class,
                CodemirrorPresenter.MyProxy.class);
    }
}
