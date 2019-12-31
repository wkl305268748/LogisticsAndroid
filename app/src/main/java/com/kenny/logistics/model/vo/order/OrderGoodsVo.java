package com.kenny.logistics.model.vo.order;

import lombok.Data;

@Data
public class OrderGoodsVo {
	//("货物ID")
	Integer id;
	//("货物名称")
	String name;
	//("货物的体积")
	String size;
	//("货物总重量，单位吨")
	Double weight;
	//("件数")
	Double number;
	//("运费")
	Double freight;
	//("货物备注")
	String remark;
}
