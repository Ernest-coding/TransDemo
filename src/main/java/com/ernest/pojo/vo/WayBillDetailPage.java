package com.ernest.pojo.vo;

import com.ernest.pojo.entity.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Data
public class WayBillDetailPage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 运单本身信息
     */
    private WayBill wayBill;

    /**
     * 货物信息
     */
    private CargoInfo cargoInfo;

    /**
     * 运次列表
     */
    private List<SignalTransPage> signalTransList;

    /**
     * 到港信息列表
     */
    private List<ArrivalPortInfo> arrivalPortInfo;

    /**
     * 报关信息
     */
    private List<CustomsDeclaration> customsDeclaration;

    /**
     * 运输工具列表，添加运次用
     */
    private List<Conveyance> conveyanceList;

    /**
     * 货品税额信息表，添加报关单用
     */
    private List<CargoTaxDictionary> cargoTaxDictionaryList;
}
