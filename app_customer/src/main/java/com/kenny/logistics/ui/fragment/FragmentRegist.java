package com.kenny.logistics.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kenny.logistics.R;
import com.kenny.logistics.api.ApiRetrofit;
import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.user.Sms;
import com.kenny.logistics.model.user.UserToken;
import com.kenny.logistics.ui.MainActivity;
import com.kenny.logistics.ui.base.BaseLoginFragment;
import com.kenny.logistics.ui.component.EditTextWithDel;
import com.kenny.logistics.ui.component.PaperButton;
import com.kenny.logistics.ui.preferences.Cache;
import com.kenny.logistics.util.CheckUtils;
import com.kenny.logistics.util.Tools;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FragmentRegist extends BaseLoginFragment {
    @BindView(R.id.next)
    PaperButton nextBt;
    @BindView(R.id.userpassword)
    EditTextWithDel userpassword;
    @BindView(R.id.send_smscode)
    PaperButton sendsmscode;
    @BindView(R.id.userphone)
    EditTextWithDel userphone;
    @BindView(R.id.smscode)
    EditTextWithDel smscode;
    @BindView(R.id.fg_regist)
    LinearLayout fg_regist;
    @BindView(R.id.rela_rephone)
    RelativeLayout rela_rephone;
    @BindView(R.id.rela_recode)
    RelativeLayout rela_recode;
    @BindView(R.id.rela_repass)
    RelativeLayout rela_repass;
    @BindView(R.id.usericon)
    ImageView phoneIv;
    @BindView(R.id.keyicon)
    ImageView keyIv;
    @BindView(R.id.codeicon)
    ImageView passIv;
    MyCountTimer timer;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_regist;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        TextListener();
    }

    private void TextListener() {
        //用户名改变背景变
        userphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = userphone.getText().toString();
                if (CheckUtils.isMobile(text)) {
                    //抖动
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));

                }

            }
        });
        //验证码改变背景变
        smscode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));


            }
        });
        //密码改变背景变
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                rela_repass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
            }
        });
    }

    String cookie;
    private void initView() {
        //发送验证码点击事件
        sendsmscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘

                if(!issms)
                    return;

                final View view = v;
                String phone = userphone.getText().toString();
                boolean mobile = CheckUtils.isMobile(phone);
                if (!TextUtils.isEmpty(phone)) {
                    if (mobile) {
                        ApiRetrofit.getInstance().userApi.SendSms(phone)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<JsonBean<Sms>>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onNext(@NonNull JsonBean<Sms> smsJsonBean) {
                                        if (smsJsonBean.isSuccess()) {
                                            //验证码发送成功
                                            timer = new MyCountTimer(60000, 1000);
                                            timer.start();
                                            showSnackar(view, "提示：短信发送成功");
                                            cookie = smsJsonBean.getData().getCookie();
                                        } else {
                                            showSnackar(view, "提示：短信发送失败：" + smsJsonBean.getMsg());
                                        }
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    } else {
                        rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                        phoneIv.setAnimation(Tools.shakeAnimation(2));
                        showSnackar(view, "提示：输入手机号码");
                    }
                } else {
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(view, "提示：手机号码不正确");
                }

            }
        });
        //下一步的点击事件
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                final String password = userpassword.getText().toString();
                String code = smscode.getText().toString();
                final String phone = userphone.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(view, "提示：请输入手机号码");

                    return;
                }
                if (!CheckUtils.isMobile(phone)) {
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(view, "提示：手机号不正确");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    keyIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(view, "提示：请输入验证码");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    rela_repass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    passIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(view, "提示：请输入密码");
                    return;
                }

                ApiRetrofit.getInstance().userApi.Register(cookie, code, phone, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<JsonBean<UserToken>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@NonNull JsonBean<UserToken> userTokenJsonBean) {
                                if (userTokenJsonBean.isSuccess()) {
                                    Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT);

                                    Cache.account.setToken(userTokenJsonBean.getData().getToken());
                                    Cache.account.setUserName(phone);

                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    showSnackar(view, "提示：注册失败：" + userTokenJsonBean.getMsg());
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                showSnackar(view, "提示：注册失败");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }

    boolean issms = true;
    //事件定时器
    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            sendsmscode.setText((millisUntilFinished / 1000) + "秒后重发");
            sendsmscode.setClickable(false);
            issms = false;
        }

        @Override
        public void onFinish() {
            sendsmscode.setText("重新发送");
            sendsmscode.setClickable(true);
            issms = true;
        }
    }

    //回收timer
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}

