package com.kenny.logistics.service.presenter;

import android.content.Context;


import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.vo.user.UserLoginVo;
import com.kenny.logistics.service.presenter.base.BaseObserver;
import com.kenny.logistics.service.presenter.base.BasePresenter;
import com.kenny.logistics.service.retrofit.RetrofitHelper;
import com.kenny.logistics.service.retrofit.RetrofitUser;
import com.kenny.logistics.service.view.BaseView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kenny on 2017/12/26.
 */

public class LoginPresenter implements BasePresenter {

    private BaseView view;
    private Context mContext;

    private CompositeDisposable compositeDisposable;

    public LoginPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(BaseView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDistory() {
        compositeDisposable.clear();
    }

    public void onLogin(String phone, String password,String type) {
        RetrofitUser service = RetrofitHelper.getInstance(mContext).getRetrofitUser();
        service.login(phone, password,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserLoginVo>() {
                    private JsonBean<UserLoginVo> token;

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(JsonBean<UserLoginVo> institutionJsonBean) {
                        token = institutionJsonBean;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.onError("网络错误！");
                        view.onComplate();
                    }

                    @Override
                    public void onComplete() {
                        if (token != null && token.isSuccess())
                            view.onSuccess(token.getData());
                        else
                            view.onError(token.getMsg());
                        view.onComplate();
                    }
                });
    }
}
