package com.kenny.logistics.driver.model.user;

import java.util.Date;

//@ApiModel("用户信息表")
public class UserInfo{
	//@ApiModelProperty("")
	private Integer id;
	//@ApiModelProperty("")
	private Integer user_id;
	//@ApiModelProperty("")
	private String nickname;
	//@ApiModelProperty("")
	private String sex;
	//@ApiModelProperty("")
	private String img;
	//@ApiModelProperty("")
	private Date birthday;
	//@ApiModelProperty("公司名称")
	private String company;
	//@ApiModelProperty("账户余额")
	private Float money;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getUser_id(){
		return user_id;
	}

	public void setUser_id(Integer user_id){
		this.user_id = user_id;
	}

	public String getNickname(){
		return nickname;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getSex(){
		return sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getImg(){
		return img;
	}

	public void setImg(String img){
		this.img = img;
	}

	public Date getBirthday(){
		return birthday;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}
}
