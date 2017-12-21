package com.kenny.logistics.driver.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import com.kenny.logistics.driver.R;
import com.kenny.logistics.driver.model.order.OrderSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 2017/8/8.
 */

public class ItemOrderAdapter extends RecyclerView.Adapter<ItemOrderAdapter.MyViewHolder> {
    private List<OrderSet> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mClickListener;
    Context context;

    public ItemOrderAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setDatas(List<OrderSet> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_order, parent, false);
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        OrderSet item = mItems.get(position);
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyy年MM月dd日");

        holder.tvTime.setText(myFmt.format(item.getOrder().getTime()));

        Drawable up = ContextCompat.getDrawable(context,R.mipmap.ic_order_superscript);
        Drawable drawableUp= DrawableCompat.wrap(up);

        if(item.getOrder().getStatus().equals("ORDER_PLACE")) {
            holder.tvStatus.setText("已下单");
            DrawableCompat.setTint(drawableUp, ContextCompat.getColor(context,R.color.color_order_pending));
            holder.im_status.setImageDrawable(drawableUp);
            holder.tvTime.setTextColor(ContextCompat.getColor(context, R.color.color_order_pending));
        }
        if(item.getOrder().getStatus().equals("ORDER_TAKING")) {
            holder.tvStatus.setText("已处理");

            DrawableCompat.setTint(drawableUp, ContextCompat.getColor(context,R.color.color_order_taking));
            holder.im_status.setImageDrawable(drawableUp);
            holder.tvTime.setTextColor(ContextCompat.getColor(context, R.color.color_order_taking));
        }
        if(item.getOrder().getStatus().equals("ORDER_SIGN")) {
            holder.tvStatus.setText("已签收");
            DrawableCompat.setTint(drawableUp, ContextCompat.getColor(context,R.color.color_order_sign));
            holder.im_status.setImageDrawable(drawableUp);
            holder.tvTime.setTextColor(ContextCompat.getColor(context, R.color.color_order_sign));
        }
        if(item.getOrder().getStatus().equals("ORDER_REFUSE")) {
            holder.tvStatus.setText("已拒绝");
            DrawableCompat.setTint(drawableUp, ContextCompat.getColor(context,R.color.color_order_warning));
            holder.im_status.setImageDrawable(drawableUp);
            holder.tvTime.setTextColor(ContextCompat.getColor(context, R.color.color_order_warning));
        }
        holder.tvNumber.setText("订单号："+ item.getOrder().getOrder_number());

        String sendSplit[] = item.getOrderCustomer().getSend_addr().split("/");
        holder.tvSendAdd.setText(item.getOrderCustomer().getSend_name());
        holder.tvSendAdd1.setText(item.getOrderCustomer().getSend_addr());

        String reciveSplit[] = item.getOrderCustomer().getRecive_addr().split("/");
        holder.tvReciveAdd.setText(item.getOrderCustomer().getRecive_name());
        holder.tvReciveAdd1.setText(item.getOrderCustomer().getRecive_addr());

        holder.tvSendTime.setText(myFmt2.format(item.getOrderCustomer().getSend_time()));
        holder.tvReciveTime.setText(myFmt2.format(item.getOrderCustomer().getRecive_time()));

        if(!item.getOrder().getIs_company())
            holder.tvCompany.setText("未指定公司");
        else
            holder.tvCompany.setText(item.getWantCompany().getUserInfo().getCompany());
        holder.tvDispatching.setText(item.getOrderCustomer().getDispatching_type());

        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onItemMapClick(position, v, holder);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public OrderSet getItem(int position) {
        return mItems.get(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNumber, tvTime, tvStatus, tvSendAdd, tvSendAdd1, tvReciveAdd, tvReciveAdd1,
                tvSendTime,tvReciveTime,tvDispatching,tvCompany;
        private ImageView im_status;
        private View map;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
            tvSendAdd = (TextView) itemView.findViewById(R.id.tvSendAdd);
            tvSendAdd1 = (TextView) itemView.findViewById(R.id.tvSendAdd1);
            tvReciveAdd = (TextView) itemView.findViewById(R.id.tvReciveAdd);
            tvReciveAdd1 = (TextView) itemView.findViewById(R.id.tvReciveAdd1);
            tvReciveAdd1 = (TextView) itemView.findViewById(R.id.tvReciveAdd1);
            tvSendTime = (TextView) itemView.findViewById(R.id.tvSendTime);
            tvReciveTime = (TextView) itemView.findViewById(R.id.tvReciveTime);
            tvDispatching = (TextView) itemView.findViewById(R.id.tvDispatching);
            tvCompany = (TextView) itemView.findViewById(R.id.tvCompany);

            im_status = (ImageView) itemView.findViewById(R.id.im_status);
            map = itemView.findViewById(R.id.map);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view, RecyclerView.ViewHolder vh);
        void onItemMapClick(int position, View view, RecyclerView.ViewHolder vh);
    }

}
