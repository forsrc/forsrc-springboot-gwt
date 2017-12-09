package com.forsrc.gwt.client.application.plantuml;

import com.google.gwt.dom.client.Document;
import com.google.gwt.http.client.URL;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.util.tools.shared.StringUtils;

import gwt.material.design.client.base.HasImage;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ImageMixin;
import gwt.material.design.client.constants.CssName;

public class PlantumlMaterialImage extends MaterialWidget implements HasText, HasImage {

    private ImageMixin<PlantumlMaterialImage> imageMixin;
    private String src;
    private String uml;
    
    public PlantumlMaterialImage() {
        super(Document.get().createImageElement(), CssName.RESPONSIVE_IMG);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        String uml = this.uml;
        if (uml == null) {
            return;
        }
        //String url = getSrc() + uml;
        String url = getSrc() + URL.encodeQueryString(uml);
        this.getElement().setAttribute("src", url);
        // setUrl(url);
        //getImageMixin().setUrl(url);
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
}
