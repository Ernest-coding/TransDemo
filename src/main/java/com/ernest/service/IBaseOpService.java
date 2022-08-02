package com.ernest.service;

import com.ernest.pojo.bo.OpStaEnum;
import com.ernest.pojo.entity.Manager;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户登录及其他信息相关 服务类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
public interface IBaseOpService {

    OpStaEnum login(HttpSession session, String act, String pwd, Integer type);

    OpStaEnum forgot(String act, String pwd, String phone, Integer type);

    /**
     * 执行密码加密
     *
     * @param password 原始密码
     * @param salt     盐值
     * @return 加密后的密文
     */
    String getMd5Password(String password, String salt);
}
