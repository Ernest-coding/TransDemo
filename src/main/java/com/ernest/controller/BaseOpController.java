package com.ernest.controller;

import com.ernest.pojo.bo.OpStaEnum;
import com.ernest.service.IBaseOpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户登录及其他信息相关 前端控制器
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Controller
@RequestMapping("/")
public class BaseOpController {

    @Autowired
    private IBaseOpService baseOpService;

    @GetMapping("manage")
    public String toLogin() {
        return "back/login";
    }

    @GetMapping("forgot")
    public String toForgot() {
        return "back/forgot";
    }


    /**
     * 登录
     *
     * @param session session 信息
     * @param map     页面渲染信息
     * @param act     账号
     * @param pwd     密码
     * @param type    账号类型  1-后台人员  2-客户
     * @return 登录是否成功，成功直接跳转后台主页，失败则返回本页面，同时携带失败信息
     */
    @PostMapping("login.do")
    public String login(HttpSession session, ModelMap map,
                        @RequestParam(name = "act") String act,
                        @RequestParam(name = "pwd") String pwd,
                        @RequestParam(name = "type") Integer type) {
        if (type == 2) {
            map.put("msg", "您没有权限！");
            return "/back/exPage";
        }
        OpStaEnum result = baseOpService.login(session, act, pwd, type);
        if (result == OpStaEnum.SUCCESS) {
            return "redirect:/backIndex";
        } else {
            map.put("act", act);
            map.put("pwd", pwd);
            map.put("type", type);
            map.put("welInfo", "display:none");
            map.put("sucInfo", "display:none");
            map.put("errInfo", "display:inherit");
            map.put("msg", result.getStatus());
            return "back/login";
        }
    }

    /**
     * 后台主页请求
     *
     * @param map
     * @return
     */
    @GetMapping("backIndex")
    public String toBackIndex(ModelMap map) {

        return "redirect:/wb/all";
//        return "back/back_index";
    }

    /**
     * 修改密码
     *
     * @param map   页面渲染信息
     * @param act   账号
     * @param pwd   新密码
     * @param phone 手机号
     * @param type  账号类型 1-后台人员  2-客户
     * @return 修改是否成功，成功直接跳转登录页面，失败则返回本页面，同时携带失败信息
     */
    @PostMapping("forgot.do")
    public String forgot(ModelMap map,
                         @RequestParam(name = "act") String act,
                         @RequestParam(name = "newPwd") String pwd,
                         @RequestParam(name = "phone") String phone,
                         @RequestParam(name = "type") Integer type) {
        OpStaEnum result = baseOpService.forgot(act, pwd, phone, type);
        if (result == OpStaEnum.SUCCESS) {
            map.put("msg", "密码修改成功");
            return "back/login";
        } else {
            map.put("msg", result.getStatus());
            return "back/forgot";
        }
    }

}
