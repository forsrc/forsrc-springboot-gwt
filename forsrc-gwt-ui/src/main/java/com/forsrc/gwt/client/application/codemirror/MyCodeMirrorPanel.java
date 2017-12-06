package com.forsrc.gwt.client.application.codemirror;

import org.geomajas.annotation.Api;
import org.geomajas.codemirror.client.CodeMirrorWrapper;
import org.geomajas.codemirror.client.Config;
import org.geomajas.codemirror.client.widget.CodeMirrorPanel;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;

@Api(allMethods = true)
public class MyCodeMirrorPanel extends CodeMirrorPanel {

    private CodeMirrorWrapper editor;
    private TextArea textArea;
    private Config config;

    /**
     * Create an new codemirrorpanel with the default configuration.
     */
    public MyCodeMirrorPanel() {
        this(Config.forXml());
    }

    /**
     * Create an new codemirrorpanel with the default configuration.
     * 
     * @param initialData
     *            the initial content.
     */
    public MyCodeMirrorPanel(String initialData) {
        this(Config.forXml());
        setInitialData(initialData);
    }

    /**
     * Create an new codemirrorpanel with a custom configuration.
     * 
     * @param config
     *            the configuration.
     */
    public MyCodeMirrorPanel(Config config) {
        super(config);
        this.config = config;
        setWidth("100%");
        setHeight("100%");
        textArea = new TextArea();
        setWidget(textArea);
    }

    /**
     * Get the internal CodemirrorWrapper object.
     * 
     * @return editor
     */
    public CodeMirrorWrapper getEditor() {
        return editor;
    }

    /**
     * Set the initial data to be displayed in the codemirror editor window.
     * 
     * @param initialData
     */
    public void setInitialData(String initialData) {
        textArea.setValue(initialData);
    }

    // ------------------------------------------------------------------

    @Override
    protected void onLoad() {
        // super.onLoad();
        attachtCodeMirror();
    }

    private void attachtCodeMirror() {
        Scheduler.get().scheduleDeferred(new Command() {
            public void execute() {
                if (textArea == null) {
                    textArea = new TextArea();
                    setWidget(textArea);
                }
                if (editor == null) {
                    // Window.alert(textArea == null? "null" : "OK");
                    editor = CodeMirrorWrapper.fromTextArea(textArea, config == null ? Config.getDefault() : config);
                }
            }
        });
    }
}
