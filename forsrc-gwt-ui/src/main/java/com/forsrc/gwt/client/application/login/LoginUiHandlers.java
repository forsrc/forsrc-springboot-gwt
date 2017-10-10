package com.forsrc.gwt.client.application.login;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LoginUiHandlers extends UiHandlers {

    public void login(String email, String password);
}
