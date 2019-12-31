package com.kenny.logistics.model.vo.shipper;

import com.kenny.logistics.model.vo.carrier.CarrierVo;

import lombok.Data;

import java.util.Date;

@Data
public class ShipperVo {

    //("id")
    Integer id;
    //("所属的承运商")
    Integer fkCarrierId;
    //("所属的承运商")
    CarrierVo carrierVo;
    //("运输差价比")
    Double carrierTax;
    //("公司名")
    String name;
    //("公司地址")
    String address;
    //("公司电话")
    String telphone;
    //("公司营业执照")
    String businessLicense;
    //("公司电子章")
    String businessSeal;
    //("公司资金")
    Integer money;
    //("银行卡")
    String bank;
    //("开户行")
    String bankName;
    //("状态：normal 正常/no_verify 未提交审核/verify 正在审核流程")
    String status;
    //("创建时间")
    Date time;

}
