package com.forsrc.gwt.client.application.codemirror;

import javax.inject.Inject;

import org.geomajas.codemirror.client.widget.CodeMirrorPanel;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialLabel;

public class CodemirrorView extends ViewImpl implements CodemirrorPresenter.MyView {
    interface Binder extends UiBinder<Widget, CodemirrorView> {
    }

    @UiField
    MaterialLabel searchLabel;

    @Inject
    CodemirrorView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
     // -- show a codemirror panel
        String initialContent = 
            "<html>\n\t<head>\n\t\t<title>Geomajas GWT Codemirror wrapper sample</title>\n\t</head>\n" +
            "\n\t<body>\n\t\tRead more here: \n\t\t<a href=\"\">" +
            "Geomajas Codemirror GWT</a><br />" +
                    "\n\t\tand here: <a href=\"http://codemirror.net/\">CodeMirror</a><br />\n\t" +
            "</body>\n</html>";

        html.add(new CodeMirrorPanel(initialContent));
    }

    @Override
    public void onSearch(String text) {

        searchLabel.setText("Home search -> " + text);
    }

    @UiField
    HTMLPanel html;

    @Override
    protected void onAttach() {
        // TODO Auto-generated method stub
        super.onAttach();
        


            
            
    }
}
