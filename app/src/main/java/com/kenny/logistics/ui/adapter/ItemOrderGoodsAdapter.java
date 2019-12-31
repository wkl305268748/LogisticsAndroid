package com.kenny.logistics.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kenny.logistics.R;
import com.kenny.logistics.model.po.OrderGoods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 2017/8/8.
 */

public class ItemOrderGoodsAdapter extends BaseQuickAdapter<OrderGoods, BaseViewHolder> {

    public ItemOrderGoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderGoods item) {
        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.weight,item.getWeight().toString());
        helper.setText(R.id.number,item.getNumber().toString());
        helper.addOnClickListener(R.id.btn_delete);
    }
}
