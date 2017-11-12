package com.forsrc.gwt.client.application.login;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {
    interface Binder extends UiBinder<Widget, LoginView> {
    }


    @UiField
    MaterialContainer loginContainer;
    @UiField
    MaterialTextBox email;
    @UiField
    MaterialTextBox password;

    @Inject
    LoginView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        MaterialLoader.progress(true);
    }

    @Override
    protected void onAttach() {
        email.setLength(20);
        password.setLength(20);
    }

    @Override
    public void onSearch(String text) {
    }

    @Override
    public void onSideNavEvent(int w) {
        MaterialToast.fireToast("onSideNav");
        // loginContainer.setMarginLeft(w);
        Transition transition = Transition.fromStyleName(Transition.RUBBERBAND.getCssName());
        MaterialAnimation animation = new MaterialAnimation();
        animation.setTransition(transition);
        animation.setDelay(0);
        animation.setDuration(1000);
        animation.setInfinite(false);
        animation.animate(loginContainer);
    }

    @UiHandler("login")
    public void onLogin(ClickEvent clickEvent) {
        MaterialLoader.progress(true);
        getUiHandlers().login(email.getValue(), password.getValue());
    }
}
