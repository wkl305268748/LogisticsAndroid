package com.kenny.logistics.ui.fragment.shipper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.kenny.logistics.R;
import com.kenny.logistics.ui.LoginActivity;
import com.kenny.logistics.ui.fragment.base.BaseFragment;
import com.kenny.logistics.service.preferences.Proferences;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Kenny on 2017/8/8.
 */

public class SpHomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;

    Unbinder unbinder;

    public static SpHomeFragment newInstance() {
        Bundle args = new Bundle();
        SpHomeFragment fragment = new SpHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }


    public void initView() {
        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.banner1);
        images.add(R.mipmap.banner2);
        images.add(R.mipmap.banner3);

        List<String> text = new ArrayList<>();
        text.add("专业、高效的经营管理团队");
        text.add("以心传递 畅达天下");
        text.add("信誉所至，服务承诺");
        //设置展示类型
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置标题
        banner.setBannerTitles(text);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @OnClick({R.id.ll_create_order, R.id.ll_order, R.id.ll_message, R.id.ll_user, R.id.ll_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_create_order:
                SpMainFragment.instance.toFragment(1);
                break;
            case R.id.ll_order:
                SpMainFragment.instance.toFragment(2);
                break;
            case R.id.ll_message:
                SpMainFragment.instance.toFragment(3);
                break;
            case R.id.ll_user:
                SpMainFragment.instance.toFragment(4);
                break;
            case R.id.ll_logout:
                new MaterialStyledDialog.Builder(_mActivity)
                        .setTitle("提示!")
                        .setDescription("是否确定退出？")
                        .setPositiveText("确定")
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                _mActivity.finish();
                                Proferences.getInstance(getContext()).clearAppProferences();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }})
                        .show();
                break;
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
