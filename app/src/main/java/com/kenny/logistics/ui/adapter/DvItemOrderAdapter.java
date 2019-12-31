package com.kenny.logistics.ui.adapter;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kenny.logistics.R;
import com.kenny.logistics.model.vo.order.OrderVo;
import com.kenny.logistics.util.DateUtil;

/**
 * Created by Kenny on 2017/8/8.
 */

public class DvItemOrderAdapter extends BaseQuickAdapter<OrderVo,BaseViewHolder> {

    public DvItemOrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderVo item) {
        switch (item.getStatus()){
            case "ORDER_PLACE":
                helper.setText(R.id.tv_status,"已下单");
                helper.setImageResource(R.id.im_status,R.mipmap.ic_order_superscript);
                helper.setTextColor(R.id.tv_time,ContextCompat.getColor(mContext, R.color.color_order_pending));
                helper.setGone(R.id.ll_taking,false);
                helper.setGone(R.id.ll_sign,false);
                break;
            case "ORDER_TAKING":
                helper.setText(R.id.tv_status,"已处理");
                helper.setImageResource(R.id.im_status,R.mipmap.ic_order_superscript_taking);
                helper.setTextColor(R.id.tv_time,ContextCompat.getColor(mContext, R.color.color_order_taking));
                helper.setText(R.id.tvTakingCompany,item.getCarrierVo().getName());
                helper.setText(R.id.tvTakingDriver,item.getFleetDriverVo().getName() + "("+item.getFleetDriverVo().getPhone()+")");
                helper.setText(R.id.tvTakingMoney,item.getRecive().toString());
                helper.setGone(R.id.ll_taking,true);
                helper.setGone(R.id.ll_sign,false);
                break;
            case "ORDER_SIGN":
                helper.setText(R.id.tv_status,"已签收");
                helper.setImageResource(R.id.im_status,R.mipmap.ic_order_superscript_sign);
                helper.setTextColor(R.id.tv_time,ContextCompat.getColor(mContext, R.color.color_order_sign));
                helper.setText(R.id.tvTakingCompany,item.getCarrierVo().getName());
                helper.setText(R.id.tvTakingDriver,item.getFleetDriverVo().getName() + "("+item.getFleetDriverVo().getPhone()+")");
                helper.setText(R.id.tvTakingMoney,item.getRecive().toString());
                helper.setText(R.id.tvSignTime,DateUtil.DateToString(item.getSignTime(), DateUtil.DateStyle.YYYY_MM_DD_CN));
                helper.setGone(R.id.ll_taking,true);
                helper.setGone(R.id.ll_sign,true);
                break;
        }
        helper.setText(R.id.tvNumber,"订单号："+ item.getOrderNumber());
        helper.setText(R.id.tvDispatching,item.getDispatchingType());
        helper.setText(R.id.tvSendAdd,item.getSendAddr().split("/")[0]);
        helper.setText(R.id.tvSendAdd1,item.getSendAddr());
        helper.setText(R.id.tvReciveAdd,item.getReciveAddr().split("/")[0]);
        helper.setText(R.id.tvReciveAdd1,item.getReciveAddr());

        helper.setText(R.id.tvSendTime, DateUtil.DateToString(item.getSendTime(), DateUtil.DateStyle.YYYY_MM_DD_CN));
        helper.setText(R.id.tvReciveTime,DateUtil.DateToString(item.getReciveTime(), DateUtil.DateStyle.YYYY_MM_DD_CN));

        helper.addOnClickListener(R.id.map);
    }
}
