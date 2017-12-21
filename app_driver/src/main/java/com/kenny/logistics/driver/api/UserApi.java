package com.kenny.logistics.driver.api;

import com.kenny.logistics.driver.model.JsonBean;
import com.kenny.logistics.driver.model.user.UserSet;
import com.kenny.logistics.driver.model.user.UserToken;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Kenny on 2017/9/9.
 */

public interface UserApi {
    //手机号密码登录
    @POST("/v1/user/driver/login")
    Observable<JsonBean<UserToken>> Login(@Query("phone") String phone, @Query("password") String password);

    //获取用户信息
    @GET("/v1/user/driver/ex/info")
    Observable<JsonBean<UserSet>> InfoEx(@Query("token") String token);

}
