package com.kenny.logistics.model.order;

//@ApiModel("订单状态码信息")
public class OrderStatusValue{
	//"")
	private Integer id;
	//"")
	private String value;
	//"")
	private String remark;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getValue(){
		return value;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
