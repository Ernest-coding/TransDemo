package com.ernest.controller;

import com.ernest.pojo.bo.OpStaEnum;
import com.ernest.pojo.vo.UserInfoPage;
import com.ernest.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户列表信息及用户信息设置页面相关请求  前端控制器
 *
 * @author Ernest
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 管理员列表页
     *
     * @param map     页面渲染信息
     * @param session session 信息
     * @return 数据列表
     */
    @GetMapping("/allMan")
    public String allMan(ModelMap map, HttpSession session) {
        List<UserInfoPage> result = userInfoService.getAllMan();
        map.put("id", session.getAttribute("id"));
        map.put("act", session.getAttribute("act"));
        map.put("name", session.getAttribute("name"));
        map.put("type", session.getAttribute("type"));
        map.put("limit", session.getAttribute("limit"));
        map.put("userList", result);
        return "allManPage";
    }

    /**
     * 客户列表页
     *
     * @param map     页面渲染信息
     * @param session session 信息
     * @return 数据列表
     */
    @GetMapping("/allCus")
    public String allCus(ModelMap map, HttpSession session) {
        List<UserInfoPage> result = userInfoService.getAllCus();
        map.put("id", session.getAttribute("id"));
        map.put("act", session.getAttribute("act"));
        map.put("name", session.getAttribute("name"));
        map.put("type", session.getAttribute("type"));
        map.put("limit", session.getAttribute("limit"));
        map.put("userList", result);
        return "allCusPage";
    }

    /**
     * 添加后台人员
     *
     * @param map
     * @param session
     * @param act     账号
     * @param pwd     密码
     * @param type    身份  1-管理员 2-操作员
     * @param name    姓名
     * @param phone   手机号
     * @return 添加成功直接返回列表页，添加失败就跳转到失败页面
     */
    @PostMapping("/addMan")
    public String addMan(ModelMap map, HttpSession session,
                         @RequestParam(name = "act") String act,
                         @RequestParam(name = "pwd") String pwd,
                         @RequestParam(name = "type") Integer type,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "phone") String phone) {
        if (!("1".equals(session.getAttribute("type")) &&
                "1".equals(session.getAttribute("limit")))) {
            map.put("msg", "您没有权限！");
            return "exPage";
        }
        OpStaEnum result = userInfoService.addMan(act, pwd, type, name, phone);
        if (result == OpStaEnum.SUCCESS) {
            return "redirect:/user/allMan";
        } else {
            map.put("msg", result.getStatus());
            return "exPage";
        }
    }

    /**
     * 添加客户，客户只需要姓名和手机号，账号、密码等均自动生成
     *
     * @param map
     * @param session
     * @param name    姓名
     * @param phone   手机号
     * @return 添加成功直接返回列表页，添加失败就跳转到失败页面
     */
    @PostMapping("/addCus")
    public String addCus(ModelMap map, HttpSession session,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "phone") String phone) {
        if (!("1".equals(session.getAttribute("type")) &&
                "1".equals(session.getAttribute("limit")))) {
            map.put("msg", "您没有权限！");
            return "exPage";
        }
        OpStaEnum result = userInfoService.addCus(name, phone);
        if (result == OpStaEnum.SUCCESS) {
            return "redirect:/user/allCus";
        } else {
            map.put("msg", result.getStatus());
            return "exPage";
        }
    }

    /**
     * 设置用户信息，包括后台人员和客户
     *
     * @param session
     * @param type    类型  1-后台人员  2-客户
     * @param id      用户 id
     * @param op      操作码  0-删除用户  1-修改手机号  2-修改姓名  3-修改运单数
     * @param info    更新信息，对应 op ，相关的详细信息
     * @return 还是跳转到列表页，默认成功
     */
    @PostMapping("/setInfo")
    public String setInfo(ModelMap map, HttpSession session,
                          @RequestParam(name = "type") Integer type,
                          @RequestParam(name = "id") Integer id,
                          @RequestParam(name = "op") Integer op,
                          @RequestParam(name = "info") String info) {
        if (!"1".equals(session.getAttribute("type"))) {
            map.put("msg", "您没有权限！");
            return "exPage";
        }
        userInfoService.setInfo(type, id, op, info);
        return (type == 1) ? "redirect:/user/allMan" : "redirect:/user/allCus";
    }
}
