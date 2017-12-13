package com.forsrc.gwt.client.application.src;

import javax.inject.Inject;

import com.forsrc.gwt.client.application.login.LoginUiHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.tree.MaterialTree;
import gwt.material.design.addins.client.tree.MaterialTreeItem;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLabel;

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

    @Override
    protected void onAttach() {
        super.onAttach();
        String filename = Window.Location.getHash().replaceAll(".*filename=", "");
        // Window.alert(filename);
        getUiHandlers().list(filename);
    }

    @Override
    public void setSrcTree(SrcFileVo srcFileVo) {
        MaterialTreeItem item = new MaterialTreeItem(srcFileVo.getName(),
                srcFileVo.isFile() ? IconType.INSERT_DRIVE_FILE : IconType.FOLDER);
        this.srcTree.add(item);
        SrcFileVo[] list = srcFileVo.getList();
        if (list == null) {
            return;
        }
        SrcFileVo vo = null;
        for (int i = 0; i < list.length; i++) {
            vo = list[i];
            MaterialTreeItem child = new MaterialTreeItem(vo.getName(),
                    vo.isFile() ? IconType.INSERT_DRIVE_FILE : IconType.FOLDER);
            item.add(child);
        }
    }
}
