package com.kenny.logistics.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kenny.logistics.R;
import com.kenny.logistics.model.order.OrderGoods;
import com.kenny.logistics.model.user.UserSet;
import com.kenny.logistics.ui.component.CircleImageView;
import com.kenny.logistics.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 2017/8/8.
 */

public class ItemOrderGoodsAdapter extends RecyclerView.Adapter<ItemOrderGoodsAdapter.MyViewHolder> {
    private List<OrderGoods> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    private OnItemClickListener mClickListener;
    private boolean isDelete;

    public ItemOrderGoodsAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<OrderGoods> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    public void setDatas(List<OrderGoods> items,boolean isDelete) {
        mItems.clear();
        mItems.addAll(items);
        this.isDelete = isDelete;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_order_goods, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        OrderGoods item = mItems.get(position);
        holder.name.setText(item.getName());
        holder.weight.setText("货物重量："+item.getWeight());
        holder.number.setText("货物数量："+item.getNumber());
        if(isDelete) {
            holder.btn_delete.setVisibility(View.VISIBLE);
            holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(position, v, holder);
                    }
                }
            });
        }else{
            holder.btn_delete.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public OrderGoods getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name,weight,number;
        private Button btn_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            weight = (TextView) itemView.findViewById(R.id.weight);
            number = (TextView) itemView.findViewById(R.id.number);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
        }

    }
}
