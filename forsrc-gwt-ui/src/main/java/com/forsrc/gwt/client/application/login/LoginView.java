package com.forsrc.gwt.client.application.login;

import javax.inject.Inject;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import com.forsrc.gwt.client.resources.i18n.Messages;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
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
import com.google.gwt.json.client.JSONValue;
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
        test();
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
        MaterialLoader.showProgress(true);
        login(email.getValue(), password.getValue());
    }

    private void login(String email, String password) {
        String url = messages.app_url_oauth() + "/oauth/token";
        url += "?grant_type=password&client_id=forsrc&client_secret=forsrc";
        url += "&username=" + email;
        url += "&password=" + password;

        url = "http://localhost:9999/uaa/oauth/token?grant_type=password&username=forsrc@gmail.com&password=forsrc";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
        builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
        // builder.setHeader("Accept", RequestFactory.JSON_CONTENT_TYPE_UTF8);

        // Zm9yc3JjOmZvcnNyYw==
        String authorization = new String(Base64.encode("forsrc:forsrc".getBytes()));
        builder.setHeader("Authorization", "Basic " + authorization);

        // MaterialToast.fireToast(new
        // String(Base64.encode("forsrc:forsrc".getBytes())));
        JSONObject params = new JSONObject();

        params.put("client_id", new JSONString("forsrc"));
        params.put("client_secret", new JSONString("forsrc"));
        params.put("grant_type", new JSONString("password"));
        params.put("username", new JSONString(email));
        params.put("password", new JSONString(password));
        params.put("_method", new JSONString("POST"));
        // MaterialToast.fireToast(params.toString());
        builder.setRequestData(params.toString());
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    MaterialToast.fireToast("Error:" + exception.getMessage());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        MaterialToast.fireToast("Response:" + response.getStatusCode() + response.getText());
                    } else {
                        MaterialToast.fireToast("Response:" + response.getStatusCode());
                    }
                    MaterialLoader.showProgress(false);
                }
            });
        } catch (RequestException e) {
            MaterialToast.fireToast("RequestException:" + e.getMessage());
            MaterialLoader.showProgress(false);
        }
    }

    public void test() {
        String url = messages.app_url_oauth() + "/test";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    MaterialToast.fireToast("Error:" + exception.getMessage());
                }

                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        MaterialToast.fireToast("Response:" + response.getStatusCode() + response.getText());
                    } else {
                        MaterialToast.fireToast("Response:" + response.getStatusCode());
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
