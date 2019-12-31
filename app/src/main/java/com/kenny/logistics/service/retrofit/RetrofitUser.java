package com.kenny.logistics.service.retrofit;

import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.vo.user.UserLoginVo;
import com.kenny.logistics.model.vo.user.UserVo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Kenny on 2017/9/9.
 */

public interface RetrofitUser {
    //账户密码登录
    @POST("/v2/common/user/login/username")
    Observable<JsonBean<UserLoginVo>> login(@Query("username") String username, @Query("password") String password, @Query("type") String type);


    //发送短信验证码
    @POST("/v2/common/user/send/sms")
    Observable<JsonBean> sendSms(@Query("phone") String phone, @Query("type") String type);


    //重置密码
    @POST("/v2/common/user/password/phone")
    Observable<JsonBean> passwordPhone(@Query("phone") String phone, @Query("type") String type, @Query("code") String code, @Query("password") String password);


    //获取用户信息
    @GET("/v2/common/user")
    Observable<JsonBean<UserVo>> getInfo(@Query("token") String token);

}
