package com.forsrc.gwt.client.application.src;

import java.util.Arrays;

public class SrcFileVo {

    private String name;
    private boolean exists;
    private boolean isFile;
    private String length;
    private String lastModified;
    private String text;

    private SrcFileVo[] list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean isFile) {
        this.isFile = isFile;
    }

    public SrcFileVo[] getList() {
        return list;
    }

    public void setList(SrcFileVo[] list) {
        this.list = list;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SrcFileVo [name=" + name + ", exists=" + exists + ", isFile=" + isFile + ", length=" + length
                + ", lastModified=" + lastModified + ", list=" + (list == null ? null : Arrays.toString(list)) + "]";
    }

}
