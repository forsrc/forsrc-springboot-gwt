package com.forsrc.gwt.client.application.login;

import javax.inject.Inject;

import com.forsrc.gwt.client.resources.i18n.Messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Base64;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

public class LoginView extends ViewImpl implements LoginPresenter.MyView {
    interface Binder extends UiBinder<Widget, LoginView> {
    }

    Messages messages = GWT.create(Messages.class);
    @UiField
    MaterialContainer loginContainer;
    @UiField
    MaterialTextBox email;
    @UiField
    MaterialTextBox password;

    @Inject
    LoginView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        MaterialLoader.showProgress(true);
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
    public void onSearch(ClickEvent clickEvent) {
        MaterialLoader.showProgress(true);

        String url = messages.app_url_oauth() + "/oauth/token?";
        url += "grant_type=password";
        url += "&username=" + email.getValue();
        url += "&password=" + password.getValue();
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
        builder.setHeader("Content-Type", "application/json; charset=utf-8");
        // Zm9yc3JjOmZvcnNyYw==
        String authorization = new String(Base64.encode("forsrc:forsrc".getBytes()));
        builder.setHeader("Authorization", "Basic " + authorization);
        builder.setIncludeCredentials(true);
        //MaterialToast.fireToast(new String(Base64.encode("forsrc:forsrc".getBytes())));
        JSONObject params = new JSONObject();
        params.put("grant_type", new JSONString("password"));
        params.put("username", new JSONString(email.getValue()));
        params.put("password", new JSONString(password.getValue()));
        //MaterialToast.fireToast(params.toString());
        try {
            Request request = builder.sendRequest(params.toString(), new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    MaterialToast.fireToast("Error:" + exception.getMessage());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        MaterialToast.fireToast("Response:" + response.getStatusCode() + response.getText());
                    } else {
                        MaterialToast.fireToast("Response:" + response.getStatusCode() + response.getText());
                    }
                    MaterialLoader.showProgress(false);
                }
            });
        } catch (RequestException e) {
            MaterialToast.fireToast("RequestException:" + e.getMessage());
            MaterialLoader.showProgress(false);
        }
    }
}
