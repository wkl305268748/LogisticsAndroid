package com.kenny.logistics.model.vo.fleet;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FleetDriverVo {
	//("")
	private Integer id;
	//("姓名")
	private String name;
	//("")
	private String sex;
	//("手机号")
	private String phone;
	//("是否短信通知司机")
	private Boolean isSms;
	//("身份证号码")
	private String idcard;
	//("邮箱")
	private String email;
	//("籍贯")
	private String hometown;
	//("")
	private String remark;
	//("")
	private Date time;
	//("所属承运商的ID")
	private Integer fkCarrierId;
	//("银行卡号")
	private String bankNumber;
	//("开户行")
	private String bankAddr;
	//("")
	private Boolean visible;
	//状态
	String status;
	//身份证照片
	String idcardImg;
	//驾驶证
	String driverLicense;
	//行驶证
	String drivingLicense;
	//从业资格证
	String workLicense;
}
