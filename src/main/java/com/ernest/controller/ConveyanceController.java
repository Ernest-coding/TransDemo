package com.ernest.controller;

import com.ernest.pojo.entity.Conveyance;
import com.ernest.pojo.entity.SignalTrans;
import com.ernest.pojo.vo.ConveyanceListPage;
import com.ernest.pojo.vo.SignalTransPage;
import com.ernest.service.IConveyanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 运输工具相关请求处理	 前端控制器
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Controller
@RequestMapping("/coy")
public class ConveyanceController {

    @Autowired
    IConveyanceService conveyanceService;

    /**
     * 运输工具列表
     *
     * @param session
     * @param map
     * @return
     */
    @GetMapping("/allCoy")
    public String allCoy(HttpSession session, ModelMap map) {
        List<ConveyanceListPage> result = conveyanceService.allCoy();
        map.put("id", session.getAttribute("id"));
        map.put("act", session.getAttribute("act"));
        map.put("name", session.getAttribute("name"));
        map.put("type", session.getAttribute("type"));
        map.put("limit", session.getAttribute("limit"));
        map.put("coys", result);
        return "allCoyPage";
    }

    /**
     * 单个运输工具详情
     *
     * @param map
     * @param id  运输工具 id
     * @return
     */
    @GetMapping("/oneCoy")
    public String oneCoy(ModelMap map, @RequestParam(name = "id") Integer id) {
        ConveyanceListPage baseInfo = conveyanceService.oneCoyBaseInfo(id);
        List<SignalTransPage> transList = conveyanceService.oneCoySignalTrans(id);
        map.put("baseInfo", baseInfo);
        map.put("transList", transList);
        return "oneCoyDetail";
    }

    /**
     * 添加运输工具
     *
     * @param map
     * @param userName  负责人姓名
     * @param userPhone 负责人手机
     * @param type      类型  1-空运  2-海运  3-陆运
     * @param license   证件信息，如：车牌号
     * @param address   所属地
     * @param other     说明信息
     * @return
     */
    @PostMapping("/add")
    public String add(ModelMap map,
                      @RequestParam(name = "userName") String userName,
                      @RequestParam(name = "userPhone") String userPhone,
                      @RequestParam(name = "name") String name,
                      @RequestParam(name = "type") Integer type,
                      @RequestParam(name = "license") String license,
                      @RequestParam(name = "address") String address,
                      @RequestParam(name = "other") String other) {
        Conveyance entity = new Conveyance();
        entity.setCoyPrincipalName(userName);
        entity.setCoyPrincipalPhone(userPhone);
        entity.setCoyName(name);
        entity.setCoyType(type);
        entity.setCoyStatus(1);
        entity.setCoyLicense(license);
        entity.setCoySourceAddress(address);
        entity.setCoyInfo(other);
        conveyanceService.addCoy(entity);
        return "redirect:/coy/allCoy";
    }

    /**
     * 修改信息
     *
     * @param map
     * @param id   id
     * @param op   操作码 0-删除
     * @param info 操作码对应的信息
     * @return
     */
    @GetMapping("/setInfo")
    public String setInfo(ModelMap map,
                          @RequestParam(name = "id") Integer id,
                          @RequestParam(name = "op") Integer op,
                          @RequestParam(name = "info") String info) {
        conveyanceService.setInfo(id, op, info);
        return "redirect:/coy/oneCoy";
    }

}
