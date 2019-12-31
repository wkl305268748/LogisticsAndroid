package com.kenny.logistics.model.po;

import lombok.Data;

//@ApiModel("货物信息，用户下单时需要提供货物信息")
@Data
public class OrderGoods{
	//"")
	private Integer id;
	//"订单表ID")
	private Integer fk_order_id;
	//"货物名称")
	private String name;
	//"货物的体积")
	private String size;
	//"货物总重量，单位吨")
	private Double weight;
	//"件数")
	private Double number;
	//"运费")
	private Double freight;
	//"货物备注")
	private String remark;
}
