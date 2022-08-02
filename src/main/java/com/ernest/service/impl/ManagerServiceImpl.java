package com.ernest.service.impl;

import com.ernest.pojo.entity.Manager;
import com.ernest.mapper.ManagerMapper;
import com.ernest.service.IManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {

}
