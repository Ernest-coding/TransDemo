package com.ernest.service.impl;

import com.ernest.pojo.entity.Conveyance;
import com.ernest.mapper.ConveyanceMapper;
import com.ernest.service.IConveyanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 运输工具表	 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
public class ConveyanceServiceImpl implements IConveyanceService {

    @Autowired
    private ConveyanceMapper conveyanceMapper;

    @Override
    public List<Conveyance> allCoy() {
        return conveyanceMapper.selectList(null);
    }
}
