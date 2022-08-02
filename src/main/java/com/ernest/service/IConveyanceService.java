package com.ernest.service;

import com.ernest.pojo.entity.Conveyance;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<Conveyance> allCoy();
}
