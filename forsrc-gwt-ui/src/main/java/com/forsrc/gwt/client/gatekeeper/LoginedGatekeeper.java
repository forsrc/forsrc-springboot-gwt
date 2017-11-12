package com.forsrc.gwt.client.gatekeeper;


import javax.inject.Inject;

import com.forsrc.gwt.client.commons.model.AccessToken;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

import gwt.material.design.client.ui.MaterialLoader;


@DefaultGatekeeper
public class LoginedGatekeeper implements Gatekeeper {
 
    AccessToken accessToken;
 
    @Inject
    LoginedGatekeeper(AccessToken accessToken) {
        MaterialLoader.progress(true);
        this.accessToken = accessToken;
    }

    @Override
    public boolean canReveal() {

        //Window.alert(this.accessToken.toString());

        if (this.accessToken == null || this.accessToken.getAccessToken() == null || this.accessToken.getAccessToken() == "undefined") {
            Storage storage = Storage.getLocalStorageIfSupported();
            if (storage != null) {
                //Window.alert(storage.getItem("/oauth/token/login_time"));
                String loginTimeStr = storage.getItem("/oauth/token/login_time");
                if (loginTimeStr == null || loginTimeStr == "undefined") {
                    return false;
                }
                long loginTime = Long.parseLong(loginTimeStr);
                if (loginTime < 100000) {
                    return  false;
                }
                String expiresInStr = storage.getItem("/oauth/token/expires_in");
                long expiresIn = Long.parseLong(expiresInStr);
                if (expiresIn < 60) {
                    return  false;
                }
                return System.currentTimeMillis() - loginTime < expiresIn;
            }
        }
        return this.accessToken.isNotExpired();
    }

}
