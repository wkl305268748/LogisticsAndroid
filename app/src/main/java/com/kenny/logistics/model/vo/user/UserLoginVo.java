package com.kenny.logistics.model.vo.user;

import com.kenny.logistics.model.vo.carrier.CarrierVo;
import com.kenny.logistics.model.vo.fleet.FleetDriverVo;
import com.kenny.logistics.model.vo.shipper.ShipperVo;

import lombok.Data;

@Data
public class UserLoginVo {

    //("")
    Integer id;
    //("登录token")
    String token;
    //("")
    String username;
    //("")
    String phone;
    //("账户类型admin/carrier/shipper/driver")
    String type;
    //("昵称")
    String nickname;
    //("头像")
    String img;
    //("性别")
    String sex;

    //("所属承运商信息")
    CarrierVo carrierVo;
    //("所属货主信息")
    ShipperVo shipperVo;
    //("所属车主信息")
    FleetDriverVo driverVo;
}
