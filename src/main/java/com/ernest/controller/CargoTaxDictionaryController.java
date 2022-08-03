package com.ernest.controller;

import com.ernest.pojo.entity.CargoTaxDictionary;
import com.ernest.service.ICargoTaxDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 货品税额信息字典表 前端控制器
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Controller
@RequestMapping("/cargoTaxDic")
public class CargoTaxDictionaryController {

    @Autowired
    private ICargoTaxDictionaryService cargoTaxDictionaryService;

    @GetMapping("/allDic")
    public String allDic(ModelMap map) {
        List<CargoTaxDictionary> dicList = cargoTaxDictionaryService.allDic();
        map.put("dicList", dicList);
        return "cargoTaxDic";
    }

    /**
     * 设置税额信息
     *
     * @param map
     * @param id   id
     * @param op   操作码 0-删除  1-修改货品税额单价
     * @param info 对应 op 的信息
     * @return
     */
    @GetMapping("/setInfo")
    public String setInfo(ModelMap map,
                          @RequestParam(name = "id") Integer id,
                          @RequestParam(name = "op") Integer op,
                          @RequestParam(name = "info") String info) {
        cargoTaxDictionaryService.setInfo(id, op, info);
        return "redirect:/cargoTaxDic/allDic";
    }

    @PostMapping("/add")
    public String add(ModelMap map,
                      @RequestParam(name = "name") String name,
                      @RequestParam(name = "type") String type,
                      @RequestParam(name = "tax") String tax) {
        CargoTaxDictionary ctd = new CargoTaxDictionary();
        ctd.setCtdName(name);
        ctd.setCtdType(type);
        ctd.setCtdTax(new BigDecimal(tax));
        ctd.setCtdStatus(1);
        cargoTaxDictionaryService.add(ctd);
        return "redirect:/cargoTaxDic/allDic";
    }

}
