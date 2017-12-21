package com.kenny.logistics.api;

import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.PageResponse;
import com.kenny.logistics.model.order.OrderCustomer;
import com.kenny.logistics.model.order.OrderSet;

import java.util.List;
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
    @GET("/v1/order/page/customer")
    Observable<JsonBean<PageResponse<OrderSet>>> GetOrderPageByCustomerToken(@Query("offset") Integer offset,
                                                                             @Query("pageSize") Integer pageSize,
                                                                             @Query("token") String token);


    //根据客户Token统计所有数据
    @GET("/v1/order/count/customer/all")
    Observable<JsonBean<Map>> CustomerAll(@Query("token") String token);

    //增加订单
    @POST("/v1/order/customer")
    Observable<JsonBean<OrderCustomer>> insert(@Query("token") String token,
                                               @Query("send_name") String send_name,
                                               @Query("send_phone") String send_phone,
                                               @Query("send_addr") String send_addr,
                                               @Query("send_addr_info") String send_addr_info,
                                               @Query("recive_name") String recive_name,
                                               @Query("recive_phone") String recive_phone,
                                               @Query("recive_addr") String recive_addr,
                                               @Query("recive_addr_info") String recive_addr_info,
                                               @Query("send_time") String send_time,
                                               @Query("recive_time") String recive_time,
                                               @Query("dispatching_type") String dispatching_type,
                                               @Query("is_company") Boolean is_company,
                                               @Query("fk_want_company_id") Integer fk_want_company_id,
                                               @Query("goods[]") String[] goods);

    //获取根据ID
    @GET("/v1/order/{id}")
    Observable<JsonBean<OrderSet>> GetOrderById(@Path("id") int id);

}
