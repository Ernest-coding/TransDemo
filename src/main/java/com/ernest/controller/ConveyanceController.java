package com.ernest.controller;

import com.ernest.pojo.entity.Conveyance;
import com.ernest.service.IConveyanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

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

    @GetMapping("/allCoy")
    public String allCoy(HttpSession session, ModelMap map) {
        List<Conveyance> result = conveyanceService.allCoy();
        map.put("id", session.getAttribute("id"));
        map.put("act", session.getAttribute("act"));
        map.put("name", session.getAttribute("name"));
        map.put("type", session.getAttribute("type"));
        map.put("limit", session.getAttribute("limit"));
        map.put("coys", result);
        return "allCoyPage";
    }

    /*@GetMapping("/oneCoy")
    public String*/

}
