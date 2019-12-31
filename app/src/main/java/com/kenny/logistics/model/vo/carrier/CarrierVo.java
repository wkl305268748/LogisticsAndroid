package com.kenny.logistics.model.vo.carrier;

import lombok.Data;

import java.util.Date;

@Data
public class CarrierVo {

    //("id")
    Integer id;
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
    //("运输许可证")
    String transportLicense;
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

    //宝付支付信息
    //("商户号")
    String baofuMemberId;
    //("终端号")
    String baofuTerminalId;
    //("宝付返回解析的publicKey")
    String baofuPublicKey;
    //("商户的私钥")
    String baofuMineKey;
    //("商户私钥的密码")
    String baofuMineKeyPass;
}
