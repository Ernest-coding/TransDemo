package com.ernest.mapper;

import com.ernest.pojo.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 客户表	可以由工作人员导入 Mapper 接口
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {

}
