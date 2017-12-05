package com.forsrc.gwt.client.application.codemirror;

import javax.inject.Inject;

import org.geomajas.codemirror.client.CodeMirrorWrapper;
import org.geomajas.codemirror.client.Config;
import org.geomajas.codemirror.client.widget.CodeMirrorPanel;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
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

    CodeMirrorPanel codeMirrorPanel;

    @UiField
    HTMLPanel codeMirrorHtmlPanel;

    @Inject
    CodemirrorView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

    }

    @Override
    public void onSearch(String text) {

        searchLabel.setText("Home search -> " + text);
        codeMirrorPanel.getEditor().setContent(text);
    }

    @Override
    protected void onAttach() {
        // TODO Auto-generated method stub
        super.onAttach();
        Scheduler.get().scheduleDeferred(new Command() {
            public void execute() {
                if (codeMirrorPanel == null) {
                    Config config = Config.getDefault();
                    config.setOption(Config.MODE, "xml");
                    config.setOption("autoCloseTags", true);
                    config.setOption("collapseRange", true);
                    codeMirrorPanel = new MyCodeMirrorPanel(config);
                    codeMirrorHtmlPanel.add(codeMirrorPanel);
                }
            }
        });
    }

}
