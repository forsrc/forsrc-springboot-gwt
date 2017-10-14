package com.forsrc.gwt.client.gatekeeper;

import java.util.Arrays;

import javax.inject.Inject;

import com.forsrc.gwt.client.commons.model.AccessToken;
import com.gwtplatform.mvp.client.proxy.GatekeeperWithParams;

public class HasRolesGatekeeper extends LoginedGatekeeper implements GatekeeperWithParams{

    String[] requiredRoles;
    //User user;

    @Inject
    HasRolesGatekeeper(
                AccessToken accessToken //,
                // User user
            ) {
        super(accessToken);
    }

    @Override
    public GatekeeperWithParams withParams(String[] params) {
        requiredRoles = params;
        return this;
    }

    @Override
    public boolean canReveal() {
        if (!super.canReveal()) {
            return false;
        }
        //return user.getRoles().containsAll(Arrays.asList(requiredRoles);
        return true;
    }
}
