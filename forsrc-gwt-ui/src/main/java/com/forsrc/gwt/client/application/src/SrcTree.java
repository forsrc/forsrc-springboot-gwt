package com.forsrc.gwt.client.application.src;

import gwt.material.design.addins.client.tree.MaterialTreeItem;

public class SrcTree {

    private MaterialTreeItem self;
    private MaterialTreeItem[] items;

    private SrcTree parent;
    private SrcTree[] children;

    public SrcTree getParent() {
        return parent;
    }

    public void setParent(SrcTree parent) {
        this.parent = parent;
    }

    public MaterialTreeItem getSelf() {
        return self;
    }

    public void setSelf(MaterialTreeItem self) {
        this.self = self;
    }

    public MaterialTreeItem[] getItems() {
        return items;
    }

    public void setItems(MaterialTreeItem[] items) {
        this.items = items;
    }

    public SrcTree[] getChildren() {
        return children;
    }

    public void setChildren(SrcTree[] children) {
        this.children = children;
    }

}
