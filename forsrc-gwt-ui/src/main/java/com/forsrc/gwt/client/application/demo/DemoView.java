package com.forsrc.gwt.client.application.demo;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;



public class DemoView extends ViewWithUiHandlers<DemoUiHandlers>  implements DemoPresenter.MyView {
    interface Binder extends UiBinder<Widget, DemoView> {
    }


    @Inject
    DemoView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void onSearch(String text) {

    }

}
