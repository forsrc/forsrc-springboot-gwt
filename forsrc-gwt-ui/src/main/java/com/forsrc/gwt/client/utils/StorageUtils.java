package com.forsrc.gwt.client.utils;

import com.forsrc.gwt.client.commons.model.AccessToken;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;

public class StorageUtils {

    public static Storage storage;
    public static JSONObject storageJson;

    static {
        storage = Storage.getLocalStorageIfSupported();
        if (storage == null) {
            storageJson = new JSONObject();
        }
    }

    public static final String OAUTH_TOKEN = "/oauth/token/";
    public static final String ACCESS_TOKEN = OAUTH_TOKEN + "access_token";

    public static String getAccessToken() {
        String token = null;
        if (storage != null) {
            token = storage.getItem(ACCESS_TOKEN);
        } else {
            token = storageJson.get(ACCESS_TOKEN).isString().stringValue();
        }
        return "&access_token=" + token;
    }

    public static AccessToken setAccessToken(String email, JSONObject accessTokenJson) {
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(accessTokenJson.get("access_token").isString().stringValue());
        accessToken.setRefreshToken(accessTokenJson.get("refresh_token").isString().stringValue());
        accessToken.setTokenType(accessTokenJson.get("token_type").isString().stringValue());
        accessToken.setScope(accessTokenJson.get("scope").isString().stringValue());
        accessToken.setJti(accessTokenJson.get("jti").isString().stringValue());
        accessToken.setExpiresIn((long) accessTokenJson.get("expires_in").isNumber().doubleValue() * 1000);
        accessToken.setEmail(email);

        if (storage != null) {
            storage.setItem("/user/email", email);
            storage.setItem("/oauth/token", accessToken.toString());
            storage.setItem(ACCESS_TOKEN, accessToken.getAccessToken());
            storage.setItem("/oauth/token/login_time", String.valueOf(System.currentTimeMillis()));
            storage.setItem("/oauth/token/expires_in", String.valueOf(accessToken.getExpiresIn()));
        } else {
            storageJson.put("/user/email", new JSONString(email));
            storageJson.put("/oauth/token", new JSONString(accessToken.toString()));
            storageJson.put(ACCESS_TOKEN, new JSONString(accessToken.getAccessToken()));
            storageJson.put("/oauth/token/login_time", new JSONString(String.valueOf(System.currentTimeMillis())));
            storageJson.put("/oauth/token/expires_in", new JSONString(String.valueOf(accessToken.getExpiresIn())));
        }
        return accessToken;
    }
}
