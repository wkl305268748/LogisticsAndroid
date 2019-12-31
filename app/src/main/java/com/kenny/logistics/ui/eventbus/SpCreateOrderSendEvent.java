package com.kenny.logistics.ui.eventbus;

import com.kenny.logistics.model.po.Order;

import java.util.Map;

public class SpCreateOrderSendEvent extends BaseCURDEvent<Order>{
    public SpCreateOrderSendEvent(Order data) {
        super(data);
    }
}
