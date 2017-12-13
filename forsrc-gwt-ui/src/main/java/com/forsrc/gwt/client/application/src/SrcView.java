package com.forsrc.gwt.client.application.src;



import javax.inject.Inject;

import org.geomajas.codemirror.client.Config;

import com.forsrc.gwt.client.application.codemirror.CodemirrorResources;
import com.forsrc.gwt.client.application.codemirror.MyCodeMirrorPanel;
import com.forsrc.gwt.client.utils.ScriptInjectorUtils;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.tree.MaterialTree;
import gwt.material.design.addins.client.tree.MaterialTreeItem;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialToast;

public class SrcView extends ViewWithUiHandlers<SrcUiHandlers> implements SrcPresenter.MyView {
    interface Binder extends UiBinder<Widget, SrcView> {
    }

    @Inject
    SrcView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void onSearch(String text) {

    }

    static {
        ScriptInjectorUtils.inject(CodemirrorResources.INSTANCE.codemirror());
        ScriptInjectorUtils.inject(CodemirrorResources.INSTANCE.closetag());
        ScriptInjectorUtils.inject(CodemirrorResources.INSTANCE.clike());
        ScriptInjectorUtils.inject(CodemirrorResources.INSTANCE.collapserange());
        ScriptInjectorUtils.inject(CodemirrorResources.INSTANCE.showHint());
        //CodemirrorResources.INSTANCE.showHintCss().ensureInjected();
        StyleInjector.injectStylesheet(CodemirrorResources.INSTANCE.showHintCss().getText());
    }

    @UiField
    MaterialTree srcTree;
    @UiField
    MaterialContainer materialContainer;

    @Override
    protected void onAttach() {
        super.onAttach();
        String root = Window.Location.getHash().replaceAll(".*filename=", "");
        srcTree.addCloseHandler(new CloseHandler<MaterialTreeItem>() {
            @Override
            public void onClose(CloseEvent<MaterialTreeItem> event) {
                // event.getTraget() returns MaterialTreeItem
                // MaterialToast.fireToast("Closed : " + event.getTarget().getText());
            }
        });

        srcTree.addOpenHandler(new OpenHandler<MaterialTreeItem>() {
            @Override
            public void onOpen(OpenEvent<MaterialTreeItem> event) {
                // event.getTraget() returns MaterialTreeItem
                // MaterialToast.fireToast("Opened : " + event.getTarget().getText());

            }
        });
        srcTree.addSelectionHandler(new SelectionHandler<MaterialTreeItem>() {
            @Override
            public void onSelection(SelectionEvent<MaterialTreeItem> event) {
                // MaterialToast.fireToast("Selected : " + event.getSelectedItem().getText());
                MaterialTreeItem item = event.getSelectedItem();
                String isLoad = item.getElement().getAttribute("isLoad");
                if ("true".equals(isLoad)) {
                    return;
                }
                String parentPath = item.getElement().getAttribute("parentPath");
                String path = parentPath.equals("") ? item.getText() : parentPath + "/" + item.getText();
                getUiHandlers().list(path);
                item.getElement().setAttribute("isLoad", "true");
            }
        });

        // Window.alert(filename);
        getUiHandlers().list(root);
    }

    @Override
    public void setSrcTree(SrcFileVo srcFileVo) {
        if (srcFileVo.isFile()) {
            Config config = Config.getDefault();
            config.setOption(Config.MODE, "text/x-java");
            config.setOption("autoCloseTags", true);
            config.setOption("collapseRange", true);
            MyCodeMirrorPanel codeMirrorPanel = new MyCodeMirrorPanel(config);
            codeMirrorPanel.getTextArea().setValue(srcFileVo.getText());
            materialContainer.add(codeMirrorPanel);
        }
        MaterialTreeItem select = srcTree.getSelectedItem();
        if (select == null) {
            MaterialTreeItem root = new MaterialTreeItem(srcFileVo.getName(),
                    srcFileVo.isFile() ? IconType.INSERT_DRIVE_FILE : IconType.FOLDER);
            root.getElement().setAttribute("parentPath", "");
            srcTree.add(root);
            select = root;
        }
        SrcFileVo[] srcFileVos = srcFileVo.getList();
        if (srcFileVos == null) {
            return;
        }
        MaterialTreeItem item = null;
        SrcFileVo vo = null;
        for (int i = 0; i < srcFileVos.length; i++) {
            vo = srcFileVos[i];
            item = new MaterialTreeItem(vo.getName(), vo.isFile() ? IconType.INSERT_DRIVE_FILE : IconType.FOLDER);
            item.getElement().setAttribute("parentPath", srcFileVo.getName());
            select.add(item);
        }

    }
}
