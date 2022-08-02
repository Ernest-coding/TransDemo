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
 * 报关信息表
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Repository
@TableName("customs_declaration")
public class CustomsDeclaration implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ctd_id", type = IdType.AUTO)
    private Integer ctdId;

    /**
     * 运单 id，逻辑映射到 way_bill 表
     */
    private Integer ctdWbId;

    /**
     * 货品税务信息字典表 id，逻辑映射到 cargo_tax_dictionary
     */
    private Integer ctdCgtxId;

    /**
     * 报关地
     */
    private String ctdAddress;

    /**
     * 总缴税额，理论上等于货物数量 x 税务字典表里的单价
     */
    private BigDecimal ctdPrice;

    /**
     * 报关开始时间
     */
    private LocalDateTime ctdStartTime;

    /**
     * 报关结束时间
     */
    private LocalDateTime ctdFinishTime;

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

    public Integer getCtdWbId() {
        return ctdWbId;
    }

    public void setCtdWbId(Integer ctdWbId) {
        this.ctdWbId = ctdWbId;
    }

    public Integer getCtdCgtxId() {
        return ctdCgtxId;
    }

    public void setCtdCgtxId(Integer ctdCgtxId) {
        this.ctdCgtxId = ctdCgtxId;
    }

    public String getCtdAddress() {
        return ctdAddress;
    }

    public void setCtdAddress(String ctdAddress) {
        this.ctdAddress = ctdAddress;
    }

    public BigDecimal getCtdPrice() {
        return ctdPrice;
    }

    public void setCtdPrice(BigDecimal ctdPrice) {
        this.ctdPrice = ctdPrice;
    }

    public LocalDateTime getCtdStartTime() {
        return ctdStartTime;
    }

    public void setCtdStartTime(LocalDateTime ctdStartTime) {
        this.ctdStartTime = ctdStartTime;
    }

    public LocalDateTime getCtdFinishTime() {
        return ctdFinishTime;
    }

    public void setCtdFinishTime(LocalDateTime ctdFinishTime) {
        this.ctdFinishTime = ctdFinishTime;
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
        return "CustomsDeclaration{" +
                "ctdId=" + ctdId +
                ", ctdWbId=" + ctdWbId +
                ", ctdCgtxId=" + ctdCgtxId +
                ", ctdAddress=" + ctdAddress +
                ", ctdPrice=" + ctdPrice +
                ", ctdStartTime=" + ctdStartTime +
                ", ctdFinishTime=" + ctdFinishTime +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
