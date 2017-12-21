package com.kenny.logistics.model.user;

import java.util.Date;

public class Sms {
    private Integer id;
    private String code;
    private String phone;
    private String cookie;
    private Integer code_type_id;
    private Date sendtime;
    private Date subtime;
    private Boolean is_submit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie == null ? null : cookie.trim();
    }

    public Integer getCodeTypeId() {
        return code_type_id;
    }

    public void setCodeTypeId(Integer codeTypeId) {
        this.code_type_id = codeTypeId;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getSubtime() {
        return subtime;
    }

    public void setSubtime(Date subtime) {
        this.subtime = subtime;
    }

    public Boolean getIs_submit() {
        return is_submit;
    }

    public void setIs_submit(Boolean is_submit) {
        this.is_submit = is_submit;
    }
}