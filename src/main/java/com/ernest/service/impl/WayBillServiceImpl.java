package com.ernest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ernest.mapper.*;
import com.ernest.pojo.entity.*;
import com.ernest.pojo.vo.SignalTransPage;
import com.ernest.pojo.vo.WayBillDetailPage;
import com.ernest.service.IConveyanceService;
import com.ernest.service.IUserInfoService;
import com.ernest.service.IWayBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 运单表 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
@Slf4j
public class WayBillServiceImpl implements IWayBillService {

    @Autowired
    private WayBillMapper wbMapper;
    @Autowired
    private CargoInfoMapper cargoInfoMapper;
    @Autowired
    private SignalTransMapper signalTransMapper;
    @Autowired
    private ArrivalPortInfoMapper arrivalPortInfoMapper;
    @Autowired
    private CustomsDeclarationMapper customsDeclarationMapper;
    @Autowired
    private ConveyanceMapper conveyanceMapper;
    @Autowired
    private CargoTaxDictionaryMapper cargoTaxDictionaryMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IConveyanceService conveyanceService;

    @Override
    public List<WayBill> all() {
        return wbMapper.selectList(null);
    }

    @Override
    public WayBillDetailPage detail(Integer id) {
        WayBillDetailPage result = new WayBillDetailPage();
        WayBill wb = wbMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", id));
        // 1.运单本身信息
        result.setWayBill(wb);
        // 2. 货物信息
        result.setCargoInfo(cargoInfoMapper.selectOne(
                new QueryWrapper<CargoInfo>().eq("cgi_id", wb.getWbCgiId())));
        // 3. 运次列表信息
        ArrayList<SignalTransPage> stp = new ArrayList<>();
        for (SignalTrans st :
                signalTransMapper.selectList(new QueryWrapper<SignalTrans>().eq("sig_wb_id", wb.getWbId()))) {
            stp.add(conveyanceService.convertToSignalTransPage(st));
        }
        result.setSignalTransList(stp);


        // 4. 到港信息列表
        result.setArrivalPortInfo(arrivalPortInfoMapper.selectList(
                new QueryWrapper<ArrivalPortInfo>().eq("arp_wb_id", wb.getWbId())));
        // 5. 报关信息
        result.setCustomsDeclaration(customsDeclarationMapper.selectList(
                new QueryWrapper<CustomsDeclaration>().eq("ctd_wb_id", wb.getWbId())));
        result.setConveyanceList(conveyanceMapper.selectList(
                new QueryWrapper<Conveyance>().ne("coy_status", 5)));
        result.setCargoTaxDictionaryList(cargoTaxDictionaryMapper.selectList(
                new QueryWrapper<CargoTaxDictionary>().eq("ctd_status", 1)));
        return result;
    }

    @Override
    public int addCargo(CargoInfo cargoInfo) {
        return cargoInfoMapper.insert(cargoInfo);
    }

    @Override
    public int addWayBill(WayBill wayBill, String wbSendTime, String wbReceTime) {
        // 解析 wbSendTime
        if (!"null".equals(wbSendTime)) {
            wayBill.setWbSendTime(wbSendTime);
        } else {
            wayBill.setWbSendTime(LocalDateTime.now().toString());
        }
        // 解析 wbReceTime
        if (!"null".equals(wbReceTime)) {
            wayBill.setWbReceTime(wbReceTime);
        } else {
            wayBill.setWbReceTime(null);
        }
        // 查找客户
        Customer customer = customerMapper.selectOne(new QueryWrapper<Customer>()
                .eq("cus_name", wayBill.getWbSendName())
                .eq("cus_phone", wayBill.getWbSendPhone()));
        if (customer != null) {
            wayBill.setWbSendId(customer.getCusId());
            customer.setCusOrder(customer.getCusOrder() + 1);
            customerMapper.updateById(customer);
        } else {
            int index = userInfoService.addCus(wayBill.getWbSendName(), wayBill.getWbSendPhone(), 1);
            wayBill.setWbSendId(index);
        }
        // 插入
        return wbMapper.insert(wayBill);
    }

