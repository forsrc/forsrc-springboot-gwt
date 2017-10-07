package com.forsrc.gwt.client.application.websocket;

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

import com.forsrc.gwt.client.websocket.MessageEvent;
import com.forsrc.gwt.client.websocket.WebSocket;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialLabel;

public class WebSocketView extends ViewImpl implements WebSocketPresenter.MyView {

    interface Binder extends UiBinder<Widget, WebSocketView> {
    }

    @UiField
    MaterialLabel searchLabel;

    public WebSocket socket;

    @Inject
    WebSocketView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

    }

    @Override
    public void onSearch(String text) {

        searchLabel.setText("Home search -> " + text);
    }

    @Override
    protected void onAttach() {
        socket = new WebSocket("ws://localhost:8088/ws-myhtmlshell");

        socket.onmessage = (evt) -> {
            MessageEvent event = evt.cast();
            searchLabel.setText("socket onmessage: " + event.getData());
            return evt;
        };

        socket.onopen = (evt) -> {
            searchLabel.setText("socket open");
            return evt;
        };

        socket.onclose = (evt) -> {
            searchLabel.setText("socket closed");
            return evt;
        };
    }

    @Override
    protected void onDetach() {
        if (socket != null) {
            socket.close();
            socket = null;
        }
    }
}
