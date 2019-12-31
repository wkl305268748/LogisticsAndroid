package com.kenny.logistics.ui.fragment.shipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kenny.logistics.R;
import com.kenny.logistics.model.vo.PageVo;
import com.kenny.logistics.model.vo.order.OrderVo;
import com.kenny.logistics.service.presenter.SpOrderPresenter;
import com.kenny.logistics.service.view.BasePageView;
import com.kenny.logistics.ui.adapter.ItemOrderAdapter;
import com.kenny.logistics.ui.component.DialogLoading;
import com.kenny.logistics.ui.component.EmptyRecyclerView;
import com.kenny.logistics.ui.fragment.base.BaseFragment;
import com.kenny.logistics.service.preferences.Proferences;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Kenny on 2017/8/8.
 */

public class SpOrderFragment extends BaseFragment {

    @BindView(R.id.recycler)
    EmptyRecyclerView recycler;
    @BindView(R.id.empty_view)
    View emptyView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    int pageSize = 10;

    ItemOrderAdapter mAdapter;
    SpOrderPresenter presenter;
    Unbinder unbinder;
    DialogLoading loading;

    public static SpOrderFragment newInstance() {
        Bundle args = new Bundle();
        SpOrderFragment fragment = new SpOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        // 设置布局管理器
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ItemOrderAdapter(R.layout.item_order);
        recycler.setAdapter(mAdapter);
        //设置空布局
        recycler.setEmptyView(emptyView);

        //presenter
        presenter = new SpOrderPresenter(getContext());
        presenter.onCreate(basePageView);
        loading = new DialogLoading(getContext());
        //下拉上拉刷新
        refresh.setOnRefreshListener(refreshListener);
        refresh.setOnLoadMoreListener(loadmoreListener);

        presenter.onInit(Proferences.getInstance(getContext()).getAppProferences().getToken(), pageSize);
        loading.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null)
        {
            presenter.onDistory();
        }
    }


    //下拉刷新
    private OnRefreshListener refreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            presenter.onRefresh(Proferences.getInstance(getContext()).getAppProferences().getToken(), pageSize);
        }
    };
    //加载更多
    private OnLoadMoreListener loadmoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore(RefreshLayout refreshlayout) {
            int current = mAdapter.getItemCount()/pageSize + 1;
            if(current == 1){current = 2;}
            presenter.onLoadMore(Proferences.getInstance(getContext()).getAppProferences().getToken(), current, pageSize);
        }
    };

    private BasePageView<OrderVo> basePageView = new BasePageView<OrderVo>(){
        @Override
        public void onSuccess(Object info) {}

        @Override
        public void onError(String result) {
            refresh.finishRefresh();
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplate() {
            loading.cancel();
        }

        @Override
        public void onRefresh(PageVo<OrderVo> data) {
            mAdapter.replaceData(data.getItem());
            refresh.finishRefresh();
        }

        @Override
        public void onLoadMore(PageVo<OrderVo> data) {
            if (data.getItem().size() == 0) {
                Toast.makeText(getContext(), "已经到底了！", Toast.LENGTH_SHORT).show();
            } else {
                mAdapter.addData(data.getItem());
            }
            refresh.finishLoadMore();
        }

        @Override
        public void onInit(PageVo<OrderVo> data) {
            mAdapter.setNewData(data.getItem());
            refresh.finishRefresh();
        }
    };

}