    @Override
    public void delWb(Integer id) {
        WayBill wb = wbMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", id));
        // 删除与该运单相关的一切，包括运次、报关、到港、货品、客户的运单数
        for (SignalTrans sig : signalTransMapper.selectList(
                new QueryWrapper<SignalTrans>().eq("sig_wb_id", wb.getWbId()))) {
            delOth(1, sig.getSigId());
        }
        delOth(2, customsDeclarationMapper.selectOne(
                new QueryWrapper<CustomsDeclaration>().eq("ctd_wb_id", wb.getWbId())).getCtdId());

        for (ArrivalPortInfo arp : arrivalPortInfoMapper.selectList(new QueryWrapper<ArrivalPortInfo>().eq("arp_wb_id", wb.getWbId()))) {
            delOth(3, arp.getArpId());
        }
        cargoInfoMapper.delete(new QueryWrapper<CargoInfo>().eq("cgi_id", wb.getWbCgiId()));
        Customer cus = customerMapper.selectOne(new QueryWrapper<Customer>().eq("cus_id", wb.getWbSendId()));
        cus.setCusOrder(cus.getCusOrder() - 1);
        customerMapper.updateById(cus);
        wbMapper.deleteById(wb);

    }

    @Override
    public void delOth(Integer op, Integer id) {
        switch (op) {
            case 1:
                // 运次，先释放运输工具状态，再删除运次
                SignalTrans sig = signalTransMapper.selectOne(new QueryWrapper<SignalTrans>().eq("sig_id", id));
                if (sig.getSigStatus() == 1) {
                    Conveyance coy = conveyanceMapper.selectOne(new QueryWrapper<Conveyance>().eq("coy_id", sig.getSigCoyId()));
                    coy.setCoyStatus(1);
                    conveyanceMapper.updateById(coy);
                }
                signalTransMapper.deleteById(sig);
                break;
            case 2:
                // 报关，先重置运单状态，再删除运次
                CustomsDeclaration ctd = customsDeclarationMapper.selectOne(new QueryWrapper<CustomsDeclaration>().eq("ctd_id", id));
                WayBill wb2 = wbMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", ctd.getCtdWbId()));
                // 这里可能要修改流程，可以放在后期迭代时修改
                customsDeclarationMapper.deleteById(ctd);
                break;
            case 3:
                // 到港，先重置运单状态，再删除到港
                ArrivalPortInfo arp = arrivalPortInfoMapper.selectOne(new QueryWrapper<ArrivalPortInfo>().eq("arp_id", id));
                WayBill wb3 = wbMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", arp.getArpWbId()));
                if (wb3.getWbStatus() == 4) {
                    wb3.setWbStatus(3);
                    wbMapper.updateById(wb3);
                }
                arrivalPortInfoMapper.deleteById(arp);
                break;
            default:
                break;
        }

    }

    @Override
    public void setWbInfo(Integer wbId, String wbReceName, String wbRecePhone,
                          String wbReceAddress, String wbReceTime, String wbPrice,
                          Integer wbStatus, String wbInfo) {
        WayBill wb = wbMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", wbId));
        wb.setWbReceName(wbReceName);
        wb.setWbRecePhone(wbRecePhone);
        wb.setWbReceAddress(wbReceAddress);
        wb.setWbReceTime(wbReceTime);
        wb.setWbPrice(new BigDecimal(wbPrice));
        wb.setWbStatus(wbStatus);
        wb.setWbInfo(wbInfo);
        wb.setUpdTime(LocalDateTime.now());
        wbMapper.updateById(wb);
    }

