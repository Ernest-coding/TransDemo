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
 * 到港信息表
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Repository
@TableName("arrival_port_info")
public class ArrivalPortInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "arp_id", type = IdType.AUTO)
    private Integer arpId;

    /**
     * 运单 id，逻辑映射到 way_bill 表
     */
    private Integer arpWbId;

    /**
     * 空港名
     */
    private String arpName;

    /**
     * 到港时间
     */
    private LocalDateTime arpInTime;

    /**
     * 离港时间
     */
    private LocalDateTime arpOutTime;

    /**
     * 停港日单价
     */
    private BigDecimal arpPerPrice;

    /**
     * 停港总费用
     */
    private BigDecimal arpMulPrice;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    public Integer getArpId() {
        return arpId;
    }

    public void setArpId(Integer arpId) {
        this.arpId = arpId;
    }

    public Integer getArpWbId() {
        return arpWbId;
    }

    public void setArpWbId(Integer arpWbId) {
        this.arpWbId = arpWbId;
    }

    public String getArpName() {
        return arpName;
    }

    public void setArpName(String arpName) {
        this.arpName = arpName;
    }

    public LocalDateTime getArpInTime() {
        return arpInTime;
    }

    public void setArpInTime(LocalDateTime arpInTime) {
        this.arpInTime = arpInTime;
    }

    public LocalDateTime getArpOutTime() {
        return arpOutTime;
    }

    public void setArpOutTime(LocalDateTime arpOutTime) {
        this.arpOutTime = arpOutTime;
    }

    public BigDecimal getArpPerPrice() {
        return arpPerPrice;
    }

    public void setArpPerPrice(BigDecimal arpPerPrice) {
        this.arpPerPrice = arpPerPrice;
    }

    public BigDecimal getArpMulPrice() {
        return arpMulPrice;
    }

    public void setArpMulPrice(BigDecimal arpMulPrice) {
        this.arpMulPrice = arpMulPrice;
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
        return "ArrivalPortInfo{" +
                "arpId=" + arpId +
                ", arpWbId=" + arpWbId +
                ", arpName=" + arpName +
                ", arpInTime=" + arpInTime +
                ", arpOutTime=" + arpOutTime +
                ", arpPerPrice=" + arpPerPrice +
                ", arpMulPrice=" + arpMulPrice +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
