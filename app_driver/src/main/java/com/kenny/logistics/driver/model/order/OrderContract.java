package com.kenny.logistics.driver.model.order;

import java.util.Date;

//@ApiModel("合同信息表")
public class OrderContract{
	//"")
	private Integer id;
	//"订单外键")
	private Integer fk_order_id;
	//"订单号")
	private String order_number;
	//"合同编号")
	private String contract_number;
	//"甲方")
	private String aname;
	//"乙方")
	private String bname;
	//"账户名")
	private String bbank_name;
	//"账号")
	private String bbank_number;
	//"开户行")
	private String bbank;
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

	public String getOrder_number(){
		return order_number;
	}

	public void setOrder_number(String order_number){
		this.order_number = order_number;
	}

	public String getContract_number(){
		return contract_number;
	}

	public void setContract_number(String contract_number){
		this.contract_number = contract_number;
	}

	public String getAname(){
		return aname;
	}

	public void setAname(String aname){
		this.aname = aname;
	}

	public String getBname(){
		return bname;
	}

	public void setBname(String bname){
		this.bname = bname;
	}

	public String getBbank_name(){
		return bbank_name;
	}

	public void setBbank_name(String bbank_name){
		this.bbank_name = bbank_name;
	}

	public String getBbank_number(){
		return bbank_number;
	}

	public void setBbank_number(String bbank_number){
		this.bbank_number = bbank_number;
	}

	public String getBbank(){
		return bbank;
	}

	public void setBbank(String bbank){
		this.bbank = bbank;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
