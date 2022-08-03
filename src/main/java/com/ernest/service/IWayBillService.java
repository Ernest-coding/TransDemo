package com.ernest.service;

import com.ernest.pojo.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ernest.pojo.vo.WayBillDetailPage;

import java.util.List;

/**
 * <p>
 * 运单表 服务类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
public interface IWayBillService {

    List<WayBill> all();

    WayBillDetailPage detail(Integer id);

    int addCargo(CargoInfo cargoInfo);

    int addWayBill(WayBill wayBill, String wbSendTime, String wbReceTime);

    void addTran(SignalTrans signalTrans);

    void addCtd(CustomsDeclaration ctd);

    void addArr(ArrivalPortInfo arr);

    void delWb(Integer id);

    void delOth(Integer op, Integer id);
}
