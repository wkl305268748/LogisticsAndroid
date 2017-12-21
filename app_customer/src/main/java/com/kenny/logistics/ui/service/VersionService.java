package com.kenny.logistics.ui.service;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.allenliu.versionchecklib.core.AVersionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.system.SystemVersion;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Kenny on 2017/9/21.
 */

public class VersionService extends AVersionService {
    @Override
    public void onResponses(AVersionService service, String response) {
        //获取版本号
        int version = 0;
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            version = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //Gson解析数据
        Gson gson = new GsonBuilder()
                //Gson转换Long 到 Date 适配器
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .setLenient()
                .create();
        JsonBean<SystemVersion> versionJsonBean = gson.fromJson(response,new TypeToken<JsonBean<SystemVersion>>(){}.getType());
        //判断更新
        if(versionJsonBean.isSuccess()){
            if(versionJsonBean.getData().getVersion_number() > version){
                service.showVersionDialog(versionJsonBean.getData().getUrl(),"发现新版本！",versionJsonBean.getData().getChangelog());
            }
        }
    }
}
