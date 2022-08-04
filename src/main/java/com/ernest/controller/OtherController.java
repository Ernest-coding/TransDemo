package com.ernest.controller;


import com.ernest.pojo.vo.WayBillDetailPage;
import com.ernest.service.IWayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oth")
public class OtherController {

    @Autowired
    private IWayBillService wbService;

    @PostMapping("/searchWaybill")
    public String userSearchWayBill(ModelMap map,
                                    @RequestParam(name = "phone") String phone,
                                    @RequestParam(name = "wbNum") String wbNum) {
        System.out.println("test1");
        if ("发件人手机号".equals(phone)) {
            map.put("msg", "请输入手机号和运单号");
            return "back/exPage";
        }
        WayBillDetailPage detail = wbService.userSearchWayBill(phone, wbNum);
        if (detail == null) {
            map.put("msg", "运单不存在");
            return "back/exPage";
        } else {
            map.put("detail", detail);
            return "back/userWayBill";
        }
    }
}
