package com.ernest.service;

import com.ernest.pojo.bo.OpStaEnum;
import com.ernest.pojo.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ernest.pojo.vo.UserInfoPage;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 客户表	可以由工作人员导入 服务类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
public interface IUserInfoService {

    List<UserInfoPage> getAllMan();

    List<UserInfoPage> getAllCus();

    OpStaEnum addMan(String act, String pwd, Integer type, String name, String phone);

    OpStaEnum addCus(String name, String phone);

    void setInfo(Integer type, Integer id, Integer op, String info);
}
