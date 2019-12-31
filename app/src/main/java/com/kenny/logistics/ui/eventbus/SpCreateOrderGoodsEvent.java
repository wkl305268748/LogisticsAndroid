package com.kenny.logistics.ui.eventbus;

import com.kenny.logistics.model.po.Order;
import com.kenny.logistics.model.po.OrderGoods;

public class SpCreateOrderGoodsEvent extends BaseCURDEvent<OrderGoods>{
    public SpCreateOrderGoodsEvent(OrderGoods data) {
        super(data);
    }

    public SpCreateOrderGoodsEvent(int position, OrderGoods data) {
        super(position, data);
    }

    public SpCreateOrderGoodsEvent(int position) {
        super(position);
    }

    public SpCreateOrderGoodsEvent(int position, OrderGoods data, int event) {
        super(position, data, event);
    }
}
