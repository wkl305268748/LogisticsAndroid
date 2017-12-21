package test1.retrofit;

import android.database.Observable;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kenny on 2017/9/12.
 */

public interface HttpApi {

    @GET("/test1/login")
    Call<ResponseBody> login(@Query("phone") String phone, @Query("password") String password);


    //通过Gson自动解析
    @GET("/test1/login")
    Call<List<String>> login1(@Query("phone") String phone, @Query("password") String password);

    //与RxJava配合使用
    @GET("/test1/login")
    Observable<List<String>> login2(@Query("phone") String phone, @Query("password") String password);
}
