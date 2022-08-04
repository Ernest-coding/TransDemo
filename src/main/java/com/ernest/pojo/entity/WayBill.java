package com.ernest.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 运单表
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Repository
@TableName("way_bill")
public class WayBill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "wb_id", type = IdType.AUTO)
    private Integer wbId;

    /**
     * 运单号
     */
    private String wbNum;

    /**
     * 货物信息 id，逻辑映射到 cargo_info 表
     */
    private Integer wbCgiId;

    /**
     * 类型 1-国内运单  2-国际运单
     */
    private Integer wbType;

    /**
     * 发出人 id，逻辑映射到 customer 表
     */
    private Integer wbSendId;

    /**
     * 发出人姓名
     */
    private String wbSendName;

    /**
     * 发出人手机号
     */
    private String wbSendPhone;

    /**
     * 发出地址
     */
    private String wbSendAddress;

    /**
     * 发出时间
     */
    private LocalDateTime wbSendTime;

    /**
     * 收件人姓名
     */
    private String wbReceName;

    /**
     * 收件人手机号
     */
    private String wbRecePhone;

    /**
     * 收件人地址
     */
    private String wbReceAddress;

    /**
     * 签收时间
     */
    private LocalDateTime wbReceTime;

    /**
     * 运费金额
     */
    private BigDecimal wbPrice;

    /**
     * 状态   1-已下单（国际运单还包括检视报关中）  2-已发出  3-运输中  4-到港  5-运输中  6-已签收
     */
    private Integer wbStatus;

    /**
     * 运单说明信息
     */
    private String wbInfo;

    /**
     * 运单添加人员 id，逻辑映射到 manager 表
     */
    private Integer wbOpId;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    public Integer getWbId() {
        return wbId;
    }

    public void setWbId(Integer wbId) {
        this.wbId = wbId;
    }

    public String getWbNum() {
        return wbNum;
    }

    public void setWbNum(String wbNum) {
        this.wbNum = wbNum;
    }

    public Integer getWbCgiId() {
        return wbCgiId;
    }

    public void setWbCgiId(Integer wbCgiId) {
        this.wbCgiId = wbCgiId;
    }

    public Integer getWbType() {
        return wbType;
    }

    public void setWbType(Integer wbType) {
        this.wbType = wbType;
    }

    public Integer getWbSendId() {
        return wbSendId;
    }

    public void setWbSendId(Integer wbSendId) {
        this.wbSendId = wbSendId;
    }

    public String getWbSendName() {
        return wbSendName;
    }

    public void setWbSendName(String wbSendName) {
        this.wbSendName = wbSendName;
    }

    public String getWbSendPhone() {
        return wbSendPhone;
    }

    public void setWbSendPhone(String wbSendPhone) {
        this.wbSendPhone = wbSendPhone;
    }

    public String getWbSendAddress() {
        return wbSendAddress;
    }

    public void setWbSendAddress(String wbSendAddress) {
        this.wbSendAddress = wbSendAddress;
    }

    public LocalDateTime getWbSendTime() {
        return wbSendTime;
    }

    public void setWbSendTime(LocalDateTime wbSendTime) {
        this.wbSendTime = wbSendTime;
    }

    public String getWbReceName() {
        return wbReceName;
    }

    public void setWbReceName(String wbReceName) {
        this.wbReceName = wbReceName;
    }

    public String getWbRecePhone() {
        return wbRecePhone;
    }

    public void setWbRecePhone(String wbRecePhone) {
        this.wbRecePhone = wbRecePhone;
    }

    public String getWbReceAddress() {
        return wbReceAddress;
    }

    public void setWbReceAddress(String wbReceAddress) {
        this.wbReceAddress = wbReceAddress;
    }

    public LocalDateTime getWbReceTime() {
        return wbReceTime;
    }

    public void setWbReceTime(LocalDateTime wbReceTime) {
        this.wbReceTime = wbReceTime;
    }

    public BigDecimal getWbPrice() {
        return wbPrice;
    }

    public void setWbPrice(BigDecimal wbPrice) {
        this.wbPrice = wbPrice;
    }

    public Integer getWbStatus() {
        return wbStatus;
    }

    public void setWbStatus(Integer wbStatus) {
        this.wbStatus = wbStatus;
    }

    public String getWbInfo() {
        return wbInfo;
    }

    public void setWbInfo(String wbInfo) {
        this.wbInfo = wbInfo;
    }

    public Integer getWbOpId() {
        return wbOpId;
    }

    public void setWbOpId(Integer wbOpId) {
        this.wbOpId = wbOpId;
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
        return "WayBill{" +
                "wbId=" + wbId +
                ", wbNum=" + wbNum +
                ", wbCgiId=" + wbCgiId +
                ", wbType=" + wbType +
                ", wbSendId=" + wbSendId +
                ", wbSendAddress=" + wbSendAddress +
                ", wbSendTime=" + wbSendTime +
                ", wbReceName=" + wbReceName +
                ", wbRecePhone=" + wbRecePhone +
                ", wbReceAddress=" + wbReceAddress +
                ", wbReceTime=" + wbReceTime +
                ", wbPrice=" + wbPrice +
                ", wbStatus=" + wbStatus +
                ", wbInfo=" + wbInfo +
                ", wbOpId=" + wbOpId +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
