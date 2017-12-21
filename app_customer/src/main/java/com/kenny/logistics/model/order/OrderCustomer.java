package com.kenny.logistics.model.order;

import java.util.Date;

//@ApiModel("客户订单表")
public class OrderCustomer{
	//"")
	private Integer id;
	//"订单id")
	private Integer fk_order_id;
	//"订单号")
	private String order_number;
	//"发件人姓名")
	private String send_name;
	//"发件人手机")
	private String send_phone;
	//"发件人省市区地址，用/隔开")
	private String send_addr;
	//"发件人详细地址")
	private String send_addr_info;
	//"收件人姓名")
	private String recive_name;
	//"收件人电话")
	private String recive_phone;
	//"收件人地址")
	private String recive_addr;
	//"收件人详细地址")
	private String recive_addr_info;
	//"预计发送时间")
	private Date send_time;
	//"限时到达时间")
	private Date recive_time;
	//"配送还是自提")
	private String dispatching_type;
	//"")
	private String remark;
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

	public String getSend_name(){
		return send_name;
	}

	public void setSend_name(String send_name){
		this.send_name = send_name;
	}

	public String getSend_phone(){
		return send_phone;
	}

	public void setSend_phone(String send_phone){
		this.send_phone = send_phone;
	}

	public String getSend_addr(){
		return send_addr;
	}

	public void setSend_addr(String send_addr){
		this.send_addr = send_addr;
	}

	public String getSend_addr_info(){
		return send_addr_info;
	}

	public void setSend_addr_info(String send_addr_info){
		this.send_addr_info = send_addr_info;
	}

	public String getRecive_name(){
		return recive_name;
	}

	public void setRecive_name(String recive_name){
		this.recive_name = recive_name;
	}

	public String getRecive_phone(){
		return recive_phone;
	}

	public void setRecive_phone(String recive_phone){
		this.recive_phone = recive_phone;
	}

	public String getRecive_addr(){
		return recive_addr;
	}

	public void setRecive_addr(String recive_addr){
		this.recive_addr = recive_addr;
	}

	public String getRecive_addr_info(){
		return recive_addr_info;
	}

	public void setRecive_addr_info(String recive_addr_info){
		this.recive_addr_info = recive_addr_info;
	}

	public Date getSend_time(){
		return send_time;
	}

	public void setSend_time(Date send_time){
		this.send_time = send_time;
	}

	public Date getRecive_time(){
		return recive_time;
	}

	public void setRecive_time(Date recive_time){
		this.recive_time = recive_time;
	}

	public String getDispatching_type(){
		return dispatching_type;
	}

	public void setDispatching_type(String dispatching_type){
		this.dispatching_type = dispatching_type;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public Integer getFk_order_id() {
		return fk_order_id;
	}

	public void setFk_order_id(Integer fk_order_id) {
		this.fk_order_id = fk_order_id;
	}
}
