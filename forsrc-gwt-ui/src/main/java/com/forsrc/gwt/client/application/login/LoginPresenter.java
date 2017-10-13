package com.forsrc.gwt.client.application.login;

import com.forsrc.gwt.client.application.ApplicationPresenter;
import com.forsrc.gwt.client.application.ApplicationView;
import com.forsrc.gwt.client.event.MyEvent;
import com.forsrc.gwt.client.event.MyEvent.MyEventData;
import com.forsrc.gwt.client.event.MyEvent.MyEventHandler;
import com.forsrc.gwt.client.place.NameTokens;
import com.forsrc.gwt.client.resources.i18n.Messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Base64;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import gwt.material.design.client.base.validator.MessageFormat;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialToast;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements MyEventHandler, LoginUiHandlers {

    interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
        public void onSearch(String text);

        public void onSideNavEvent(int w);
    }

    @ProxyStandard
    @NameToken(NameTokens.LOGIN)
    interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private final PlaceManager placeManager;

    @Inject
    LoginPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager
            ) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        getView().setUiHandlers(this);
        this.placeManager = placeManager;
    }

    @Override
    protected void onBind() {
        super.onBind();
        addRegisteredHandler(MyEvent.TYPE, this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();
    }

    @Override
    public void onMyEventHandler(MyEvent event) {
        MyEventData data = event.getData();
        if (ApplicationView.TYPE_SEARECH.equals(data.getType())) {
            getView().onSearch((String) data.getData());
        } else if (ApplicationView.TYPE_SIDEBAR.equals(data.getType())) {
            getView().onSideNavEvent((int) data.getData());
        }
    }

    Messages messages = GWT.create(Messages.class);

    @Override
    public void login(String email, String password) {
        String url = MessageFormat.format("{1}/oauth/token?grant_type=password&username={2}&password={3}",
                messages.app_url_oauth(), email, password).toString();
        MaterialToast.fireToast(url);
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
        builder.setHeader("Content-Type", "application/json; charset=utf-8");
        // Zm9yc3JjOmZvcnNyYw==
        String authorization = new String(Base64.encode("forsrc:forsrc".getBytes()));
        builder.setHeader("Authorization", "Basic " + authorization);
        // String crsfCookie = Cookies.getCookie("XSRF-TOKEN");
        // MaterialToast.fireToast(crsfCookie);
        // builder.setHeader("X-XSRF-TOKEN", crsfCookie);

        // MaterialToast.fireToast(new
        // String(Base64.encode("forsrc:forsrc".getBytes())));
//        JSONObject params = new JSONObject();
//        params.put("client_id", new JSONString("forsrc"));
//        params.put("client_secret", new JSONString("forsrc"));
//        params.put("grant_type", new JSONString("password"));
//        params.put("username", new JSONString(email));
//        params.put("password", new JSONString(password));
        // MaterialToast.fireToast(params.toString());
        try {
            Request request = builder.sendRequest(null, new RequestCallback() {
                public void onError(Request request, Throwable exception) {
                    MaterialToast.fireToast("Error:" + exception.getMessage());
                }
                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        JSONObject data = new JSONObject(JsonUtils.safeEval(response.getText()));
                        GWT.log(data.toString());
                        Storage storage = Storage.getLocalStorageIfSupported();
                        String token = data.get("access_token").toString();
                        GWT.log("token: " + token);
                        token = URL.decodeQueryString(token);
                        token = token.substring(1, token.length() - 1);
                        if (storage != null) {
                            storage.setItem("/oauth/token", data.toString());
                            storage.setItem("/oauth/token/access_token", token);
                        }
                        MaterialToast.fireToast("Response:" + response.getStatusCode());
                        PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.WS).build();
                        placeManager.revealPlace(placeRequest);
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
