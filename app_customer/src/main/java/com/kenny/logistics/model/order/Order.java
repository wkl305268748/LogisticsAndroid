package com.kenny.logistics.model.order;

import java.util.Date;

public class Order{
	//"")
	private Integer id;
	//"订单号")
	private String order_number;
	//"流水号")
	private String serial_number;
	//"下单的客户")
	private Integer fk_customer_id;
	//"是否指定物流公司接单")
	private Boolean is_company;
	//"客户指定接单的物流公司")
	private Integer fk_want_company_id;
	//"接单的物流公司")
	private Integer fk_company_id;
	//"订单状态，关联status表")
	private String status;
	//"")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getOrder_number(){
		return order_number;
	}

	public void setOrder_number(String order_number){
		this.order_number = order_number;
	}

	public String getSerial_number(){
		return serial_number;
	}

	public void setSerial_number(String serial_number){
		this.serial_number = serial_number;
	}

	public Integer getFk_customer_id(){
		return fk_customer_id;
	}

	public void setFk_customer_id(Integer fk_customer_id){
		this.fk_customer_id = fk_customer_id;
	}

	public Boolean getIs_company() {
		return is_company;
	}

	public void setIs_company(Boolean is_company) {
		this.is_company = is_company;
	}

	public Integer getFk_want_company_id(){
		return fk_want_company_id;
	}

	public void setFk_want_company_id(Integer fk_want_company_id){
		this.fk_want_company_id = fk_want_company_id;
	}

	public Integer getFk_company_id(){
		return fk_company_id;
	}

	public void setFk_company_id(Integer fk_company_id){
		this.fk_company_id = fk_company_id;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
