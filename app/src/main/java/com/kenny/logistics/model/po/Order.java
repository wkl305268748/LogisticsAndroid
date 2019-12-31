package com.kenny.logistics.model.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Created by Kenny on 2017/9/15.
 */
@Data
public class Order {
    String token;
    String sendName = "";
    String sendPhone = "";
    String sendAddr = "";
    String sendAddrInfo = "";
    String reciveName = "";
    String recivePhone = "";
    String reciveAddr = "";
    String reciveAddrInfo = "";
    Date sendTime;
    Date reciveTime;
    String dispatchingType = "";
    List<OrderGoods> goods = new ArrayList<>();
}
