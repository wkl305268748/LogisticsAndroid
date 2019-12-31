package com.kenny.logistics.service.retrofit;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kenny.logistics.BuildConfig;

import java.lang.reflect.Type;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kenny on 2017/12/26.
 */

public class RetrofitHelper {
    private static final String URL_RELEASE = "http://www.ahly56.com:84";
    private static final String URL_DEBUG = "http://192.168.1.29:84";
    //private static final String URL_DEBUG = "http://10.10.10.205:84";

    private Context mCntext;
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext){
        this.mCntext = mContext;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        //HTTPClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = builder.build();

        //Gson
        Gson gson = new GsonBuilder()
                //Gson转换适配器
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    //反序列化 转换Long 到 Date
                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
                    //序列化 转换Date 到 Long
                    @Override
                    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.getTime());
                    }
                })
                .setLenient()
                .create();
        GsonConverterFactory factory = GsonConverterFactory.create(gson);

        String base = URL_RELEASE;
        if(BuildConfig.DEBUG){
            base = URL_DEBUG;
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(base)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }



    public RetrofitUser getRetrofitUser(){
        return mRetrofit.create(RetrofitUser.class);
    }
    public RetrofitShOrder getRetrofitShOrder(){
        return mRetrofit.create(RetrofitShOrder.class);
    }
    public RetrofitDvOrder getRetrofitDvOrder(){
        return mRetrofit.create(RetrofitDvOrder.class);
    }

}