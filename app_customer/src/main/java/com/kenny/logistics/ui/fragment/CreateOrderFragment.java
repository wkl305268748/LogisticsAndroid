package com.kenny.logistics.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kenny.logistics.R;
import com.kenny.logistics.api.ApiRetrofit;
import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.order.OrderCustomer;
import com.kenny.logistics.model.order.OrderGoods;
import com.kenny.logistics.model.request.order.Order;
import com.kenny.logistics.ui.adapter.ItemOrderGoodsAdapter;
import com.kenny.logistics.ui.listener.OnItemClickListener;
import com.kenny.logistics.ui.preferences.Cache;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kenny on 2017/8/8.
 */

public class CreateOrderFragment extends SupportFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.tv_send_name)
    TextView tvSendName;
    @BindView(R.id.tv_send_addr)
    TextView tvSendAddr;
    @BindView(R.id.tv_recive_name)
    TextView tvReciveName;
    @BindView(R.id.tv_recive_addr)
    TextView tvReciveAddr;
    @BindView(R.id.dispatching)
    RadioGroup dispatching;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.tv_recive_time)
    TextView tvReciveTime;
    @BindView(R.id.tv_want_company)
    TextView tvWantCompany;
    @BindView(R.id.st_is_company)
    Switch stIsCompany;
    @BindView(R.id.ll_want_company)
    LinearLayout llWantCompany;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_goods)
    RecyclerView recyclerGoods;

    private Order order = new Order();
    private List<OrderGoods> orderGoodses = new ArrayList<>();
    ItemOrderGoodsAdapter itemOrderGoodsAdapter;

    private final int REQ_CODE_SEND = 1;
    private final int REQ_CODE_RECIVE = 2;
    private final int REQ_CODE_COMPANY = 3;
    private final int REQ_CODE_GOODS = 4;

    public static CreateOrderFragment newInstance() {
        Bundle args = new Bundle();
        CreateOrderFragment fragment = new CreateOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_order, container, false);
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
        stIsCompany.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                order.setIs_company(isChecked);
                if (isChecked)
                    llWantCompany.setVisibility(View.VISIBLE);
                else
                    llWantCompany.setVisibility(View.GONE);
            }
        });

        //列表设置
        itemOrderGoodsAdapter = new ItemOrderGoodsAdapter(_mActivity);
        recyclerGoods.setLayoutManager(new LinearLayoutManager(_mActivity));
        itemOrderGoodsAdapter.setDatas(orderGoodses);
        recyclerGoods.setAdapter(itemOrderGoodsAdapter);
        itemOrderGoodsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                orderGoodses.remove(position);
                itemOrderGoodsAdapter.setDatas(orderGoodses);
                itemOrderGoodsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_send, R.id.ll_recive, R.id.ll_dispatching_type, R.id.ll_send_time, R.id.ll_recive_time, R.id.ll_want_company, R.id.ll_add_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_send:
                Bundle bundle = new Bundle();
                bundle.putString("send_name", order.getSend_name());
                bundle.putString("send_phone", order.getSend_phone());
                bundle.putString("send_addr", order.getSend_addr());
                bundle.putString("send_addr_info", order.getSend_addr_info());
                startForResult(CreateOrderSendFragment.newInstance(bundle), REQ_CODE_SEND);
                break;
            case R.id.ll_recive:
                Bundle bundle2 = new Bundle();
                bundle2.putString("recive_name", order.getRecive_name());
                bundle2.putString("recive_phone", order.getRecive_phone());
                bundle2.putString("recive_addr", order.getRecive_addr());
                bundle2.putString("recive_addr_info", order.getRecive_addr_info());
                startForResult(CreateOrderReciveFragment.newInstance(bundle2), REQ_CODE_RECIVE);
                break;
            case R.id.ll_dispatching_type:
                break;
            case R.id.ll_send_time:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear++;
                                tvSendTime.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
                                order.setSend_time(tvSendTime.getText().toString());
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.ll_recive_time:
                Calendar now1 = Calendar.getInstance();
                DatePickerDialog dpd1 = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear++;
                                tvReciveTime.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
                                order.setRecive_time(tvReciveTime.getText().toString());
                            }
                        },
                        now1.get(Calendar.YEAR),
                        now1.get(Calendar.MONTH),
                        now1.get(Calendar.DAY_OF_MONTH)
                );
                dpd1.show(getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.ll_want_company:
                startForResult(CreateOrderCompanyFragment.newInstance(), REQ_CODE_COMPANY);
                break;
            case R.id.ll_add_goods:
                startForResult(CreateOrderGoodsFragment.newInstance(), REQ_CODE_GOODS);
                break;
        }
    }

    @OnClick(R.id.btn_ok)
    public void onViewClicked() {
        if (dispatching.getCheckedRadioButtonId() == R.id.dispatching1)
            order.setDispatching_type("配送");
        else
            order.setDispatching_type("自提");

        if (order.getSend_name().equals("") || order.getSend_addr().equals("") || order.getSend_phone().equals("") || order.getSend_addr_info().equals("")) {
            Snackbar.make(getView(), "请完善发货人信息", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (order.getRecive_name().equals("") || order.getRecive_addr().equals("") || order.getRecive_phone().equals("") || order.getRecive_addr_info().equals("")) {
            Snackbar.make(getView(), "请完善收货人信息", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (order.getSend_time().equals("")) {
            Snackbar.make(getView(), "请选择发货时间", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (order.getRecive_time().equals("")) {
            Snackbar.make(getView(), "请选择到达时间", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (order.getIs_company() && order.getFk_want_company_id() == -1) {
            Snackbar.make(getView(), "请选择指定物流公司", Snackbar.LENGTH_LONG).show();
            return;
        }

        String[] goodstr = new String[orderGoodses.size()];
        Gson gson = new Gson();
        for (int i=0;i<orderGoodses.size();i++) {
            goodstr[i] = gson.toJson(orderGoodses.get(i));
        }

        progressBar.setVisibility(View.VISIBLE);
        ApiRetrofit.getInstance().orderApi.insert(Cache.account.getToken(),
                order.getSend_name(), order.getSend_phone(), order.getSend_addr(), order.getSend_addr_info(),
                order.getRecive_name(), order.getRecive_phone(), order.getRecive_addr(), order.getRecive_addr_info(),
                order.getSend_time(), order.getRecive_time(), order.getDispatching_type(), order.getIs_company(), order.getFk_want_company_id(),goodstr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean<OrderCustomer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonBean<OrderCustomer> orderCustomerJsonBean) {

                        progressBar.setVisibility(View.GONE);
                        if (orderCustomerJsonBean.isSuccess()) {
                            Toast.makeText(_mActivity, "提交成功！", Toast.LENGTH_SHORT).show();
                            pop();

                            Bundle bundle = new Bundle();
                            bundle.putInt("order_id",orderCustomerJsonBean.getData().getFk_order_id());
                            MainFragment.instance.toFragment(5,bundle);
                        } else
                            Toast.makeText(_mActivity, "提交失败 " + orderCustomerJsonBean.getMsg(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(_mActivity, "提交失败！", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SEND && resultCode == RESULT_OK) {
            // 在此通过Bundle data 获取返回的数据
            order.setSend_name(data.getString("send_name"));
            order.setSend_phone(data.getString("send_phone"));
            order.setSend_addr(data.getString("send_addr"));
            order.setSend_addr_info(data.getString("send_addr_info"));

            tvSendName.setText(order.getSend_name() + "(" + order.getSend_phone() + ")");
            tvSendAddr.setText(order.getSend_addr() + "    " + order.getSend_addr_info());
        }

        if (requestCode == REQ_CODE_RECIVE && resultCode == RESULT_OK) {
            // 在此通过Bundle data 获取返回的数据
            order.setRecive_name(data.getString("recive_name"));
            order.setRecive_phone(data.getString("recive_phone"));
            order.setRecive_addr(data.getString("recive_addr"));
            order.setRecive_addr_info(data.getString("recive_addr_info"));

            tvReciveName.setText(order.getRecive_name() + "(" + order.getRecive_phone() + ")");
            tvReciveAddr.setText(order.getRecive_addr() + "    " + order.getRecive_addr_info());
        }

        if (requestCode == REQ_CODE_COMPANY && resultCode == RESULT_OK) {
            // 在此通过Bundle data 获取返回的数据
            order.setFk_want_company_id(data.getInt("company_id"));
            tvWantCompany.setText(data.getString("company_name"));
        }

        if (requestCode == REQ_CODE_GOODS && resultCode == RESULT_OK) {
            // 在此通过Bundle data 获取返回的数据
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setName(data.getString("goods_name"));
            orderGoods.setWeight(data.getFloat("goods_weight"));
            orderGoods.setNumber(data.getInt("goods_number"));
            orderGoods.setSize(data.getString("goods_size"));
            orderGoods.setRemark(data.getString("goods_remark"));
            orderGoodses.add(orderGoods);

            itemOrderGoodsAdapter.setDatas(orderGoodses);
            itemOrderGoodsAdapter.notifyDataSetChanged();
        }
    }
}
