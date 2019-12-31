package com.kenny.logistics.service.presenter.base;


import com.kenny.logistics.service.view.BaseView;

/**
 * Created by Kenny on 2017/12/26.
 */

public interface BasePresenter {

    public int INIT = 1;
    public int REFRESH = 2;
    public int LOADMORE = 3;

    void onCreate(BaseView view);

    void onDistory();
}
