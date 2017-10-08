package com.forsrc.gwt.client.application.table;


import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TableModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(TablePresenter.class, TablePresenter.MyView.class, TableView.class,
                TablePresenter.MyProxy.class);
    }
}
