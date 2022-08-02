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
 * 货物信息表
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Repository
@TableName("cargo_info")
public class CargoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "cgi_id", type = IdType.AUTO)
    private Integer cgiId;

    /**
     * 货物类型：	1-办公用品	2-电子设备	3-需补充......
     */
    private Integer cgiType;

    /**
     * 货物名称
     */
    private String cgiName;

    /**
     * 货物编号 (系统自动生成)
     */
    private String cgiNumber;

    /**
     * 货物价值
     */
    private BigDecimal cgiValue;

    /**
     * 货物其他说明信息
     */
    private String cgiInfo;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    public Integer getCgiId() {
        return cgiId;
    }

    public void setCgiId(Integer cgiId) {
        this.cgiId = cgiId;
    }

    public Integer getCgiType() {
        return cgiType;
    }

    public void setCgiType(Integer cgiType) {
        this.cgiType = cgiType;
    }

    public String getCgiName() {
        return cgiName;
    }

    public void setCgiName(String cgiName) {
        this.cgiName = cgiName;
    }

    public String getCgiNumber() {
        return cgiNumber;
    }

    public void setCgiNumber(String cgiNumber) {
        this.cgiNumber = cgiNumber;
    }

    public BigDecimal getCgiValue() {
        return cgiValue;
    }

    public void setCgiValue(BigDecimal cgiValue) {
        this.cgiValue = cgiValue;
    }

    public String getCgiInfo() {
        return cgiInfo;
    }

    public void setCgiInfo(String cgiInfo) {
        this.cgiInfo = cgiInfo;
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
        return "CargoInfo{" +
                "cgiId=" + cgiId +
                ", cgiType=" + cgiType +
                ", cgiName=" + cgiName +
                ", cgiNumber=" + cgiNumber +
                ", cgiValue=" + cgiValue +
                ", cgiInfo=" + cgiInfo +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
