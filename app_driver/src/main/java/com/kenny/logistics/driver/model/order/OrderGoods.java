package com.kenny.logistics.driver.model.order;

//@ApiModel("货物信息，用户下单时需要提供货物信息")
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
	private Float weight;
	//"件数")
	private Integer number;
	//"运费")
	private Float freight;
	//"货物备注")
	private String remark;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_order_id() {
		return fk_order_id;
	}

	public void setFk_order_id(Integer fk_order_id) {
		this.fk_order_id = fk_order_id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getSize(){
		return size;
	}

	public void setSize(String size){
		this.size = size;
	}

	public Float getWeight(){
		return weight;
	}

	public void setWeight(Float weight){
		this.weight = weight;
	}

	public Integer getNumber(){
		return number;
	}

	public void setNumber(Integer number){
		this.number = number;
	}

	public Float getFreight(){
		return freight;
	}

	public void setFreight(Float freight){
		this.freight = freight;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
