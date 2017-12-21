package com.kenny.logistics.model.order;

import java.util.Date;

//@ApiModel("订单处理表")
public class OrderTaking{
	//"")
	private Integer id;
	//"订单外键")
	private Integer fk_order_id;
	//"车辆外键")
	private Integer fk_car_id;
	//"司机外键")
	private Integer fk_driver_id;
	//"运费")
	private Float freight;
	//"保险费用")
	private Float safes;
	//"应付账款")
	private Float recive;
	//"应收账款")
	private Float pay;
	//"")
	private Date time;

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

	public Integer getFk_car_id(){
		return fk_car_id;
	}

	public void setFk_car_id(Integer fk_car_id){
		this.fk_car_id = fk_car_id;
	}

	public Integer getFk_driver_id(){
		return fk_driver_id;
	}

	public void setFk_driver_id(Integer fk_driver_id){
		this.fk_driver_id = fk_driver_id;
	}

	public Float getRecive(){
		return recive;
	}

	public void setRecive(Float recive){
		this.recive = recive;
	}

	public Float getPay(){
		return pay;
	}

	public void setPay(Float pay){
		this.pay = pay;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public Float getFreight() {
		return freight;
	}

	public void setFreight(Float freight) {
		this.freight = freight;
	}

	public Float getSafes() {
		return safes;
	}

	public void setSafes(Float safes) {
		this.safes = safes;
	}
}
