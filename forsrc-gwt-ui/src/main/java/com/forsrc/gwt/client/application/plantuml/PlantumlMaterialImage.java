package com.forsrc.gwt.client.application.plantuml;

import com.google.gwt.safehtml.shared.UriUtils;

import gwt.material.design.client.ui.MaterialImage;

public class PlantumlMaterialImage extends MaterialImage {

    private String src;
    private String uml;

    @Override
    protected void onLoad() {
        super.onLoad();
        String uml = this.uml;
        if (uml == null) {
            return;
        }
        // String url = getSrc() + uml;
        String url = getSrc() + UriUtils.encode(param(uml));
        this.getElement().setAttribute("src", url);
        // setUrl(url);
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setUml(String uml) {
        this.uml = uml;
    }

    private String param(String uml) {
        return uml.replaceAll("(%0A)|(\\\\n)", "\n");
    }
}
