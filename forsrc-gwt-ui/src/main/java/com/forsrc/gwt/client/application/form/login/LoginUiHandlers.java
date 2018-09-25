package com.forsrc.gwt.client.application.form.login;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LoginUiHandlers extends UiHandlers {

    public boolean checklogin(String email, String password);
}
