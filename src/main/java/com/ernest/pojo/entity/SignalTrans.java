package com.ernest.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 运次表
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@TableName("signal_trans")
public class SignalTrans implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "sig_id", type = IdType.AUTO)
    private Integer sigId;

    /**
     * 运输工具 id，逻辑映射到 conveyance 表
     */
    private Integer sigCoyId;

    /**
     * 运单 id，逻辑映射到 way_bill 表
     */
    private Integer sigWbId;

    /**
     * 运次类型  1-国内  2-国际
     */
    private Integer sigType;

    /**
     * 运输线，如：北京——天津
     */
    private String sigTransLine;

    /**
     * 运次状态  1-运输中  2-已完成
     */
    private Integer sigStatus;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    public Integer getSigId() {
        return sigId;
    }

    public void setSigId(Integer sigId) {
        this.sigId = sigId;
    }

    public Integer getSigCoyId() {
        return sigCoyId;
    }

    public void setSigCoyId(Integer sigCoyId) {
        this.sigCoyId = sigCoyId;
    }

    public Integer getSigWbId() {
        return sigWbId;
    }

    public void setSigWbId(Integer sigWbId) {
        this.sigWbId = sigWbId;
    }

    public Integer getSigType() {
        return sigType;
    }

    public void setSigType(Integer sigType) {
        this.sigType = sigType;
    }

    public String getSigTransLine() {
        return sigTransLine;
    }

    public void setSigTransLine(String sigTransLine) {
        this.sigTransLine = sigTransLine;
    }

    public Integer getSigStatus() {
        return sigStatus;
    }

    public void setSigStatus(Integer sigStatus) {
        this.sigStatus = sigStatus;
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
        return "SignalTrans{" +
                "sigId=" + sigId +
                ", sigCoyId=" + sigCoyId +
                ", sigWbId=" + sigWbId +
                ", sigType=" + sigType +
                ", sigTransLine=" + sigTransLine +
                ", sigStatus=" + sigStatus +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
