package com.kenny.logistics.driver.model.order;

import java.util.Date;

//@ApiModel("定单签收表")
public class OrderSign{
	//"")
	private Integer id;
	//"订单表id")
	private Integer fk_order_id;
	//"签收照片")
	private String order_img;
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

	public String getOrder_img(){
		return order_img;
	}

	public void setOrder_img(String order_img){
		this.order_img = order_img;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
