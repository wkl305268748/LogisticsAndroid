package com.kenny.logistics.model.vo.user;

import com.kenny.logistics.model.vo.carrier.CarrierVo;
import com.kenny.logistics.model.vo.fleet.FleetDriverVo;
import com.kenny.logistics.model.vo.shipper.ShipperVo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {

    Integer id;
    String username;
    String phone;
    ////("账户类型admin/carrier/shipper/driver")
    String type;
    //("注册时间")
    Date regtime;
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
    //("所属司机信息")
    FleetDriverVo driverVo;
}
