package com.kenny.logistics.model.vo.order;

import lombok.Data;

import java.util.Date;

@Data
public class OrderTakingVo {
	//("")
	private Integer id;
	//("订单外键")
	private Integer fkOrderId;
	//("车辆外键")
	private Integer fkCarId;
	//("司机外键")
	private Integer fkDriverId;
	//("运费")
	private Integer freight;
	//("保险费用")
	private Integer safes;
	//("应付账款")
	private Integer recive;
	//("应收账款")
	private Integer pay;
	//("运输差价")
	private Integer diff;
	//("运输服务费比例")
	private Double tax;
	//("")
	private Date time;
}
