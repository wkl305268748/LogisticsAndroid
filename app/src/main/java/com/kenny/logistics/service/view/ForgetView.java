package com.kenny.logistics.service.view;

/**
 * Created by Kenny on 2017/12/28.
 */

public interface ForgetView extends BaseView {

    void onRePassword();

    void onGetCode();

    void onCount(Long second);

    void onCountDown();
}
