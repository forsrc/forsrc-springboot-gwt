package com.forsrc.gwt.client.application.plantuml;

import com.forsrc.gwt.client.commons.oauth.Oauth;
import com.google.gwt.dom.client.Document;
import com.google.gwt.http.client.URL;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.client.base.HasImage;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ImageMixin;
import gwt.material.design.client.constants.CssName;

public class PlantumlMaterialImage extends MaterialWidget implements HasText, HasImage, Oauth {

    private ImageMixin<PlantumlMaterialImage> imageMixin;
    private String src;
    private String uml;
    private boolean isUi = true;
    private String accessToken;

    public PlantumlMaterialImage() {
        super(Document.get().createImageElement(), CssName.RESPONSIVE_IMG);
    }

    public PlantumlMaterialImage(String src, String uml) {
        this();
        this.src = src;
        this.uml = uml;
        this.isUi = false;
        setUrl(this.src, this.uml);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        if (this.isUi) {
            setUrl(this.src, this.uml);
        }
    }

    private void setUrl(String src, String uml) {
        if (uml == null) {
            return;
        }
        // String url = getSrc() + uml;
        String url = src + "&uml=" + URL.encodeQueryString(uml) + getAccessToken();
        // this.getElement().setAttribute("src", url);
        // setUrl(url);
        getImageMixin().setUrl(url);
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    @Override
    public String getText() {
        return this.uml;
    }

    @Override
    public void setText(String text) {
        this.uml = new HTML(text).getText();
    }

    @Override
    public void setUrl(String url) {
        this.src = url;
    }

    @Override
    public String getUrl() {
        return this.src;
    }

    @Override
    public void setResource(ImageResource resource) {
        getImageMixin().setResource(resource);
    }

    @Override
    public ImageResource getResource() {
        return getImageMixin().getResource();
    }

    protected ImageMixin<PlantumlMaterialImage> getImageMixin() {
        if (imageMixin == null) {
            imageMixin = new ImageMixin<>(this);
        }
        return imageMixin;
    }

    @Override
    public String getAccessToken() {
        return this.accessToken != null ? this.accessToken : "";
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
