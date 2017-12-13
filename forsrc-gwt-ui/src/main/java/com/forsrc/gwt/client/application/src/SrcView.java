package com.forsrc.gwt.client.application.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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

    @UiField
    MaterialTree srcTree;

    private SrcTree tree;
    private MaterialTreeItem root;

    @Override
    protected void onAttach() {
        super.onAttach();
        String root = Window.Location.getHash().replaceAll(".*filename=", "");
        srcTree.addCloseHandler(new CloseHandler<MaterialTreeItem>() {
            @Override
            public void onClose(CloseEvent<MaterialTreeItem> event) {
                // event.getTraget() returns MaterialTreeItem
                MaterialToast.fireToast("Closed : " + event.getTarget().getText());
            }
        });

        srcTree.addOpenHandler(new OpenHandler<MaterialTreeItem>() {
            @Override
            public void onOpen(OpenEvent<MaterialTreeItem> event) {
                // event.getTraget() returns MaterialTreeItem
                MaterialToast.fireToast("Opened : " + event.getTarget().getText());
            }
        });
        srcTree.addSelectionHandler(new SelectionHandler<MaterialTreeItem>() {
            @Override
            public void onSelection(SelectionEvent<MaterialTreeItem> event) {
                MaterialToast.fireToast("Selected : " + event.getSelectedItem().getText());
                MaterialTreeItem item = event.getSelectedItem();
                String parentPath = item.getElement().getAttribute("parentPath");
                String path = parentPath.equals("") ? item.getText() : parentPath + "/" + item.getText();
                getUiHandlers().list(path);
            }
        });

        // Window.alert(filename);
        getUiHandlers().list(root);
    }

    @Override
    public void setSrcTree(SrcFileVo srcFileVo) {

        SrcTree clickSrcTree = null;
        if (this.root == null) {
            MaterialTreeItem item = new MaterialTreeItem(srcFileVo.getName(),
                    srcFileVo.isFile() ? IconType.INSERT_DRIVE_FILE : IconType.FOLDER);
            item.getElement().setAttribute("parentPath", "");
            this.root = item;
            this.tree = new SrcTree();
            clickSrcTree = this.tree;
            clickSrcTree.setSelf(item);
        } else {
            clickSrcTree = find(this.tree, srcFileVo.getName());
            if (clickSrcTree == null) {
                return;
            }
            this.srcTree.remove(this.root);
        }

        SrcFileVo[] list = srcFileVo.getList();
        if (list == null) {
            return;
        }
        SrcTree[] children = new SrcTree[list.length];
        SrcFileVo vo = null;
        MaterialTreeItem[] items = new MaterialTreeItem[list.length];
        for (int i = 0; i < list.length; i++) {
            vo = list[i];
            MaterialTreeItem child = new MaterialTreeItem(vo.getName(),
                    vo.isFile() ? IconType.INSERT_DRIVE_FILE : IconType.FOLDER);
            child.getElement().setAttribute("parentPath", srcFileVo.getName());
            items[i] = child;
            SrcTree s = new SrcTree();
            s.setParent(clickSrcTree);
            s.setSelf(child);
            children[i] = s;
        }
        clickSrcTree.setChildren(children);

        renderTree(srcTree, tree);
    }

    private SrcTree find(final SrcTree srcTree, final String path) {

        MaterialTreeItem self = srcTree.getSelf();
        if (path.equals(self.getText())) {
            return srcTree;
        }

        SrcTree[] children = srcTree.getChildren();
        if (children == null) {
            return null;
        }
        SrcTree st = null;

        for (int j = 0; j < children.length; j++) {
            st = children[j];
            String parentPath = st.getSelf().getElement().getAttribute("parentPath");
            parentPath = parentPath.equals("") ? "" : parentPath + "/";
            if (path.equals(parentPath + st.getSelf().getText())) {
                return st;
            } else {
                st = find(st, path);
                if (st != null) {
                    return st;
                }
            }
        }
        return null;
    }

    private void renderTree(MaterialTree materialTree, SrcTree srcTree) {
        MaterialTreeItem self = srcTree.getSelf();
        SrcTree parent = srcTree.getParent();
        SrcTree[] children = srcTree.getChildren();
        if (parent == null) {
            materialTree.add(self);
        } else {
            parent.getSelf().add(self);
        }
        if (children == null) {
            return;
        }
        for (int j = 0; j < children.length; j++) {
            renderTree(materialTree, children[j]);
        }
    }
}
