package com.kenny.logistics.driver.model.user;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Date regtime;
    private Boolean is_disable;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Boolean getIsDisable() {
        return is_disable;
    }

    public void setIsDisable(Boolean isDisable) {
        this.is_disable = isDisable;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}