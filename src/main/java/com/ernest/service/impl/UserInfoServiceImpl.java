package com.ernest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ernest.mapper.ManagerMapper;
import com.ernest.pojo.bo.OpStaEnum;
import com.ernest.pojo.entity.Customer;
import com.ernest.mapper.CustomerMapper;
import com.ernest.pojo.entity.Manager;
import com.ernest.pojo.vo.UserInfoPage;
import com.ernest.service.IBaseOpService;
import com.ernest.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 客户表	可以由工作人员导入 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private IBaseOpService baseOpService;

    @Override
    public List<UserInfoPage> getAllMan() {
        List<Manager> list = managerMapper.selectList(
                new QueryWrapper<Manager>().ne("man_status", 2));
        if (list == null) {
            return null;
        }
        List<UserInfoPage> result = new ArrayList<>();
        for (Manager one : list) {
            UserInfoPage item = new UserInfoPage();
            item.setManId(one.getManId());
            item.setManAct(one.getManAct());
            item.setManName(one.getManName());
            item.setManStatus(one.getManStatus());
            item.setManPhone(one.getManPhone());
            item.setCusOrder(-1);
            item.setAddTime(one.getAddTime());
            item.setUpdTime(one.getUpdTime());
            result.add(item);
        }
        return result;
    }

    @Override
    public List<UserInfoPage> getAllCus() {
        List<Customer> list = customerMapper.selectList(null);
        if (list == null) {
            return null;
        }
        List<UserInfoPage> result = new ArrayList<>();
        for (Customer one : list) {
            UserInfoPage item = new UserInfoPage();
            item.setManId(one.getCusId());
            item.setManAct(one.getCusAct());
            item.setManName(one.getCusName());
            item.setManStatus(-1);
            item.setManPhone(one.getCusPhone());
            item.setCusOrder(one.getCusOrder());
            item.setAddTime(one.getAddTime());
            item.setUpdTime(one.getUpdTime());
            result.add(item);
        }
        return result;
    }

    @Override
    public OpStaEnum addMan(String act, String pwd, Integer type, String name, String phone) {
        Manager check = managerMapper.selectOne(new QueryWrapper<Manager>()
                .eq("man_name", name)
                .eq("man_act", act));
        if (check != null) {
            return OpStaEnum.HASEXIST;
        }
        Manager user = new Manager();
        user.setManAct(act);
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setManPwd(baseOpService.getMd5Password(pwd, salt));
        user.setManSalt(salt);
        user.setManType(type);
        user.setManStatus(1);
        user.setManName(name);
        user.setManPhone(phone);
        int insert = managerMapper.insert(user);
        return (insert == 1) ? OpStaEnum.SUCCESS : OpStaEnum.NOTKNOW;
    }

    @Override
    public OpStaEnum addCus(String name, String phone) {
        Customer check = customerMapper.selectOne(new QueryWrapper<Customer>()
                .eq("cus_name", name)
                .eq("cus_phone", phone));
        if (check != null) {
            return OpStaEnum.HASEXIST;
        }
        Customer user = new Customer();
        user.setCusAct(phone);
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setCusPwd(baseOpService.getMd5Password(phone, salt));
        user.setCusSalt(salt);
        user.setCusName(name);
        user.setCusPhone(phone);
        int insert = customerMapper.insert(user);
        return (insert == 1) ? OpStaEnum.SUCCESS : OpStaEnum.NOTKNOW;
    }

    @Override
    public void setInfo(Integer type, Integer id, Integer op, String info) {
        if (type == 1) {
            Manager user = managerMapper.selectOne(new QueryWrapper<Manager>().eq("man_id", id));
            switch (op) {
                case 0:
                    user.setManStatus(2);
                    managerMapper.deleteById(user);
                    break;
                case 1:
                    user.setManPhone(info);
                    managerMapper.updateById(user);
                    break;
                case 2:
                    user.setManName(info);
                    managerMapper.updateById(user);
                    break;
                default:
                    break;
            }
        } else {
            Customer user = customerMapper.selectOne(new QueryWrapper<Customer>().eq("cus_id", id));
            switch (op) {
                case 0:
                    customerMapper.deleteById(user);
                    break;
                case 1:
                    user.setCusPhone(info);
                    customerMapper.updateById(user);
                    break;
                case 2:
                    user.setCusName(info);
                    customerMapper.updateById(user);
                    break;
                case 3:
                    user.setCusOrder(Integer.valueOf(info));
                    customerMapper.updateById(user);
                    break;
                default:
                    break;
            }
        }
    }
}
