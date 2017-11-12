package com.forsrc.gwt.client.bootstrap;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import com.forsrc.gwt.client.commons.model.AccessToken;
import com.forsrc.gwt.client.place.NameTokens;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.storage.client.Storage;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import gwt.material.design.client.ui.MaterialLoader;


public class BootstrapperImpl implements Bootstrapper {

    private final Logger logger = Logger.getLogger(BootstrapperImpl.class.getSimpleName());
    private final AccessToken accessToken;
    private final PlaceManager placeManager;
    private final String unauthorizedPlace;

    public @Inject
    BootstrapperImpl(
            PlaceManager placeManager,
            AccessToken accessToken,
            @UnauthorizedPlace String unauthorizedPlace
            ) {
        MaterialLoader.progress(true);
        this.accessToken = accessToken;
        this.placeManager = placeManager;
        this.unauthorizedPlace = unauthorizedPlace;
    }

    @Override
    public void onBootstrap() {
        final Storage storage = Storage.getLocalStorageIfSupported();

        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            @Override
            public void onUncaughtException(@NotNull Throwable e) {
                logger.log(Level.WARNING, e.getMessage(), e);
                if (storage != null) {
                    storage.setItem("/error/message", e.getMessage());
                    DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy/MM/dd HH:mm:ss");
                    storage.setItem("/error/time", fmt.format(new Date()));
                }
                placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.ERROR).build());
            }
        });
        this.placeManager.revealCurrentPlace();
        //reveal();
    }

    private void reveal() {
        if (this.accessToken.isNotExpired()) {
            this.placeManager.revealCurrentPlace();
        } else {
            this.placeManager.revealPlace(new PlaceRequest.Builder().nameToken(unauthorizedPlace).build());
        }
    }
}
