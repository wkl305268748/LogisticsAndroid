package com.kenny.logistics.service.retrofit;

import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.po.Order;
import com.kenny.logistics.model.vo.PageVo;
import com.kenny.logistics.model.vo.order.OrderVo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Kenny on 2017/9/9.
 */

public interface RetrofitDvOrder {

    //列出列表
    @GET("/v2/driver/order/page")
    Observable<JsonBean<PageVo<OrderVo>>> getPage(@Query("token") String token, @Query("current") Integer current, @Query("pageSize") Integer pageSize);
}
