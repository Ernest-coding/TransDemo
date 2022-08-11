package com.ernest.pojo.vo;

import com.ernest.pojo.entity.Customer;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 这个是运单列表页的单个运单数据条目
 *
 * @author Ernest
 */

@Repository
@Data
public class WayBillPage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer wbId;

    /**
     * 运单号
     */
    private String wbNum;

    /**
     * 委托公司
     */
    private String wbCompany;

    /**
     * 类型 1-国内运单  2-国际运单
     */
    private Integer wbType;

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
     * 状态   1-已下单  2-发出，这个过程需要核对  3-已签收
     */
    private Integer wbStatus;

    /**
     * 运单说明信息
     */
    private String wbInfo;

    /**
     * 运单添加人员
     */
    private String wbOpName;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updTime;
}
