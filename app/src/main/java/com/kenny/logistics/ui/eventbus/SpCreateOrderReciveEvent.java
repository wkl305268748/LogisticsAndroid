package com.kenny.logistics.ui.eventbus;

import com.kenny.logistics.model.po.Order;

public class SpCreateOrderReciveEvent extends BaseCURDEvent<Order>{
    public SpCreateOrderReciveEvent(Order data) {
        super(data);
    }
}
