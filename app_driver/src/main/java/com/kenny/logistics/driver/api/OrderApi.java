package com.kenny.logistics.driver.api;

import com.kenny.logistics.driver.model.JsonBean;
import com.kenny.logistics.driver.model.PageResponse;
import com.kenny.logistics.driver.model.order.OrderSet;
import com.kenny.logistics.driver.model.order.OrderSign;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Kenny on 2017/9/9.
 */

public interface OrderApi {

    //根据客户Token列出所有的Order
    @GET("/v1/order/page/driver")
    Observable<JsonBean<PageResponse<OrderSet>>> GetOrderPageByDriverToken(@Query("offset") Integer offset,
                                                                           @Query("pageSize") Integer pageSize,
                                                                           @Query("token") String token);


    //根据客户Token列出所有的Order
    @GET("/v1/order/page_taking/driver")
    Observable<JsonBean<PageResponse<OrderSet>>> GetOrderPageTakingByDriverToken(@Query("offset") Integer offset,
                                                                           @Query("pageSize") Integer pageSize,
                                                                           @Query("token") String token);

    //根据客户Token统计所有数据
    @GET("/v1/order/count/driver/all")
    Observable<JsonBean<Map>> DriverAll(@Query("token") String token);


    //获取根据ID
    @GET("/v1/order/{id}")
    Observable<JsonBean<OrderSet>> GetOrderById(@Path("id") int id);


    @POST("/v1/order/sign")
    Observable<JsonBean<OrderSign>> SignOrder(@Query("token") String token,
                                              @Query("fk_order_id") Integer fk_order_id,
                                              @Query("order_img") String order_img);


    @GET("/v1/util/upload/token")
    Observable<JsonBean<String>> GetQiniuToken();


}
