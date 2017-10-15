package com.forsrc.gwt.client.application.websocket.vo;

public class ChatMessage {

    private long id;
    private String name;
    private String image;
    private String message;
    private long time;
    private boolean myself;

    public ChatMessage() {
    }

    public ChatMessage(long id, String name, boolean myself, String message, long time, String image) {
        super();
        this.id = id;
        this.name = name;
        this.myself = myself;
        this.message = message;
        this.time = time;
        this.image = image;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isMyself() {
        return myself;
    }

    public void setMyself(boolean myself) {
        this.myself = myself;
    }


}
