package com.kenny.logistics.service.presenter;

import android.content.Context;

import com.kenny.logistics.model.po.Order;
import com.kenny.logistics.model.vo.PageVo;
import com.kenny.logistics.model.vo.order.OrderVo;
import com.kenny.logistics.model.vo.user.UserVo;
import com.kenny.logistics.service.presenter.base.BaseObserver;
import com.kenny.logistics.service.presenter.base.BasePresenter;
import com.kenny.logistics.service.retrofit.RetrofitHelper;
import com.kenny.logistics.service.retrofit.RetrofitShOrder;
import com.kenny.logistics.service.retrofit.RetrofitUser;
import com.kenny.logistics.service.view.BasePageView;
import com.kenny.logistics.service.view.BaseView;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kenny on 2017/12/26.
 */

public class SpCreateOrderPresenter implements BasePresenter {

    private BaseView view;
    private Context mContext;

    private CompositeDisposable compositeDisposable;

    public SpCreateOrderPresenter(Context context) {
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

    public void onInsert(Order order){

        RetrofitShOrder service = RetrofitHelper.getInstance(mContext).getRetrofitShOrder();
        service.insert(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<OrderVo>(view) {

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
