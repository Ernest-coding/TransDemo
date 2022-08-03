package com.ernest.controller;

import com.ernest.pojo.entity.*;
import com.ernest.pojo.vo.WayBillDetailPage;
import com.ernest.service.IWayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.RelationNotFoundException;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 运单表 前端控制器
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Controller
@RequestMapping("/wb")
public class WayBillController {

    @Autowired
    private IWayBillService wbService;

    /**
     * 运单列表
     *
     * @param map
     * @return
     */
    @GetMapping("/all")
    public String all(ModelMap map) {
        List<WayBill> wbList = wbService.all();
        map.put("wbList", wbList);
        return "allWayBillPage";
    }

    /**
     * 运单详情页
     *
     * @param map
     * @param id  运单 id
     * @return
     */
    @GetMapping("/one")
    public String one(ModelMap map,
                      @RequestParam(name = "id") Integer id) {
        WayBillDetailPage detail = wbService.detail(id);
        map.put("detail", detail);
        return "wayBillDetailPage";
    }

    /*
     * 添加运单的逻辑：
     * 1. 打开新建运单页面，填写运单的所有基本信息和货物的基本信息
     * 2. 传到后端，后端先生成货物信息到数据库
     * 3. 然后生成运单信息到数据库
     * 4. 后续运单的添加运次、报关等信息都在运单详情界面操作
     * 5. 删除运单时删除该运单相关的所有数据
     */

    /**
     * 添加运单，各项参数对照实体表查看
     *
     * @param map
     * @param session
     * @param wbNum
     * @param wbType
     * @param wbSendName
     * @param wbSendPhone
     * @param wbSendAddress
     * @param wbSendTime
     * @param wbReceName
     * @param wbRecePhone
     * @param wbReceAddress
     * @param wbReceTime
     * @param wbPrice
     * @param wbStatus
     * @param wbInfo
     * @param cgiType
     * @param cgiName
     * @param cgiNumber
     * @param cgiValue
     * @param cgiInfo
     * @return
     */
    @PostMapping("/addWb")
    public String addWb(ModelMap map, HttpSession session,
                        @RequestParam(name = "wbNum") String wbNum,
                        @RequestParam(name = "wbType") Integer wbType,
                        @RequestParam(name = "wbSendName") String wbSendName,
                        @RequestParam(name = "wbSendPhone") String wbSendPhone,
                        @RequestParam(name = "wbSendAddress") String wbSendAddress,
                        @RequestParam(name = "wbSendTime") String wbSendTime,
                        @RequestParam(name = "wbReceName") String wbReceName,
                        @RequestParam(name = "wbRecePhone") String wbRecePhone,
                        @RequestParam(name = "wbReceAddress") String wbReceAddress,
                        @RequestParam(name = "wbReceTime") String wbReceTime,
                        @RequestParam(name = "wbPrice") String wbPrice,
                        @RequestParam(name = "wbStatus") Integer wbStatus,
                        @RequestParam(name = "wbInfo") String wbInfo,
                        @RequestParam(name = "cgiType") Integer cgiType,
                        @RequestParam(name = "cgiName") String cgiName,
                        @RequestParam(name = "cgiNumber") String cgiNumber,
                        @RequestParam(name = "cgiValue") String cgiValue,
                        @RequestParam(name = "cgiInfo") String cgiInfo) {
        CargoInfo cargoInfo = new CargoInfo();
        cargoInfo.setCgiType(cgiType);
        cargoInfo.setCgiName(cgiName);
        cargoInfo.setCgiNumber(cgiNumber);
        cargoInfo.setCgiValue(new BigDecimal(cgiValue));
        cargoInfo.setCgiInfo(cgiInfo);
        int index = wbService.addCargo(cargoInfo);

        WayBill wayBill = new WayBill();
        wayBill.setWbCgiId(index);
        wayBill.setWbNum(wbNum);
        wayBill.setWbType(wbType);
        wayBill.setWbSendName(wbSendName);
        wayBill.setWbSendPhone(wbSendPhone);
        wayBill.setWbSendAddress(wbSendAddress);
        wayBill.setWbReceName(wbReceName);
        wayBill.setWbRecePhone(wbRecePhone);
        wayBill.setWbReceAddress(wbReceAddress);
        wayBill.setWbPrice(new BigDecimal(wbPrice));
        wayBill.setWbStatus(wbStatus);
        wayBill.setWbInfo(wbInfo);
        wayBill.setWbOpId((Integer) session.getAttribute("id"));

        int wbId = wbService.addWayBill(wayBill, wbSendTime, wbReceTime);

        return "redirect:/wb/all";
    }

    /**
     * 添加运次
     *
     * @param attr
     * @param coyId     运输工具 id
     * @param wbId      运单 id
     * @param type      运次类型  1-国内 2-国际
     * @param transLine 运输线
     * @param status    运输状态 1-运输中 2-已完成
     * @return
     */
    @PostMapping("/addTran")
    public String addTran(RedirectAttributes attr,
                          @RequestParam(name = "coyId") Integer coyId,
                          @RequestParam(name = "wbId") Integer wbId,
                          @RequestParam(name = "type") Integer type,
                          @RequestParam(name = "transLine") String transLine,
                          @RequestParam(name = "status") Integer status) {
        SignalTrans signalTrans = new SignalTrans();
        signalTrans.setSigCoyId(coyId);
        signalTrans.setSigWbId(wbId);
        signalTrans.setSigType(type);
        signalTrans.setSigTransLine(transLine);
        signalTrans.setSigStatus(status);
        wbService.addTran(signalTrans);
        attr.addAttribute("id", wbId);
        return "redirect:/wb/one";
    }

