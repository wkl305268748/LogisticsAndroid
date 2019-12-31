package com.kenny.logistics.service.presenter;

import android.content.Context;

import com.kenny.logistics.model.vo.user.UserVo;
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

public class DvUserInfoPresenter implements BasePresenter {

    private BaseView view;
    private Context mContext;

    private CompositeDisposable compositeDisposable;

    public DvUserInfoPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(BaseView view) {
        compositeDisposable = new CompositeDisposable();
        this.view = view;
    }

    @Override
    public void onDistory() {
        compositeDisposable.clear();
    }


    public void onGetInfo(String token) {
        RetrofitUser service = RetrofitHelper.getInstance(mContext).getRetrofitUser();
        service.getInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserVo>(view) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        if (jsonBean != null && jsonBean.isSuccess())
                            view.onSuccess(jsonBean.getData());
                        else
                            view.onError(jsonBean.getMsg());
                        view.onComplate();
                    }
                });
    }
}
