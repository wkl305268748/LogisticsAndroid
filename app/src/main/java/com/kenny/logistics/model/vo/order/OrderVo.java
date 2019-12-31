package com.kenny.logistics.model.vo.order;

import com.kenny.logistics.model.vo.carrier.CarrierVo;
import com.kenny.logistics.model.vo.fleet.FleetCarVo;
import com.kenny.logistics.model.vo.fleet.FleetDriverVo;
import com.kenny.logistics.model.vo.shipper.ShipperVo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderVo {

    //订单信息
    //("订单ID")
    Integer orderId;
    //("订单号")
    String orderNumber;
    //("下单的货主")
    ShipperVo shipperVo;
    Integer fkShipperId;
    //("是否指定承运商接单")
    Boolean isAssign;
    //("货主指定接单的承运商")
    CarrierVo assignCarrierVo;
    Integer fkAssignCarrierId;
    //("接单的承运商")
    CarrierVo carrierVo;
    Integer fkCarrierId;
    //("订单状态，关联status表")
    String status;
    //("开票状态")
    String invoiceStatus;

    //下单信息
    //("发件人姓名")
    String sendName;
    //("发件人手机")
    String sendPhone;
    //("发件人省市区地址，用/隔开")
    String sendAddr;
    //("发件人详细地址")
    String sendAddrInfo;
    //("收件人姓名")
    String reciveName;
    //("收件人电话")
    String recivePhone;
    //("收件人地址")
    String reciveAddr;
    //("收件人详细地址")
    String reciveAddrInfo;
    //("预计发送时间")
    Date sendTime;
    //("限时到达时间")
    Date reciveTime;
    //("类型：配送/自提")
    String dispatchingType;
    //("下单时间")
    Date orderTime;
    //下单货物信息
    //("货物信息")
    List<OrderGoodsVo> orderGoods;

    //接单信息
    //("车辆外键")
    Integer fkCarId;
    //("车")
    FleetCarVo fleetCarVo;
    //("司机外键")
    Integer fkDriverId;
    //("司机")
    FleetDriverVo fleetDriverVo;
    //("运费")
    Integer freight;
    //("保险费用")
    Integer safes;
    //("应收账款")
    Integer recive;
    //("应付账款")
    Integer pay;
    //("运输差价")
    Integer diff;
    //("运输服务费比例")
    Double tax;
    //("")
    Date takingTime;

    //签收信息
    //("签收照片")
    String orderImg;
    //("签收评分")
    Integer rate;
    //("签收时间")
    Date signTime;

    //发票信息
    //("发票ID")
    String invoiceId;
    //("发票开具流水号")
    String invoiceSerialNumber;
    //("税号")
    String invoiceNumber;
    //("单位地址")
    String invoiceAddr;
    //("电话号码")
    String invoiceTel;
    //("开户银行")
    String invoiceBank;
    //("银行账户")
    String invoiceCard;
    //("开票总金额")
    String invoiceMoney;
    //("开票时间")
    Date invoiceTime;

    String shipperBusinessSeal;
    String carrierBusinessSeal;
}
