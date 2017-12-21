package com.kenny.logistics.api;

import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.PageResponse;
import com.kenny.logistics.model.order.OrderCustomer;
import com.kenny.logistics.model.order.OrderSet;
import com.kenny.logistics.model.system.SystemVersion;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Kenny on 2017/9/9.
 */

public interface SystemApi {

    //获取指定type的最新SystemVersion
    @GET("/v1/system/version/newest")
    Observable<JsonBean<SystemVersion>> selectTopByType(@Query("type") String type);

}
