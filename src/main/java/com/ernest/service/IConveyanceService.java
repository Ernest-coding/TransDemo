package com.ernest.service;

import com.ernest.pojo.entity.Conveyance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ernest.pojo.entity.SignalTrans;
import com.ernest.pojo.vo.ConveyanceListPage;
import com.ernest.pojo.vo.SignalTransPage;

import java.util.List;

/**
 * <p>
 * 运输工具表	 服务类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
public interface IConveyanceService {

    List<ConveyanceListPage> allCoy();

    ConveyanceListPage oneCoyBaseInfo(Integer id);

    List<SignalTransPage> oneCoySignalTrans(Integer id);

    void addCoy(Conveyance entity);

    void setInfo(Integer id, Integer op, String info);
}
