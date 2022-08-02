package com.ernest.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 客户表	可以由工作人员导入
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "cus_id", type = IdType.AUTO)
    private Integer cusId;

    /**
     * 账号
     */
    private String cusAct;

    /**
     * 密码
     */
    private String cusPwd;

    /**
     * 盐值
     */
    private String cusSalt;

    /**
     * 客户姓名
     */
    private String cusName;

    /**
     * 客户手机号
     */
    private String cusPhone;

    /**
     * 客户发出运单数
     */
    private Integer cusOrder;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusAct() {
        return cusAct;
    }

    public void setCusAct(String cusAct) {
        this.cusAct = cusAct;
    }

    public String getCusPwd() {
        return cusPwd;
    }

    public void setCusPwd(String cusPwd) {
        this.cusPwd = cusPwd;
    }

    public String getCusSalt() {
        return cusSalt;
    }

    public void setCusSalt(String cusSalt) {
        this.cusSalt = cusSalt;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public Integer getCusOrder() {
        return cusOrder;
    }

    public void setCusOrder(Integer cusOrder) {
        this.cusOrder = cusOrder;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdTime() {
        return updTime;
    }

    public void setUpdTime(LocalDateTime updTime) {
        this.updTime = updTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId=" + cusId +
                ", cusAct=" + cusAct +
                ", cusPwd=" + cusPwd +
                ", cusSalt=" + cusSalt +
                ", cusName=" + cusName +
                ", cusPhone=" + cusPhone +
                ", cusOrder=" + cusOrder +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
