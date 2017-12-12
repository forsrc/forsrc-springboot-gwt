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

public class SrcView extends ViewWithUiHandlers<SrcUiHandlers>  implements SrcPresenter.MyView {
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
        MaterialTreeItem item = new MaterialTreeItem("test", IconType.FOLDER);
        item.add(new MaterialTreeItem("A"));
        srcTree.add(item);
        String filename = Window.Location.getHash().replaceAll(".*filename=", "");
        //Window.alert(filename);
        getUiHandlers().list(filename);
    }
}