    /**
     * 添加报关信息
     *
     * @param attr
     * @param wbId
     * @param cgtxId
     * @param address
     * @param price
     * @param startTime
     * @param finishTime
     * @return
     */
    @PostMapping("/addCtd")
    public String addCtd(RedirectAttributes attr,
                         @RequestParam(name = "wbId") Integer wbId,
                         @RequestParam(name = "cgtxId") Integer cgtxId,
                         @RequestParam(name = "address") String address,
                         @RequestParam(name = "price") String price,
                         @RequestParam(name = "startTime") String startTime,
                         @RequestParam(name = "finishTime") String finishTime) {
        CustomsDeclaration ctd = new CustomsDeclaration();
        ctd.setCtdWbId(wbId);
        ctd.setCtdCgtxId(cgtxId);
        ctd.setCtdAddress(address);
        ctd.setCtdPrice(new BigDecimal(price));
        ctd.setCtdStartTime(startTime);
        ctd.setCtdFinishTime(finishTime);
        wbService.addCtd(ctd);
        attr.addAttribute("id", wbId);
        return "redirect:/wb/one";
    }

    /**
     * 添加到港信息
     *
     * @param attr
     * @param wbId
     * @param name
     * @param inTime
     * @param outTime
     * @param perPrice
     * @param mulPrice
     * @return
     */
    @PostMapping("/addArr")
    public String addArr(RedirectAttributes attr,
                         @RequestParam(name = "wbId") Integer wbId,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "inTime") String inTime,
                         @RequestParam(name = "outTime") String outTime,
                         @RequestParam(name = "perPrice") String perPrice,
                         @RequestParam(name = "mulPrice") String mulPrice) {
        ArrivalPortInfo arr = new ArrivalPortInfo();
        arr.setArpWbId(wbId);
        arr.setArpName(name);
        arr.setArpInTime(inTime);
        arr.setArpOutTime(outTime);
        arr.setArpPerPrice(new BigDecimal(perPrice));
        arr.setArpMulPrice(new BigDecimal(mulPrice));
        wbService.addArr(arr);
        attr.addAttribute("id", wbId);
        return "redirect:/wb/one";
    }

    /**
     * 删除运单
     *
     * @param id 运单 id
     * @return
     */
    @GetMapping("/delWb")
    public String delWb(HttpSession session, ModelMap map,
                        @RequestParam(name = "id") Integer id) {
        if ("2".equals(session.getAttribute("limit"))) {
            map.put("msg", "您没有权限！");
            return "back/exPage";
        }
        wbService.delWb(id);
        return "redirect:/wb/all";
    }

    /**
     * 删除其他信息
     *
     * @param attr
     * @param op   操作码  1-运次  2-报关  3-到港
     * @param id   对应 op 的信息
     * @param wbId 运单 id
     * @return
     */
    @GetMapping("/delOth")
    public String delOth(RedirectAttributes attr, HttpSession session, ModelMap map,
                         @RequestParam(name = "op") Integer op,
                         @RequestParam(name = "id") Integer id,
                         @RequestParam(name = "wbId") Integer wbId) {
        if ("2".equals(session.getAttribute("limit"))) {
            map.put("msg", "您没有权限！");
            return "back/exPage";
        }
        wbService.delOth(op, id);
        attr.addAttribute("id", wbId);
        return "redirect:/wb/one";
    }

    /**
     * 修改运单基本信息
     *
     * @param attr
     * @param map
     * @param session
     * @param wbId          运单 id
     * @param wbReceName    收件人信息，下同
     * @param wbRecePhone
     * @param wbReceAddress
     * @param wbReceTime
     * @param wbPrice       运单价格
     * @param wbStatus      运单状态
     * @param wbInfo        运单说明信息
     * @return
     */
    @GetMapping("/setWbInfo")
    public String setWbInfo(RedirectAttributes attr, ModelMap map, HttpSession session,
                            @RequestParam(name = "wbId") Integer wbId,
                            @RequestParam(name = "wbReceName") String wbReceName,
                            @RequestParam(name = "wbRecePhone") String wbRecePhone,
                            @RequestParam(name = "wbReceAddress") String wbReceAddress,
                            @RequestParam(name = "wbReceTime") String wbReceTime,
                            @RequestParam(name = "wbPrice") String wbPrice,
                            @RequestParam(name = "wbStatus") Integer wbStatus,
                            @RequestParam(name = "wbInfo") String wbInfo) {
        if ("2".equals(session.getAttribute("limit"))) {
            map.put("msg", "您没有权限！");
            return "back/exPage";
        }
        wbService.setWbInfo(wbId, wbReceName, wbRecePhone, wbReceAddress, wbReceTime,
                wbPrice, wbStatus, wbInfo);
        attr.addAttribute("id", wbId);
        return "redirect:/wb/one";
    }

}
