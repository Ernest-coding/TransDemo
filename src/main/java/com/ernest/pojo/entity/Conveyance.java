package com.ernest.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 运输工具表
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Repository
public class Conveyance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "coy_id", type = IdType.AUTO)
    private Integer coyId;

    /**
     * 负责人姓名
     */
    private String coyPrincipalName;

    /**
     * 负责人手机号
     */
    private String coyPrincipalPhone;

    /**
     * 运输工具名称
     */
    private String coyName;

    /**
     * 运输工具类型   1-空运  2-海运   3-陆运
     */
    private Integer coyType;

    /**
     * 运输工具状态  1-空闲中  2-运输中  3-维修中  4-已报废  5-已删除
     */
    private Integer coyStatus;

    /**
     * 运输工具证件信息（如：车牌号）
     */
    private String coyLicense;

    /**
     * 运输工具所属地
     */
    private String coySourceAddress;

    /**
     * 运输工具说明信息
     */
    private String coyInfo;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 修改时间
     */
    private LocalDateTime updTime;

    public Integer getCoyId() {
        return coyId;
    }

    public void setCoyId(Integer coyId) {
        this.coyId = coyId;
    }

    public String getCoyPrincipalName() {
        return coyPrincipalName;
    }

    public void setCoyPrincipalName(String coyPrincipalName) {
        this.coyPrincipalName = coyPrincipalName;
    }

    public String getCoyPrincipalPhone() {
        return coyPrincipalPhone;
    }

    public void setCoyPrincipalPhone(String coyPrincipalPhone) {
        this.coyPrincipalPhone = coyPrincipalPhone;
    }

    public String getCoyName() {
        return coyName;
    }

    public void setCoyName(String coyName) {
        this.coyName = coyName;
    }

    public Integer getCoyType() {
        return coyType;
    }

    public void setCoyType(Integer coyType) {
        this.coyType = coyType;
    }

    public Integer getCoyStatus() {
        return coyStatus;
    }

    public void setCoyStatus(Integer coyStatus) {
        this.coyStatus = coyStatus;
    }

    public String getCoyLicense() {
        return coyLicense;
    }

    public void setCoyLicense(String coyLicense) {
        this.coyLicense = coyLicense;
    }

    public String getCoySourceAddress() {
        return coySourceAddress;
    }

    public void setCoySourceAddress(String coySourceAddress) {
        this.coySourceAddress = coySourceAddress;
    }

    public String getCoyInfo() {
        return coyInfo;
    }

    public void setCoyInfo(String coyInfo) {
        this.coyInfo = coyInfo;
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
        return "Conveyance{" +
                "coyId=" + coyId +
                ", coyPrincipalName=" + coyPrincipalName +
                ", coyPrincipalPhone=" + coyPrincipalPhone +
                ", coyType=" + coyType +
                ", coyStatus=" + coyStatus +
                ", coyLicense=" + coyLicense +
                ", coySourceAddress=" + coySourceAddress +
                ", coyInfo=" + coyInfo +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
