package com.kenny.logistics.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kenny.logistics.R;
import com.kenny.logistics.api.ApiRetrofit;
import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.PageResponse;
import com.kenny.logistics.model.user.UserSet;
import com.kenny.logistics.ui.adapter.ItemOrderCompanyAdapter;
import com.kenny.logistics.ui.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

public class CreateOrderCompanyFragment extends SupportFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Unbinder unbinder;

    ItemOrderCompanyAdapter itemOrderCompanyAdapter;

    public static CreateOrderCompanyFragment newInstance() {
        Bundle args = new Bundle();
        CreateOrderCompanyFragment fragment = new CreateOrderCompanyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_order_company, container, false);
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
        itemOrderCompanyAdapter = new ItemOrderCompanyAdapter(_mActivity);
        recycler.setLayoutManager(new LinearLayoutManager(_mActivity));
        itemOrderCompanyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Bundle bundle = new Bundle();
                bundle.putInt("company_id",itemOrderCompanyAdapter.getItem(position).getUser().getId());
                bundle.putString("company_name",itemOrderCompanyAdapter.getItem(position).getUserInfo().getCompany());
                setFragmentResult(RESULT_OK, bundle);
                pop();
            }
        });
        initCompany();
    }

    private void initCompany(){
        progressBar.setVisibility(View.VISIBLE);
        ApiRetrofit.getInstance().userApi.CompanyInfoListEx(0,100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean<PageResponse<UserSet>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull JsonBean<PageResponse<UserSet>> pageResponseJsonBean) {
                        progressBar.setVisibility(View.GONE);
                        if(pageResponseJsonBean.isSuccess()) {
                            List<UserSet> userSets = pageResponseJsonBean.getData().getItem();
                            itemOrderCompanyAdapter.setDatas(userSets);
                            recycler.setAdapter(itemOrderCompanyAdapter);
                        }else{
                            Toast.makeText(_mActivity,"加载失败 "+pageResponseJsonBean.getMsg(),Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(_mActivity,"加载失败",Toast.LENGTH_SHORT);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
