package com.ernest.pojo.vo;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Ernest
 * 这个是用户管理页面的数据清单
 */

@Repository
@Data
public class UserInfoPage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer manId;

    /**
     * 账号
     */
    private String manAct;

    /**
     * 身份  1-管理员  2-操作员
     */
    private Integer manType;

    /**
     * 状态  1-生效  2-注销
     */
    private Integer manStatus;

    /**
     * 姓名
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
}
