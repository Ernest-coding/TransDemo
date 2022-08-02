package com.ernest.service.impl;

import com.ernest.pojo.entity.Customer;
import com.ernest.mapper.CustomerMapper;
import com.ernest.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表	可以由工作人员导入 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
