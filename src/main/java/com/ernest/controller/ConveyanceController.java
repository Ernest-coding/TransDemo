package com.ernest.controller;

import com.ernest.pojo.entity.Conveyance;
import com.ernest.pojo.entity.SignalTrans;
import com.ernest.pojo.vo.ConveyanceListPage;
import com.ernest.pojo.vo.SignalTransPage;
import com.ernest.service.IConveyanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

}
