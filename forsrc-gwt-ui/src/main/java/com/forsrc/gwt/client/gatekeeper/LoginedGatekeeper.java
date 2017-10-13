package com.forsrc.gwt.client.gatekeeper;

import javax.inject.Inject;

import com.forsrc.gwt.client.commons.model.AccessToken;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;


@DefaultGatekeeper
public class LoginedGatekeeper implements Gatekeeper {

    private AccessToken accessToken;
    @Inject
    LoginedGatekeeper(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean canReveal() {
        // TODO Auto-generated method stub
        return this.accessToken.getAccessToken() != null &&
                System.currentTimeMillis() - this.accessToken.getLoginTime().getTime() < this.accessToken.getExpiresIn()
                ;
    }

}
