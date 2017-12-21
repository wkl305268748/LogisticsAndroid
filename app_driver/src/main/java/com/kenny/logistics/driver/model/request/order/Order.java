package com.kenny.logistics.driver.model.request.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 2017/9/15.
 */

public class Order {
    String send_name = "";
    String send_phone = "";
    String send_addr = "";
    String send_addr_info = "";
    String recive_name = "";
    String recive_phone = "";
    String recive_addr = "";
    String recive_addr_info = "";
    String send_time = "";
    String recive_time = "";
    String dispatching_type = "";
    List<String> goods = new ArrayList<>();
    Boolean is_company = false;
    Integer fk_want_company_id = -1;

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    public String getSend_phone() {
        return send_phone;
    }

    public void setSend_phone(String send_phone) {
        this.send_phone = send_phone;
    }

    public String getSend_addr() {
        return send_addr;
    }

    public void setSend_addr(String send_addr) {
        this.send_addr = send_addr;
    }

    public String getSend_addr_info() {
        return send_addr_info;
    }

    public void setSend_addr_info(String send_addr_info) {
        this.send_addr_info = send_addr_info;
    }

    public String getRecive_name() {
        return recive_name;
    }

    public void setRecive_name(String recive_name) {
        this.recive_name = recive_name;
    }

    public String getRecive_phone() {
        return recive_phone;
    }

    public void setRecive_phone(String recive_phone) {
        this.recive_phone = recive_phone;
    }

    public String getRecive_addr() {
        return recive_addr;
    }

    public void setRecive_addr(String recive_addr) {
        this.recive_addr = recive_addr;
    }

    public String getRecive_addr_info() {
        return recive_addr_info;
    }

    public void setRecive_addr_info(String recive_addr_info) {
        this.recive_addr_info = recive_addr_info;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getRecive_time() {
        return recive_time;
    }

    public void setRecive_time(String recive_time) {
        this.recive_time = recive_time;
    }

    public String getDispatching_type() {
        return dispatching_type;
    }

    public void setDispatching_type(String dispatching_type) {
        this.dispatching_type = dispatching_type;
    }

    public Boolean getIs_company() {
        return is_company;
    }

    public void setIs_company(Boolean is_company) {
        this.is_company = is_company;
    }

    public Integer getFk_want_company_id() {
        return fk_want_company_id;
    }

    public void setFk_want_company_id(Integer fk_want_company_id) {
        this.fk_want_company_id = fk_want_company_id;
    }

    public List<String> getGoods() {
        return goods;
    }

    public void setGoods(List<String> goods) {
        this.goods = goods;
    }
}