    @Override
    public void setTran(Integer stId, Integer coyId, Integer type,
                        String transLine, Integer status) {
        SignalTrans sig = signalTransMapper.selectOne(new QueryWrapper<SignalTrans>().eq("sig_id", stId));
        if (sig.getSigStatus() == 1) {
            // 重置原本车辆的状态
            Conveyance coy = conveyanceMapper.selectOne(new QueryWrapper<Conveyance>().eq("coy_id", sig.getSigCoyId()));
            coy.setCoyStatus(1);
            conveyanceMapper.updateById(coy);
        }
        sig.setSigCoyId(coyId);
        sig.setSigType(type);
        sig.setSigTransLine(transLine);
        sig.setSigStatus(status);
        sig.setUpdTime(LocalDateTime.now());
        signalTransMapper.updateById(sig);
        if (status == 1) {
            // 设置当前车辆状态
            Conveyance coy = conveyanceMapper.selectOne(new QueryWrapper<Conveyance>().eq("coy_id", coyId));
            coy.setCoyStatus(2);
            coy.setUpdTime(LocalDateTime.now());
            conveyanceMapper.updateById(coy);
        }
    }

    @Override
    public void setCtd(Integer ctdId, Integer cgtxId, String address,
                       String price, String startTime, String finishTime) {
        CustomsDeclaration ctd = customsDeclarationMapper.selectOne(new QueryWrapper<CustomsDeclaration>().eq("ctd_id", ctdId));
        ctd.setCtdCgtxId(cgtxId);
        ctd.setCtdAddress(address);
        ctd.setCtdPrice(new BigDecimal(price));
        ctd.setCtdStartTime(startTime);
        ctd.setCtdFinishTime(finishTime);
        ctd.setUpdTime(LocalDateTime.now());
        customsDeclarationMapper.updateById(ctd);
    }

    @Override
    public void setArr(Integer arpId, String name, String inTime,
                       String outTime, String perPrice, String mulPrice) {
        ArrivalPortInfo arp = arrivalPortInfoMapper.selectOne(new QueryWrapper<ArrivalPortInfo>().eq("arp_id", arpId));
        arp.setArpName(name);
        arp.setArpInTime(inTime);
        arp.setArpOutTime(outTime);
        arp.setArpPerPrice(new BigDecimal(perPrice));
        arp.setArpMulPrice(new BigDecimal(mulPrice));
        arp.setUpdTime(LocalDateTime.now());
        arrivalPortInfoMapper.updateById(arp);
    }

    @Override
    public WayBillDetailPage userSearchWayBill(String phone, String wbNum) {
        WayBill wb = wbMapper.selectOne(new QueryWrapper<WayBill>()
                .eq("wb_send_phone", phone)
                .eq("wb_num", wbNum));
        if (wb == null) {
            return null;
        }
        return detail(wb.getWbId());
    }

    @Override
    public void addTran(SignalTrans signalTrans) {
        log.info("添加了一个运次  " + signalTrans.toString());
        signalTransMapper.insert(signalTrans);
        // 这里要设置对应交通工具的状态
        Conveyance coy = conveyanceMapper.selectOne(new QueryWrapper<Conveyance>().eq("coy_id", signalTrans.getSigCoyId()));
        coy.setCoyStatus((signalTrans.getSigStatus() == 1) ? 2 : 1);
        conveyanceMapper.updateById(coy);
        // 同时更新运单状态
        WayBill wb = wbMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", signalTrans.getSigWbId()));
        if (wb.getWbStatus() == 2 || wb.getWbStatus() == 4) {
            wb.setWbStatus(wb.getWbStatus() + 1);
            wbMapper.updateById(wb);
        }
    }

    @Override
    public void addCtd(CustomsDeclaration ctd) {
        log.info("添加了一个报关信息  " + ctd.toString());
        customsDeclarationMapper.insert(ctd);
        // 这里要对应设置运单的状态
        WayBill wb = wbMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", ctd.getCtdWbId()));
        wb.setWbStatus(wb.getWbStatus() + 1);
        wbMapper.updateById(wb);
    }

    @Override
    public void addArr(ArrivalPortInfo arr) {
        log.info("添加了一个到港信息  " + arr.toString());
        arrivalPortInfoMapper.insert(arr);
        // 这里要对应设置运单的状态
        WayBill wb = wbMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", arr.getArpWbId()));
        wb.setWbStatus(4);
        wbMapper.updateById(wb);
    }

}
