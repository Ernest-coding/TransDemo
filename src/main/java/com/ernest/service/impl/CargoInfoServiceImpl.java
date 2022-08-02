package com.ernest.service.impl;

import com.ernest.pojo.entity.CargoInfo;
import com.ernest.mapper.CargoInfoMapper;
import com.ernest.service.ICargoInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 货物信息表 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
public class CargoInfoServiceImpl extends ServiceImpl<CargoInfoMapper, CargoInfo> implements ICargoInfoService {

}
