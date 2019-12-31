package com.kenny.logistics.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.kenny.logistics.R;
import com.kenny.logistics.model.vo.user.UserLoginVo;
import com.kenny.logistics.service.presenter.LoginPresenter;
import com.kenny.logistics.service.view.BaseView;
import com.kenny.logistics.ui.MainActivity;
import com.kenny.logistics.ui.component.DialogLoading;
import com.kenny.logistics.ui.fragment.base.BaseFragment;
import com.kenny.logistics.service.preferences.AppProferences;
import com.kenny.logistics.service.preferences.Proferences;
import com.kenny.logistics.util.ValidateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Kenny on 2017/12/22.
 */

public class TabLoginFragment extends BaseFragment {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;

    Unbinder unbinder;
    LoginPresenter presenter;
    DialogLoading loading;

    public String type = LoginFragment.TYPE_SHIPPER;

    public static TabLoginFragment newInstance(String type) {
        Bundle args = new Bundle();
        TabLoginFragment fragment = new TabLoginFragment();
        fragment.setArguments(args);
        fragment.type = type;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        presenter = new LoginPresenter(getContext());
        presenter.onCreate(loginView);
        loading = new DialogLoading(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(presenter != null)
            presenter.onDistory();
    }

    @OnClick({R.id.tv_forget, R.id.btn_login, R.id.tv_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:
                ((LoginFragment)getParentFragment()).start(ForgetFragment.newInstance(type));
                break;
            case R.id.btn_login:
                if(check()){
                    presenter.onLogin(etPhone.getText().toString(), etPassword.getText().toString(),type);
                    loading.show();
                }
                break;
            case R.id.tv_contact:
                break;
        }
    }

    private boolean check(){
        if(!ValidateUtil.isPhone(etPhone.getText().toString())){
            Toast.makeText(getContext(), "请输入正确手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!ValidateUtil.isPassword(etPassword.getText().toString())){
            Toast.makeText(getContext(), "请输入正确密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private BaseView loginView = new BaseView<UserLoginVo>() {
        @Override
        public void onSuccess(UserLoginVo userToken) {
            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
            AppProferences proferences = Proferences.getInstance(getContext()).getAppProferences();
            proferences.setToken(userToken.getToken());
            proferences.setPhone(userToken.getUsername());
            proferences.setType(type);

            //上报登录事件
            //JAnalyticsInterface.onEvent(getContext(), new LoginEvent("phone_android",true).addKeyValue("phone", etPhone.getText().toString()));
            //跳转
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        }

        @Override
        public void onError(String result) {
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplate() {
            loading.hide();
        }
    };
}
