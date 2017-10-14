package com.forsrc.gwt.client.commons.model;

import java.util.Date;

public class AccessToken {

    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private long expiresIn;
    private String scope;
    private String jti;
    private Date loginTime;
    private String email;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.loginTime = new Date();
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExpired() {
        return !isNotExpired();
    }

    public boolean isNotExpired() {
        return this.accessToken != null
               && System.currentTimeMillis() - this.loginTime.getTime() < this.expiresIn
               ;
    }

    @Override
    public String toString() {
        return "AccessToken [accessToken=" + accessToken + ", tokenType=" + tokenType + ", refreshToken=" + refreshToken
                + ", expiresIn=" + expiresIn + ", scope=" + scope + ", jti=" + jti + ", loginTime=" + loginTime
                + ", email=" + email + "]";
    }

}
