package com.ernest.pojo.vo;

import com.ernest.pojo.entity.Conveyance;
import com.ernest.pojo.entity.SignalTrans;
import lombok.Data;
import org.springframework.stereotype.Repository;


@Repository
@Data
public class SignalTransPage extends SignalTrans {
    /**
     * 运次状态主题
     */
    private String sigStatusTheme;

    /**
     * 运单号
     */
    private String tranNum;

    /**
     * 运单类型  国内运单  国际运单
     */
    private String tranType;

    /**
     * 运单发出地
     */
    private String sendAdd;

    /**
     * 运单目的地
     */
    private String receAdd;

    /**
     * 运输工具信息
     */
    private Conveyance conveyance;
}
