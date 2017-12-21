package com.kenny.logistics.driver.util;

import android.content.Context;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;

/**
 * Created by Kenny on 2017/9/24.
 */

public class MapNaviUtils implements INaviInfoCallback {

    Context context;
    public MapNaviUtils(Context context){
        this.context = context;
    }

    public void Start(LatLonPoint lstart, LatLonPoint lend){
        Poi start = new Poi("起点", new LatLng(lstart.getLatitude(),lend.getLongitude()), "");
        Poi end = new Poi("终点", new LatLng(lend.getLatitude(),lend.getLongitude()), "");
        AmapNaviPage.getInstance().showRouteActivity(context, new AmapNaviParams(start, null, end, AmapNaviType.DRIVER),MapNaviUtils.this);

    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String text) {
        TtsUtils.getInstance().Speak(text);
    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }
}
