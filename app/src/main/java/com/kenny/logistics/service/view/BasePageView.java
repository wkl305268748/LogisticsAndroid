package com.kenny.logistics.service.view;


import com.kenny.logistics.model.vo.PageVo;

/**
 * Created by Kenny on 2017/12/26.
 */

public interface BasePageView<T> extends BaseView<Object>{

    void onRefresh(PageVo<T> data);

    void onLoadMore(PageVo<T> data);

    void onInit(PageVo<T> data);
}
