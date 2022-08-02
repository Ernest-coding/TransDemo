package com.ernest.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 货品税额信息字典表
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@TableName("cargo_tax_dictionary")
public class CargoTaxDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ctd_id", type = IdType.AUTO)
    private Integer ctdId;

    /**
     * 货品名称
     */
    private String ctdName;

    /**
     * 货品种类
     */
    private String ctdType;

    /**
     * 货品税额单价
     */
    private BigDecimal ctdTax;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    public Integer getCtdId() {
        return ctdId;
    }

    public void setCtdId(Integer ctdId) {
        this.ctdId = ctdId;
    }

    public String getCtdName() {
        return ctdName;
    }

    public void setCtdName(String ctdName) {
        this.ctdName = ctdName;
    }

    public String getCtdType() {
        return ctdType;
    }

    public void setCtdType(String ctdType) {
        this.ctdType = ctdType;
    }

    public BigDecimal getCtdTax() {
        return ctdTax;
    }

    public void setCtdTax(BigDecimal ctdTax) {
        this.ctdTax = ctdTax;
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
        return "CargoTaxDictionary{" +
                "ctdId=" + ctdId +
                ", ctdName=" + ctdName +
                ", ctdType=" + ctdType +
                ", ctdTax=" + ctdTax +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
