package com.kenny.logistics.service.presenter.base;

import android.util.Log;

import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.service.view.BaseView;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Kenny on 2018/4/21.
 */

public class BaseObserver<T> implements Observer<JsonBean<T>> {

    public static final int TOKEN_ERROR = 102;
    protected JsonBean<T> jsonBean;
    protected BaseView baseView;

    public BaseObserver(BaseView view){
        this.baseView = view;
    }

    public BaseObserver(){}

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JsonBean<T> tJsonBean) {
        this.jsonBean = tJsonBean;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        baseView.onError("网络错误！");
    }

    @Override
    public void onComplete() {
        switch (jsonBean.getError_code()){
            case TOKEN_ERROR:
                break;
        }
    }
}
