package com.kenny.logistics.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kenny.logistics.R;
import com.kenny.logistics.model.user.UserSet;
import com.kenny.logistics.ui.component.CircleImageView;
import com.kenny.logistics.ui.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 2017/8/8.
 */

public class ItemOrderCompanyAdapter extends RecyclerView.Adapter<ItemOrderCompanyAdapter.MyViewHolder> {
    private List<UserSet> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    private OnItemClickListener mClickListener;

    public ItemOrderCompanyAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<UserSet> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_order_company, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserSet item = mItems.get(position);
        Glide.with(context).load(item.getUserInfo().getImg()).into(holder.imageView);
        holder.company.setText(item.getUserInfo().getCompany());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public UserSet getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView company;
        private CircleImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.image);
            company = (TextView) itemView.findViewById(R.id.company);
        }

    }
}
