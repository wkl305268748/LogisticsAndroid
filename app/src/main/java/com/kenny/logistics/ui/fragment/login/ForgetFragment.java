package com.kenny.logistics.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kenny.logistics.R;
import com.kenny.logistics.service.presenter.ForgetPresenter;
import com.kenny.logistics.service.view.ForgetView;
import com.kenny.logistics.ui.component.DialogLoading;
import com.kenny.logistics.ui.fragment.base.BaseBackFragment;
import com.kenny.logistics.util.ValidateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Kenny on 2017/12/22.
 */

public class ForgetFragment extends BaseBackFragment {

    Unbinder unbinder;
    ForgetPresenter presenter;
    DialogLoading loading;

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public String type;

    public static ForgetFragment newInstance(String type) {
        Bundle args = new Bundle();
        ForgetFragment fragment = new ForgetFragment();
        fragment.setArguments(args);
        fragment.type = type;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_forget, container, false);
        unbinder = ButterKnife.bind(this, view);
        initToolbarNav(toolbar);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        presenter = new ForgetPresenter(getContext());
        presenter.onCreate(forgetView);
        loading = new DialogLoading(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        if (presenter != null)
            presenter.onDistory();
    }

    @OnClick({R.id.tv_get_code, R.id.btn_repassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                if (check_code()) {
                    presenter.onGetCode(etPhone.getText().toString(),type);
                    loading.show();
                }
                break;
            case R.id.btn_repassword:
                if (check_register()) {
                    presenter.onRePassword(etPhone.getText().toString(), type, etPassword.getText().toString(), etCode.getText().toString());
                    loading.show();
                }
                break;
        }
    }

    private boolean check_code() {
        if (!ValidateUtil.isPhone(etPhone.getText().toString())) {
            Toast.makeText(getContext(), "请输入正确手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean check_register() {
        if (!ValidateUtil.isPhone(etPhone.getText().toString())) {
            Toast.makeText(getContext(), "请输入正确手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!ValidateUtil.isPassword(etPassword.getText().toString())) {
            Toast.makeText(getContext(), "请输入正确密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etCode.getText().toString().equals("")) {
            Toast.makeText(getContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private ForgetView forgetView = new ForgetView() {

        @Override
        public void onRePassword() {
            Toast.makeText(getContext(), "密码修改成功！", Toast.LENGTH_SHORT).show();
            _mActivity.onBackPressed();
        }

        @Override
        public void onGetCode() {
            Toast.makeText(getContext(), "验证码已发送", Toast.LENGTH_SHORT).show();
            presenter.onCountDown(60L);
            tvGetCode.setEnabled(false);
        }

        @Override
        public void onCount(Long second) {
            tvGetCode.setText(second + "秒后重新获取");
        }

        @Override
        public void onCountDown() {
            tvGetCode.setText("重新获取");
            tvGetCode.setEnabled(true);
        }

        @Override
        public void onSuccess(Object info) {

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
