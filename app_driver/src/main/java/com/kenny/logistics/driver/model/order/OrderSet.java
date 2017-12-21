package com.kenny.logistics.driver.model.order;

import com.kenny.logistics.driver.model.fleet.FleetCar;
import com.kenny.logistics.driver.model.fleet.FleetDriver;
import com.kenny.logistics.driver.model.user.UserSet;

import java.util.List;

/**
 * Created by WKL on 2017-7-16.
 */
//@ApiModel("订单综合信息")
public class OrderSet {
    //"订单信息")
    Order order;
    //"下单信息")
    OrderCustomer orderCustomer;
    //"货物信息")
    List<OrderGoods> orderGoods;
    //"派单信息")
    OrderTaking orderTaking;
    //"签收信息")
    OrderSign orderSign;
    //"合同信息")
    OrderContract orderContract;
    //"订单操作状态")
    List<OrderStatus> orderStatuses;
    //"订单司机")
    FleetDriver fleetDriver;
    //"订单车辆")
    FleetCar fleetCar;
    //"下单用户信息")
    UserSet customer;
    //"指定接单物流公司信息")
    UserSet wantCompany;
    //"接单物流公司信息")
    UserSet company;

    public OrderCustomer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(OrderCustomer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public OrderTaking getOrderTaking() {
        return orderTaking;
    }

    public void setOrderTaking(OrderTaking orderTaking) {
        this.orderTaking = orderTaking;
    }

    public OrderSign getOrderSign() {
        return orderSign;
    }

    public void setOrderSign(OrderSign orderSign) {
        this.orderSign = orderSign;
    }

    public List<OrderStatus> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatus> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public FleetDriver getFleetDriver() {
        return fleetDriver;
    }

    public void setFleetDriver(FleetDriver fleetDriver) {
        this.fleetDriver = fleetDriver;
    }

    public FleetCar getFleetCar() {
        return fleetCar;
    }

    public void setFleetCar(FleetCar fleetCar) {
        this.fleetCar = fleetCar;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public OrderContract getOrderContract() {
        return orderContract;
    }

    public void setOrderContract(OrderContract orderContract) {
        this.orderContract = orderContract;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserSet getCustomer() {
        return customer;
    }

    public void setCustomer(UserSet customer) {
        this.customer = customer;
    }

    public UserSet getWantCompany() {
        return wantCompany;
    }

    public void setWantCompany(UserSet wantCompany) {
        this.wantCompany = wantCompany;
    }

    public UserSet getCompany() {
        return company;
    }

    public void setCompany(UserSet company) {
        this.company = company;
    }
}
