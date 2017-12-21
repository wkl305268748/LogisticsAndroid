package com.kenny.logistics.driver.ui.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kenny.logistics.driver.R;
import com.kenny.logistics.driver.model.system.SystemVersion;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.error.CameraErrorCallback;
import io.fotoapparat.hardware.CameraException;
import io.fotoapparat.parameter.LensPosition;
import io.fotoapparat.result.PendingResult;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.view.CameraView;
import me.yokeyword.fragmentation.SupportFragment;

import static io.fotoapparat.log.Loggers.logcat;
import static io.fotoapparat.log.Loggers.loggers;
import static io.fotoapparat.parameter.selector.LensPositionSelectors.lensPosition;

/**
 * Created by Kenny on 2017/8/8.
 */

public class OrderSignCameraFragment extends SupportFragment {


    @BindView(R.id.camera_view)
    CameraView cameraView;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    Bundle bundle;
    Unbinder unbinder;

    Fotoapparat fotoapparat;

    public static OrderSignCameraFragment newInstance(Bundle args) {
        OrderSignCameraFragment fragment = new OrderSignCameraFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_sign_camera, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        bundle = getArguments();

        fotoapparat = Fotoapparat
                .with(_mActivity)
                .into(cameraView)
                .logger(logcat())
                .lensPosition(lensPosition(LensPosition.BACK))
                .cameraErrorCallback(new CameraErrorCallback() {
                    @Override
                    public void onError(CameraException e) {
                        Toast.makeText(_mActivity, e.toString(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                })
                .build();
        fotoapparat.start();
    }

    @OnClick(R.id.iv_camera)
    public void onIvCamera() {
        PhotoResult photoResult = fotoapparat.takePicture();

        String path = Environment.getExternalStorageDirectory()+"/logistics";
        File file = new File(path);
        if(!file.exists())
            file.mkdir();
        final String name = "logistics"+ System.currentTimeMillis()+".jpg";

        PendingResult result = photoResult.saveToFile(
                new File(path,name
        ));
        result.whenDone(new PendingResult.Callback() {
            @Override
            public void onResult(Object o) {
                bundle.putString("file",name);
                startWithPop(OrderSignSubmitFragment.newInstance(bundle));
            }
        });

    }

    @OnClick(R.id.tv_cancel)
    public void onTvCancel() {
        _mActivity.onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        fotoapparat.stop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
