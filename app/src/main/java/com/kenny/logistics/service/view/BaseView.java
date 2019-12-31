package com.kenny.logistics.service.view;

/**
 * Created by Kenny on 2017/12/26.
 */

public interface BaseView<T> {

    void onSuccess(T info);

    void onError(String result);

    void onComplate();
}
