package com.kenny.logistics.model.vo.fleet;

import lombok.Data;


@Data
public class FleetCarVo {
	//("")
	private Integer id;
	//("车牌号如皖A5504")
	private String plate;
	//("车辆类型如面包车")
	private String type;
	//("车辆所属如自有车辆")
	private String resource;
	//("挂车车牌")
	private String twoPlate;
	//("随车电话")
	private String driverPhone;
	//("随车司机姓名")
	private String driverName;
	//("能耗类型")
	private String energy;
	//("车辆长度")
	private String length;
	//("核定载重吨")
	private Float weight;
	//("汽车VIN码")
	private String vin;
	//("车辆品牌")
	private String brand;
	//("发动机号")
	private String engine;
	//("车轴数")
	private String axle;
	//("轴距")
	private String wheelbase;
	//("轮胎数量")
	private String tire;
	//("出厂日期")
	private String factoryTime;
	//("购买日期")
	private String buyTime;
	//("购买价格")
	private Integer buyPrice;
	//("年审日期")
	private String limitedTime;
	//("二级维护有效期")
	private String towMaintainTime;
	//("保险单号")
	private String insurancePolicy;
	//("保险公司")
	private String insuranceCompany;
	//("保险有效期")
	private String insuranceTime;
	//("车头照片")
	private String frontImg;
	//("车尾照片")
	private String tailImg;
	//("车辆备注")
	private String remark;
	//("")
	private String time;
	//("所属用户的外键")
	private Integer fkCarrierId;
	//("是否存在")
	private Boolean visible;
}
