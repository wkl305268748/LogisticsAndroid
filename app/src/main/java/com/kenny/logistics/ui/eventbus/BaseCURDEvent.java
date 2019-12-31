package com.kenny.logistics.ui.eventbus;

/**
 * Created by Kenny on 2018/2/5.
 */

public class BaseCURDEvent<T> {

    public static final int EVENT_ADD = 1;
    public static final int EVENT_EDIT = 2;
    public static final int EVENT_DELETE = 3;
    public static final int EVENT_SELECT = 4;

    private int position;
    private int event;
    private T data;

    public BaseCURDEvent(T data) {
        setEvent(EVENT_ADD);
        this.data = data;
    }

    public BaseCURDEvent(int position, T data) {
        setEvent(EVENT_EDIT);
        this.data = data;
        this.position = position;
    }

    public BaseCURDEvent(int position) {
        setEvent(EVENT_DELETE);
        this.position = position;
    }

    public BaseCURDEvent(int position, T data, int event) {
        setEvent(event);
        this.data = data;
        this.position = position;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
