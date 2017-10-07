package com.forsrc.gwt.client.application.login;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
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
        //loginContainer.setMarginLeft(w);
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
        String url = GWT.getModuleBaseURL() + "#login";
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
