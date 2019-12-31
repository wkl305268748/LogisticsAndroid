package com.kenny.logistics.service.presenter;

import android.content.Context;

import com.kenny.logistics.model.vo.PageVo;
import com.kenny.logistics.model.vo.order.OrderVo;
import com.kenny.logistics.service.presenter.base.BaseObserver;
import com.kenny.logistics.service.presenter.base.BasePresenter;
import com.kenny.logistics.service.retrofit.RetrofitHelper;
import com.kenny.logistics.service.retrofit.RetrofitShOrder;
import com.kenny.logistics.service.view.BasePageView;
import com.kenny.logistics.service.view.BaseView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kenny on 2017/12/26.
 */

public class SpOrderPresenter implements BasePresenter {

    private BasePageView view;
    private Context mContext;

    private CompositeDisposable compositeDisposable;

    public SpOrderPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(BaseView view) {
        this.view = (BasePageView) view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDistory() {
        compositeDisposable.clear();
    }

    public void onInit(String token, Integer pageSize){
        onInfo(token,1,pageSize,INIT);
    }

    public void onRefresh(String token, Integer pageSize){
        onInfo(token,1,pageSize,REFRESH);
    }

    public void onLoadMore(String token,Integer current, Integer pageSize){
        onInfo(token,current,pageSize,LOADMORE);
    }

    public void onInfo(String token,Integer current,Integer pageSize, final int event) {
        RetrofitShOrder service = RetrofitHelper.getInstance(mContext).getRetrofitShOrder();
        service.getPage(token,current,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<PageVo<OrderVo>>(view) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        if (jsonBean != null && jsonBean.isSuccess())
                        {
                            if(event == INIT)
                                view.onInit(jsonBean.getData());
                            if(event == REFRESH)
                                view.onRefresh(jsonBean.getData());
                            if(event == LOADMORE)
                                view.onLoadMore(jsonBean.getData());
                        }
                        else {
                            view.onError(jsonBean.getMsg());
                        }
                        view.onComplate();
                    }
                });
    }
}
