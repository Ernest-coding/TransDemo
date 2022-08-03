package com.ernest.pojo.vo;

import com.ernest.pojo.entity.Conveyance;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class ConveyanceListPage extends Conveyance {

    /**
     * 运输工具类型   1-空运  2-海运   3-陆运
     */
    private String coyTypeStr;

    /**
     * 运输工具类型主题
     */
    private String coyTypeStrTheme;

    /**
     * 运输工具状态  1-空闲中  2-运输中  3-维修中  4-已报废  5-已删除
     */
    private String coyStatusStr;

    /**
     * 运输工具状态主题
     */
    private String coyStatusStrTheme;
}
