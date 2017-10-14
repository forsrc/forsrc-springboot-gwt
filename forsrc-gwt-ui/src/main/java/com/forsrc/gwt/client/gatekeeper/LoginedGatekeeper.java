package com.forsrc.gwt.client.gatekeeper;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.forsrc.gwt.client.commons.model.AccessToken;
import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;


@DefaultGatekeeper
public class LoginedGatekeeper implements Gatekeeper {

    private final Logger logger = Logger.getLogger(LoginedGatekeeper.class.getSimpleName());
 
    private AccessToken accessToken;
    @Inject
    LoginedGatekeeper(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean canReveal() {
        //Window.alert("" + this.accessToken.isNotExpired());
        logger.log(Level.INFO, this.accessToken.toString());
        if (this.accessToken == null || this.accessToken.getAccessToken() == null || this.accessToken.getAccessToken() == "undefined") {
            Storage storage = Storage.getLocalStorageIfSupported();
            if (storage != null) {
                String loginTimeStr = storage.getItem("login_time");
                logger.log(Level.INFO, loginTimeStr);
                if (loginTimeStr == null || loginTimeStr == "undefined") {
                    return false;
                }
                long loginTime = Long.parseLong(loginTimeStr);
                String expiresInStr = storage.getItem("expires_in");
                long expiresIn = Long.parseLong(expiresInStr);
                return System.currentTimeMillis() - loginTime < expiresIn;
            }
        }
        return this.accessToken.isNotExpired();
    }

}
