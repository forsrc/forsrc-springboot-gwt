package com.forsrc.gwt.client.application.error;

import javax.inject.Inject;

import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialLabel;

public class ErrorView extends ViewImpl implements ErrorPresenter.MyView {
    interface Binder extends UiBinder<Widget, ErrorView> {
    }

    @UiField
    MaterialLabel message;

    @Inject
    ErrorView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void onSearch(String text) {
    }

    @Override
    protected void onAttach() {
        message.setText("No error");
        final Storage storage = Storage.getLocalStorageIfSupported();
        if (storage != null) {
            String msg = storage.getItem("/error/message");
            String time = storage.getItem("/error/time");
            if (msg != null) {
                message.setText(time + " --> " + msg);
            }
        }
    }
}
