package com.kenny.logistics.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.PageResponse;
import com.kenny.logistics.model.user.Sms;
import com.kenny.logistics.model.user.UserSet;
import com.kenny.logistics.model.user.UserToken;


/**
 * Created by Kenny on 2017/9/9.
 */

public interface UserApi {
    //手机号密码登录
    @POST("/v1/user/customer/login")
    Observable<JsonBean<UserToken>> Login(@Query("phone") String phone, @Query("password") String password);

    //手机号密码注册
    @POST("/v1/user/customer/register_sms")
    Observable<JsonBean<UserToken>> Register(@Query("cookie") String cookie,
                                             @Query("code") String code,
                                             @Query("phone") String phone,
                                             @Query("password") String password);

    //发送短信验证码
    @POST("/v1/user/customer/send_register_sms")
    Observable<JsonBean<Sms>> SendSms(@Query("phone") String phone);


    //获取用户信息
    @GET("/v1/user/customer/ex/info")
    Observable<JsonBean<UserSet>> InfoEx(@Query("token") String token);


    //获取物流公司类型用户详细信息列表
    @GET("/v1/user/manager/ex/company")
    Observable<JsonBean<PageResponse<UserSet>>> CompanyInfoListEx(@Query("offset") Integer offset, @Query("pageSize") Integer pageSize);


}
