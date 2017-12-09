package com.forsrc.gwt.client.application.plantuml;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialLabel;

public class PlantumlView extends ViewImpl implements PlantumlPresenter.MyView {
    interface Binder extends UiBinder<Widget, PlantumlView> {
    }

    @UiField
    MaterialLabel searchLabel;

    @Inject
    PlantumlView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void onSearch(String text) {

        searchLabel.setText("Home search -> " + text);
    }

}
