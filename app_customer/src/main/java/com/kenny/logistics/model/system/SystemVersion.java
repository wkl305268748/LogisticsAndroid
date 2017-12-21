package com.kenny.logistics.model.system;

import java.util.Date;


//@ApiModel("")
public class SystemVersion{
	//@ApiModelProperty("")
	private Integer id;
	//@ApiModelProperty("类型")
	private String type;
	//@ApiModelProperty("版本名称")
	private String version;
	//@ApiModelProperty("版本号")
	private Integer version_number;
	//@ApiModelProperty("更新记录")
	private String changelog;
	//@ApiModelProperty("下载地址")
	private String url;
	//@ApiModelProperty("上传时间")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getVersion(){
		return version;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public Integer getVersion_number(){
		return version_number;
	}

	public void setVersion_number(Integer version_number){
		this.version_number = version_number;
	}

	public String getChangelog(){
		return changelog;
	}

	public void setChangelog(String changelog){
		this.changelog = changelog;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
