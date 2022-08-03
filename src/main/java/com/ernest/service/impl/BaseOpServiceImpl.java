package com.ernest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ernest.mapper.CustomerMapper;
import com.ernest.pojo.bo.OpStaEnum;
import com.ernest.pojo.entity.Customer;
import com.ernest.pojo.entity.Manager;
import com.ernest.mapper.ManagerMapper;
import com.ernest.service.IBaseOpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ernest.util.DebugSoutUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
@Slf4j
public class BaseOpServiceImpl implements IBaseOpService {

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public OpStaEnum login(HttpSession session, String act, String pwd, Integer type) {
        if (type == 1) {
            // 后台人员
            Manager user = managerMapper.selectOne(new QueryWrapper<Manager>().eq("man_act", act));
            if (user == null) {
                return OpStaEnum.NOTEXIST;
            }
            String checkPwd = getMd5Password(pwd, user.getManSalt());
            if (checkPwd.equals(user.getManPwd())) {
                // 密码正确
                writeSession(session, user.getManId(), user.getManAct(), user.getManName(), 1, user.getManType());
                return OpStaEnum.SUCCESS;
            } else {
                return OpStaEnum.WRONGPWD;
            }
        } else {
            // 客户
            Customer user = customerMapper.selectOne(new QueryWrapper<Customer>().eq("cus_act", act));
            if (user == null) {
                return OpStaEnum.NOTEXIST;
            }
            String checkPwd = getMd5Password(pwd, user.getCusSalt());
            if (checkPwd.equals(user.getCusPwd())) {
                // 密码正确
                writeSession(session, user.getCusId(), user.getCusAct(), user.getCusName(), 2, 0);
                return OpStaEnum.SUCCESS;
            } else {
                return OpStaEnum.WRONGPWD;
            }
        }
    }

    @Override
    public OpStaEnum forgot(String act, String pwd, String phone, Integer type) {
        if (type == 1) {
            // 后台人员
            Manager user = managerMapper.selectOne(new QueryWrapper<Manager>().eq("man_act", act));
            if (user == null) {
                return OpStaEnum.NOTEXIST;
            }
            if (phone.equals(user.getManPhone())) {
                user.setManPwd(getMd5Password(pwd, user.getManSalt()));
                managerMapper.updateById(user);
                return OpStaEnum.SUCCESS;
            } else {
                return OpStaEnum.WRONGPHONE;
            }
        } else {
            // 客户
            Customer user = customerMapper.selectOne(new QueryWrapper<Customer>().eq("cus_act", act));
            if (user == null) {
                return OpStaEnum.NOTEXIST;
            }
            if (phone.equals(user.getCusPhone())) {
                user.setCusPwd(getMd5Password(pwd, user.getCusSalt()));
                customerMapper.updateById(user);
                return OpStaEnum.SUCCESS;
            } else {
                return OpStaEnum.WRONGPHONE;
            }
        }
    }

    private void writeSession(HttpSession session, Integer id, String act,
                              String name, int limit, Integer type) {
        session.setAttribute("id", id);
        session.setAttribute("act", act);
        session.setAttribute("name", name);
        session.setAttribute("type", type);
        session.setAttribute("limit", limit);
        session.setMaxInactiveInterval(1800);
        log.debug(DebugSoutUtils.soutDebugInfo() + "====>  用户登录");
        System.out.println(DebugSoutUtils.soutDebugInfo() + "====>  用户登录");
    }


    public String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        // 循环加密次数
        int nums = 3;
        for (int i = 0; i < nums; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
