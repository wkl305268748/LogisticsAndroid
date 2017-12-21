package com.kenny.logistics.driver.model.fleet;

import java.util.Date;

//@ApiModel("")
public class FleetCar{
	//"")
	private Integer id;
	//"车牌号如皖A5504")
	private String plate;
	//"车辆类型如面包车")
	private String type;
	//"车辆所属如自有车辆")
	private String resource;
	//"挂车车牌")
	private String two_plate;
	//"随车电话")
	private String driver_phone;
	//"随车司机姓名")
	private String driver_name;
	//"能耗类型")
	private String energy;
	//"车辆长度")
	private String length;
	//"核定载重吨")
	private Float weight;
	//"汽车VIN码")
	private String vin;
	//"车辆品牌")
	private String brand;
	//"发动机号")
	private String engine;
	//"车轴数")
	private String axle;
	//"轴距")
	private String wheelbase;
	//"轮胎数量")
	private String tire;
	//"出厂日期")
	private Date factory_time;
	//"购买日期")
	private Date buy_time;
	//"购买价格")
	private Integer buy_price;
	//"年审日期")
	private Date limited_time;
	//"二级维护有效期")
	private Date tow_maintain_time;
	//"保险单号")
	private String insurance_policy;
	//"保险公司")
	private String insurance_company;
	//"保险有效期")
	private Date insurance_time;
	//"车头照片")
	private String front_img;
	//"车尾照片")
	private String tail_img;
	//"车辆备注")
	private String remark;
	//"")
	private Date time;
	//"所属用户的外键")
	private Integer belong_user_id;
	//"是否存在")
	private Boolean visible;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getPlate(){
		return plate;
	}

	public void setPlate(String plate){
		this.plate = plate;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getResource(){
		return resource;
	}

	public void setResource(String resource){
		this.resource = resource;
	}

	public String getTwo_plate(){
		return two_plate;
	}

	public void setTwo_plate(String two_plate){
		this.two_plate = two_plate;
	}

	public String getDriver_phone(){
		return driver_phone;
	}

	public void setDriver_phone(String driver_phone){
		this.driver_phone = driver_phone;
	}

	public String getDriver_name(){
		return driver_name;
	}

	public void setDriver_name(String driver_name){
		this.driver_name = driver_name;
	}

	public String getEnergy(){
		return energy;
	}

	public void setEnergy(String energy){
		this.energy = energy;
	}

	public String getLength(){
		return length;
	}

	public void setLength(String length){
		this.length = length;
	}

	public Float getWeight(){
		return weight;
	}

	public void setWeight(Float weight){
		this.weight = weight;
	}

	public String getVin(){
		return vin;
	}

	public void setVin(String vin){
		this.vin = vin;
	}

	public String getBrand(){
		return brand;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getEngine(){
		return engine;
	}

	public void setEngine(String engine){
		this.engine = engine;
	}

	public String getAxle(){
		return axle;
	}

	public void setAxle(String axle){
		this.axle = axle;
	}

	public String getWheelbase(){
		return wheelbase;
	}

	public void setWheelbase(String wheelbase){
		this.wheelbase = wheelbase;
	}

	public String getTire(){
		return tire;
	}

	public void setTire(String tire){
		this.tire = tire;
	}

	public Date getFactory_time(){
		return factory_time;
	}

	public void setFactory_time(Date factory_time){
		this.factory_time = factory_time;
	}

	public Date getBuy_time(){
		return buy_time;
	}

	public void setBuy_time(Date buy_time){
		this.buy_time = buy_time;
	}

	public Integer getBuy_price(){
		return buy_price;
	}

	public void setBuy_price(Integer buy_price){
		this.buy_price = buy_price;
	}

	public Date getLimited_time(){
		return limited_time;
	}

	public void setLimited_time(Date limited_time){
		this.limited_time = limited_time;
	}

	public Date getTow_maintain_time(){
		return tow_maintain_time;
	}

	public void setTow_maintain_time(Date tow_maintain_time){
		this.tow_maintain_time = tow_maintain_time;
	}

	public String getInsurance_policy(){
		return insurance_policy;
	}

	public void setInsurance_policy(String insurance_policy){
		this.insurance_policy = insurance_policy;
	}

	public String getInsurance_company(){
		return insurance_company;
	}

	public void setInsurance_company(String insurance_company){
		this.insurance_company = insurance_company;
	}

	public Date getInsurance_time(){
		return insurance_time;
	}

	public void setInsurance_time(Date insurance_time){
		this.insurance_time = insurance_time;
	}

	public String getFront_img(){
		return front_img;
	}

	public void setFront_img(String front_img){
		this.front_img = front_img;
	}

	public String getTail_img(){
		return tail_img;
	}

	public void setTail_img(String tail_img){
		this.tail_img = tail_img;
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

	public Integer getBelong_user_id(){
		return belong_user_id;
	}

	public void setBelong_user_id(Integer belong_user_id){
		this.belong_user_id = belong_user_id;
	}

	public Boolean getVisible(){
		return visible;
	}

	public void setVisible(Boolean visible){
		this.visible = visible;
	}

}
