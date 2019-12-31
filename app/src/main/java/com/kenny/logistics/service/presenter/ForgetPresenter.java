package com.kenny.logistics.service.presenter;

import android.content.Context;

import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.service.presenter.base.BaseObserver;
import com.kenny.logistics.service.presenter.base.BasePresenter;
import com.kenny.logistics.service.retrofit.RetrofitHelper;
import com.kenny.logistics.service.retrofit.RetrofitUser;
import com.kenny.logistics.service.view.BaseView;
import com.kenny.logistics.service.view.ForgetView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kenny on 2017/12/26.
 */

public class ForgetPresenter implements BasePresenter {

    private ForgetView view;
    private Context mContext;

    private CompositeDisposable compositeDisposable;

    public ForgetPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(BaseView view) {
        this.view = (ForgetView) view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDistory() {
        compositeDisposable.clear();
    }


    public void onGetCode(String phone,String type){
        RetrofitUser service = RetrofitHelper.getInstance(mContext).getRetrofitUser();
        service.sendSms(phone,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver(view) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        if (jsonBean != null && jsonBean.isSuccess())
                            view.onGetCode();
                        else
                            view.onError(jsonBean.getMsg());

                        view.onComplate();
                    }
                });
    }

    public void onRePassword(String phone,String type, String password, String code) {
        RetrofitUser service = RetrofitHelper.getInstance(mContext).getRetrofitUser();
        service.passwordPhone(phone, type,code,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver(view) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        if (jsonBean != null && jsonBean.isSuccess())
                            view.onRePassword();
                        else
                            view.onError(jsonBean.getMsg());

                        view.onComplate();
                    }
                });
    }

    public void onCountDown(final Long total){
        Observable.interval(0,1, TimeUnit.SECONDS)
                .take(total)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        view.onCount(total - aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.onCountDown();
                    }
        });
    }
}
