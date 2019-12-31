package com.kenny.logistics.ui.fragment.shipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kenny.logistics.R;
import com.kenny.logistics.model.po.Order;
import com.kenny.logistics.model.vo.order.OrderVo;
import com.kenny.logistics.service.presenter.SpCreateOrderPresenter;
import com.kenny.logistics.service.view.BaseView;
import com.kenny.logistics.ui.adapter.ItemOrderGoodsAdapter;
import com.kenny.logistics.ui.component.DialogLoading;
import com.kenny.logistics.ui.eventbus.BaseCURDEvent;
import com.kenny.logistics.ui.eventbus.SpCreateOrderGoodsEvent;
import com.kenny.logistics.ui.eventbus.SpCreateOrderReciveEvent;
import com.kenny.logistics.ui.eventbus.SpCreateOrderSendEvent;
import com.kenny.logistics.ui.fragment.base.BaseBackFragment;
import com.kenny.logistics.service.preferences.Proferences;
import com.kenny.logistics.util.DateUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Kenny on 2017/8/8.
 */

public class SpCreateOrderFragment extends BaseBackFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.recycler_goods)
    RecyclerView recyclerGoods;

    private Order order = new Order();
    ItemOrderGoodsAdapter itemOrderGoodsAdapter;

    SpCreateOrderPresenter presenter;
    Unbinder unbinder;
    DialogLoading loading;

    public static SpCreateOrderFragment newInstance() {
        Bundle args = new Bundle();
        SpCreateOrderFragment fragment = new SpCreateOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        initToolbarNav(toolbar);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //设置persenter
        presenter = new SpCreateOrderPresenter(getContext());
        presenter.onCreate(view);
        loading = new DialogLoading(getContext());

        //列表设置
        recyclerGoods.setLayoutManager(new LinearLayoutManager(getContext()));
        itemOrderGoodsAdapter = new ItemOrderGoodsAdapter(R.layout.item_order_goods);
        recyclerGoods.setAdapter(itemOrderGoodsAdapter);

        itemOrderGoodsAdapter.setOnItemClickListener((adapter, view, position) -> {
            start(SpCreateOrderGoodsAEFragment.newInstance(itemOrderGoodsAdapter.getItem(position),position));
        });
        itemOrderGoodsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            itemOrderGoodsAdapter.remove(position);
        });
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(presenter != null){
            presenter.onDistory();
        }
        EventBus.getDefault().unregister(this);
    }


    //EventBus事件
    @Subscribe
    public void onMessageEvent(SpCreateOrderSendEvent event) {
        switch (event.getEvent()) {
            case BaseCURDEvent.EVENT_ADD:
                order.setSendName(event.getData().getSendName());
                order.setSendPhone(event.getData().getSendPhone());
                order.setSendAddr(event.getData().getSendAddr());
                order.setSendAddrInfo(event.getData().getSendAddrInfo());

                tvSendName.setText(order.getSendName() + "(" + order.getSendPhone() + ")");
                tvSendAddr.setText(order.getSendAddr() + "    " + order.getSendAddrInfo());
                break;
        }
    }

    @Subscribe
    public void onMessageEvent(SpCreateOrderReciveEvent event) {
        switch (event.getEvent()) {
            case BaseCURDEvent.EVENT_ADD:
                order.setReciveName(event.getData().getReciveName());
                order.setRecivePhone(event.getData().getRecivePhone());
                order.setReciveAddr(event.getData().getReciveAddr());
                order.setReciveAddrInfo(event.getData().getReciveAddrInfo());

                tvReciveName.setText(order.getReciveName() + "(" + order.getRecivePhone() + ")");
                tvReciveAddr.setText(order.getReciveAddr() + "    " + order.getReciveAddrInfo());
                break;
        }
    }

    @Subscribe
    public void onMessageEvent(SpCreateOrderGoodsEvent event) {

        switch (event.getEvent()) {
            case BaseCURDEvent.EVENT_ADD:
                itemOrderGoodsAdapter.addData(event.getData());
                break;
            case BaseCURDEvent.EVENT_EDIT:
                itemOrderGoodsAdapter.setData(event.getPosition(),event.getData());
                break;
        }
    }

    @OnClick({R.id.ll_send, R.id.ll_recive, R.id.ll_dispatching_type, R.id.ll_send_time, R.id.ll_recive_time, R.id.ll_add_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_send:
                start(SpCreateOrderAddFragment.newInstance(order,SpCreateOrderAddFragment.TYPE_SEND));
                break;
            case R.id.ll_recive:
                start(SpCreateOrderAddFragment.newInstance(order,SpCreateOrderAddFragment.TYPE_RECIVE));
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
                                tvSendTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                                order.setSendTime(DateUtil.StringToDate(tvSendTime.getText().toString(), DateUtil.DateStyle.YYYY_MM_DD));
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
                                tvReciveTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                                order.setReciveTime(DateUtil.StringToDate(tvReciveTime.getText().toString(), DateUtil.DateStyle.YYYY_MM_DD));
                            }
                        },
                        now1.get(Calendar.YEAR),
                        now1.get(Calendar.MONTH),
                        now1.get(Calendar.DAY_OF_MONTH)
                );
                dpd1.show(getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.ll_add_goods:
                start(SpCreateOrderGoodsAEFragment.newInstance());
                break;
        }
    }

    @OnClick(R.id.btn_ok)
    public void onViewClicked() {
        if (dispatching.getCheckedRadioButtonId() == R.id.dispatching1)
            order.setDispatchingType("配送");
        else
            order.setDispatchingType("自提");

        if (order.getSendName().equals("") || order.getSendAddr().equals("") || order.getSendPhone().equals("") || order.getSendAddrInfo().equals("")) {
            Snackbar.make(getView(), "请完善发货人信息", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (order.getReciveName().equals("") || order.getReciveAddr().equals("") || order.getRecivePhone().equals("") || order.getReciveAddrInfo().equals("")) {
            Snackbar.make(getView(), "请完善收货人信息", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (order.getSendTime().equals("")) {
            Snackbar.make(getView(), "请选择发货时间", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (order.getReciveTime().equals("")) {
            Snackbar.make(getView(), "请选择到达时间", Snackbar.LENGTH_LONG).show();
            return;
        }

        order.setToken(Proferences.getInstance(getContext()).getAppProferences().getToken());
        order.setGoods(itemOrderGoodsAdapter.getData());
        presenter.onInsert(order);
    }

    private BaseView view = new BaseView<OrderVo>() {
        @Override
        public void onSuccess(OrderVo info) {
            Toast.makeText(_mActivity, "提交成功！", Toast.LENGTH_SHORT).show();
            pop();
        }

        @Override
        public void onError(String result) {
            Toast.makeText(_mActivity, "提交失败！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplate() {
            loading.hide();
        }
    };

}

