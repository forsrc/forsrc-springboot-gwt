package com.forsrc.gwt.client.application.codemirror;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.TextResource;

public interface CodemirrorResources extends ClientBundle {

    public static final CodemirrorResources INSTANCE = GWT.create(CodemirrorResources.class);

    // @Source("my.css")
    // public CssResource css();

    // @Source("config.xml")
    // public TextResource initialConfiguration();

    // @Source("manual.pdf")
    // public DataResource ownersManual();

    // @Source("logo.png")
    // ImageResource logo();
    /*
    <!-- scripts for codemirror START -->
    <script type="text/javascript" language="javascript" src="ForsrcGwtUi/lib/codemirror.js"></script>
    <script type="text/javascript" language="javascript" src="ForsrcGwtUi/mode/xml/xml.js"></script>
    <script type="text/javascript" language="javascript" src="ForsrcGwtUi/mode/css/css.js"></script>
    <script type="text/javascript" language="javascript" src="ForsrcGwtUi/mode/htmlmixed/htmlmixed.js"></script>
    <script type="text/javascript" language="javascript" src="ForsrcGwtUi/addon/edit/closetag.js"></script>
    <script type="text/javascript" language="javascript" src="ForsrcGwtUi/addon/fold/collapserange.js"></script>
    <!-- scripts for codemirror END -->
    */

    @Source("js/codemirror.js")
    TextResource codemirror();

    @Source("js/clike.js")
    TextResource clike();

    @Source("js/closetag.js")
    TextResource closetag();

    @Source("js/collapserange.js")
    TextResource collapserange();

    @Source("js/show-hint.js")
    TextResource showHint();

    @Source("js/show-hint.css")
    @CssResource.NotStrict
    CssResource showHintCss();
}
