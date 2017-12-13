package com.forsrc.gwt.client.application.src;

import com.forsrc.gwt.client.application.ApplicationPresenter;
import com.forsrc.gwt.client.application.ApplicationView;
import com.forsrc.gwt.client.application.login.LoginUiHandlers;
import com.forsrc.gwt.client.application.src.SrcEvent.SrcEventHandler;
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
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialToast;

public class SrcPresenter extends Presenter<SrcPresenter.MyView, SrcPresenter.MyProxy>
        implements MyEventHandler, SrcEventHandler, SrcUiHandlers {

    interface MyView extends View, HasUiHandlers<SrcUiHandlers> {
        public void onSearch(String text);

        public void setSrcTree(SrcFileVo srcFileVo);
    }

    @ProxyStandard
    @NameToken(NameTokens.SRC)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<SrcPresenter> {
    }

    @Inject
    SrcPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        super.onBind();
        addRegisteredHandler(MyEvent.TYPE, this);
    }

    @Override
    public void onMyEventHandler(MyEvent event) {
        MyEventData data = event.getData();
        if (ApplicationView.TYPE_SEARECH.equals(data.getType())) {
            getView().onSearch((String) data.getData());
        }
    }

    @Override
    public void onSrcHandler(SrcEvent event) {
        MaterialToast.fireToast("onSrcHandler: " + event);
    }

    Messages messages = GWT.create(Messages.class);

    @Override
    public void list(String filename) {
        MaterialLoader.progress(true);
        String url = messages.app_url_resource() + "/src/list?filename=" + filename;
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
        String params = null;
        try {
            Request request = builder.sendRequest(params, new RequestCallback() {

                @Override
                public void onResponseReceived(Request request, Response response) {
                    if (200 == response.getStatusCode()) {
                        JSONObject data = new JSONObject(JsonUtils.safeEval(response.getText()));
                        GWT.log(data.toString());
                        try {
                            SrcFileVo srcFileVo = new SrcFileVo();
                            srcFileVo.setName(data.get("name").isString().stringValue());
                            srcFileVo.setExists(data.get("exists").isBoolean().booleanValue());
                            if (srcFileVo.isExists()) {
                                srcFileVo.setExists(data.get("isFile").isBoolean().booleanValue());
                                srcFileVo.setLength(data.get("length").isString().stringValue());
                                srcFileVo.setLastModified(data.get("lastModified").isString().stringValue());
                            }
                            JSONArray jsonArray = data.get("list").isArray();
                            if (jsonArray != null) {
                                int size = jsonArray.size();
                                SrcFileVo[] list = new SrcFileVo[size];
                                JSONObject item = null;
                                for (int i = 0; i < size; i++) {
                                    item = jsonArray.get(i).isObject();
                                    SrcFileVo vo = new SrcFileVo();
                                    vo.setName(item.get("name").isString().stringValue());
                                    vo.setExists(item.get("exists").isBoolean().booleanValue());
                                    vo.setFile(item.get("isFile").isBoolean().booleanValue());
                                    vo.setLength(item.get("length").isString().stringValue());
                                    vo.setLastModified(item.get("lastModified").isString().stringValue());
                                    list[i] = vo;
                                }
                                srcFileVo.setList(list);
                            }
                            GWT.log(srcFileVo.toString());
                            getView().setSrcTree(srcFileVo);
                        } catch (Exception e) {
                            MaterialToast.fireToast("Response:" + e.getMessage());
                        }
                    } else {
                        MaterialToast.fireToast("Response:" + response.getStatusCode());
                    }
                    MaterialLoader.progress(false);

                }

                @Override
                public void onError(Request request, Throwable exception) {
                    MaterialToast.fireToast("Error:" + exception.getMessage());
                    MaterialLoader.progress(false);
                }

            });
        } catch (RequestException e) {
            MaterialToast.fireToast("Error:" + e.getMessage());
        }

    }

}
