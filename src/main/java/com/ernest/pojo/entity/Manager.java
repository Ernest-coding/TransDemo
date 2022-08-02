package com.ernest.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "man_id", type = IdType.AUTO)
    private Integer manId;

    /**
     * 账号
     */
    private String manAct;

    /**
     * 密码
     */
    private String manPwd;

    /**
     * 盐值
     */
    private String manSalt;

    /**
     * 身份  1-管理员  2-操作员
     */
    private Integer manType;

    /**
     * 状态  1-生效  2-注销
     */
    private Integer manStatus;

    /**
     * 身份
     */
    private String manName;

    /**
     * 手机号
     */
    private String manPhone;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;

    public Integer getManId() {
        return manId;
    }

    public void setManId(Integer manId) {
        this.manId = manId;
    }

    public String getManAct() {
        return manAct;
    }

    public void setManAct(String manAct) {
        this.manAct = manAct;
    }

    public String getManPwd() {
        return manPwd;
    }

    public void setManPwd(String manPwd) {
        this.manPwd = manPwd;
    }

    public String getManSalt() {
        return manSalt;
    }

    public void setManSalt(String manSalt) {
        this.manSalt = manSalt;
    }

    public Integer getManType() {
        return manType;
    }

    public void setManType(Integer manType) {
        this.manType = manType;
    }

    public Integer getManStatus() {
        return manStatus;
    }

    public void setManStatus(Integer manStatus) {
        this.manStatus = manStatus;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public String getManPhone() {
        return manPhone;
    }

    public void setManPhone(String manPhone) {
        this.manPhone = manPhone;
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
        return "Manager{" +
                "manId=" + manId +
                ", manAct=" + manAct +
                ", manPwd=" + manPwd +
                ", manSalt=" + manSalt +
                ", manType=" + manType +
                ", manStatus=" + manStatus +
                ", manName=" + manName +
                ", manPhone=" + manPhone +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                "}";
    }
}
