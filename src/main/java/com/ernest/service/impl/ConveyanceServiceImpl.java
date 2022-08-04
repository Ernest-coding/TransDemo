package com.ernest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ernest.mapper.SignalTransMapper;
import com.ernest.mapper.WayBillMapper;
import com.ernest.pojo.entity.Conveyance;
import com.ernest.mapper.ConveyanceMapper;
import com.ernest.pojo.entity.SignalTrans;
import com.ernest.pojo.entity.WayBill;
import com.ernest.pojo.vo.ConveyanceListPage;
import com.ernest.pojo.vo.SignalTransPage;
import com.ernest.service.IConveyanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 运输工具表	 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
public class ConveyanceServiceImpl implements IConveyanceService {

    @Autowired
    private ConveyanceMapper conveyanceMapper;
    @Autowired
    private SignalTransMapper signalTransMapper;
    @Autowired
    private WayBillMapper wayBillMapper;

    @Override
    public List<ConveyanceListPage> allCoy() {
        ArrayList<ConveyanceListPage> result = new ArrayList<>();
        for (Conveyance con : conveyanceMapper.selectList(
                new QueryWrapper<Conveyance>().ne("coy_status", 5))) {
            result.add(convertToConveyanceListPage(con));
        }
        return result;

    }

    @Override
    public ConveyanceListPage oneCoyBaseInfo(Integer id) {
        Conveyance conveyance = conveyanceMapper.selectOne(new QueryWrapper<Conveyance>().eq("coy_id", id));
        return convertToConveyanceListPage(conveyance);
    }

    @Override
    public List<SignalTransPage> oneCoySignalTrans(Integer id) {
        ArrayList<SignalTransPage> result = new ArrayList<>();
        for (SignalTrans one : signalTransMapper.selectList(new QueryWrapper<SignalTrans>().eq("sig_coy_id", id))) {
            result.add(convertToSignalTransPage(one));
        }
        return result;
    }

    @Override
    public void addCoy(Conveyance entity) {
        conveyanceMapper.insert(entity);
    }

    @Override
    public void setInfo(Integer id, Integer op, String info) {
        Conveyance coy = conveyanceMapper.selectOne(new QueryWrapper<Conveyance>().eq("coy_id", id));
        switch (op) {
            case 0:
                coy.setCoyStatus(5);
                break;
            case 1:
                coy.setCoyName(info);
                break;
            case 2:
                coy.setCoyLicense(info);
                break;
            case 3:
                coy.setCoyType(Integer.valueOf(info));
                break;
            default:
                break;
        }
        coy.setUpdTime(LocalDateTime.now());
        conveyanceMapper.updateById(coy);
    }

    /**
     * do 转 vo
     *
     * @param item do
     * @return vo
     */
    public ConveyanceListPage convertToConveyanceListPage(Conveyance item) {
        if (item == null) {
            return null;
        }
        ConveyanceListPage result = new ConveyanceListPage();
        switch (item.getCoyType()) {
            case 1:
                result.setCoyTypeStr("空运");
                result.setCoyTypeStrTheme("badge light badge-warning");
                break;
            case 2:
                result.setCoyTypeStr("海运");
                result.setCoyTypeStrTheme("badge light badge-primary");
                break;
            case 3:
                result.setCoyTypeStr("陆运");
                result.setCoyTypeStrTheme("badge light badge-info");
                break;
            default:
                break;
        }
        switch (item.getCoyStatus()) {
            case 1:
                result.setCoyStatusStr("空闲中");
                result.setCoyStatusStrTheme("badge light badge-primary");
                break;
            case 2:
                result.setCoyStatusStr("运输中");
                result.setCoyStatusStrTheme("badge light badge-success");
                break;
            case 3:
                result.setCoyStatusStr("维修中");
                result.setCoyStatusStrTheme("badge light badge-warning");
                break;
            case 4:
                result.setCoyStatusStr("已报废");
                result.setCoyStatusStrTheme("badge light badge-danger");
                break;
            case 5:
                result.setCoyStatusStr("已删除");
                result.setCoyStatusStrTheme("badge light badge-danger");
                break;
            default:
                break;
        }
        result.setCoyId(item.getCoyId());
        result.setCoyPrincipalName(item.getCoyPrincipalName());
        result.setCoyPrincipalPhone(item.getCoyPrincipalPhone());
        result.setCoyType(item.getCoyType());
        result.setCoyStatus(item.getCoyStatus());
        result.setCoyLicense(item.getCoyLicense());
        result.setCoySourceAddress(item.getCoySourceAddress());
        result.setCoyInfo(item.getCoyInfo());
        result.setAddTime(item.getAddTime());
        result.setUpdTime(item.getUpdTime());
        result.setCoyName(item.getCoyName());
        return result;
    }

    /**
     * do 转 vo
     *
     * @param item do
     * @return vo
     */
    public SignalTransPage convertToSignalTransPage(SignalTrans item) {
        if (item == null) {
            return null;
        }
        WayBill wayBill = wayBillMapper.selectOne(new QueryWrapper<WayBill>().eq("wb_id", item.getSigWbId()));
        SignalTransPage result = new SignalTransPage();
        result.setConveyance(conveyanceMapper.selectOne(new QueryWrapper<Conveyance>().eq("coy_id", item.getSigCoyId())));
        result.setSigStatusTheme(item.getSigStatus() == 1 ? "badge light badge-info" : "badge light badge-success");
        result.setTranNum(wayBill.getWbNum());
        result.setTranType(wayBill.getWbType() == 1 ? "国内运单" : "国际运单");
        result.setSendAdd(wayBill.getWbSendAddress());
        result.setReceAdd(wayBill.getWbReceAddress());
        result.setSigId(item.getSigId());
        result.setSigCoyId(item.getSigCoyId());
        result.setSigWbId(item.getSigWbId());
        result.setSigType(item.getSigType());
        result.setSigTransLine(item.getSigTransLine());
        result.setSigStatus(item.getSigStatus());
        result.setAddTime(item.getAddTime());
        result.setUpdTime(item.getUpdTime());
        return result;
    }
}
