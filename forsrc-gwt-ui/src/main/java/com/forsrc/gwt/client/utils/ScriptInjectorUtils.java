package com.forsrc.gwt.client.utils;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.resources.client.TextResource;

public class ScriptInjectorUtils {
    public static void inject(TextResource resource) {
        inject(resource, true, false);
    }

    public static void injectDebug(TextResource resource) {
        inject(resource, false, true);
    }

    public static void inject(TextResource resource, boolean removeTag, boolean sourceUrl) {
        String text = resource.getText() + (sourceUrl ? "//# sourceURL=" + resource.getName() + ".js" : "");

        // Inject the script resource
        ScriptInjector.fromString(text).setWindow(ScriptInjector.TOP_WINDOW).setRemoveTag(removeTag).inject();
    }
}
