package com.forsrc.gwt.client.resources;


import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;

public interface AppResources extends ClientBundle {
    interface Normalize extends CssResource {
    }

    interface Style extends CssResource {
    }

    @Source("css/normalize.gss")
    Normalize normalize();

    @NotStrict
    @Source("css/style.gss")
    Style style();
}
