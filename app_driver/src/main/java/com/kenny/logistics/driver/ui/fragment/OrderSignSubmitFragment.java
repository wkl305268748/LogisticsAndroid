package com.kenny.logistics.driver.ui.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kenny.logistics.driver.R;
import com.kenny.logistics.driver.api.ApiRetrofit;
import com.kenny.logistics.driver.model.JsonBean;
import com.kenny.logistics.driver.model.order.OrderSign;
import com.kenny.logistics.driver.ui.preferences.Cache;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kenny on 2017/8/8.
 */

public class OrderSignSubmitFragment extends SupportFragment {

    int order_id;
    String order_number;
    String file_name;
    File file;
    boolean isSubmit = false;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_progressBar)
    TextView tvProgressBar;
    @BindView(R.id.progress)
    RelativeLayout progress;
    Unbinder unbinder;

    public static OrderSignSubmitFragment newInstance(Bundle args) {
        OrderSignSubmitFragment fragment = new OrderSignSubmitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_sign_submit, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            order_id = bundle.getInt("order_id");
            order_number = bundle.getString("order_number");
            file_name =  bundle.getString("file");
        }
        tvOrderNumber.setText("订单号："+order_number);
        //本地文件
        file = new File(Environment.getExternalStorageDirectory()+"/logistics", file_name);
        Glide.with(this).load(file).into(ivCamera);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if(!isSubmit) {
            isSubmit = true;
            progress.setVisibility(View.VISIBLE);
            getToken();
        }
    }

    private void getToken(){
        ApiRetrofit.getInstance().orderApi.GetQiniuToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean<String>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonBean<String> pageResponseJsonBean) {

                        if (pageResponseJsonBean.isSuccess()) {
                            uploadImage(file,pageResponseJsonBean.getData());

                        } else {
                            Toast.makeText(_mActivity, "加载失败 " + pageResponseJsonBean.getMsg(), Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                            isSubmit = false;
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Toast.makeText(_mActivity, "加载失败", Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.GONE);
                        isSubmit = false;
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void uploadImage(File file,String token){
        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zone2)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        UploadManager uploadManager = new UploadManager(config);

        uploadManager.put(file, null, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        if(info.isOK()) {
                            Log.i("qiniu", "Upload Success");
                            try {
                                String img = "http://otj3hc8no.bkt.clouddn.com/"+res.getString("key");
                                submitSign(Cache.account.getToken(),order_id,img);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("qiniu", "Upload Fail");
                            progress.setVisibility(View.GONE);
                            isSubmit = false;
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                        }
                        Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                    }
                }, null);
    }

    private void submitSign(String token,int order_id,String img){
        ApiRetrofit.getInstance().orderApi.SignOrder(token,order_id,img)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean<OrderSign>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonBean<OrderSign> pageResponseJsonBean) {

                        if (pageResponseJsonBean.isSuccess()) {
                            Toast.makeText(_mActivity, "签收成功", Toast.LENGTH_SHORT).show();
                            pop();

                        } else {
                            Toast.makeText(_mActivity, "签收失败 " + pageResponseJsonBean.getMsg(), Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                            isSubmit = false;
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Toast.makeText(_mActivity, "加载失败", Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.GONE);
                        isSubmit = false;
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
