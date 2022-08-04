package com.ernest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ernest.pojo.entity.CargoTaxDictionary;
import com.ernest.mapper.CargoTaxDictionaryMapper;
import com.ernest.service.ICargoTaxDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 货品税额信息字典表 服务实现类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
@Service
public class CargoTaxDictionaryServiceImpl implements ICargoTaxDictionaryService {

    @Autowired
    private CargoTaxDictionaryMapper cargoTaxDicMapper;

    @Override
    public List<CargoTaxDictionary> allDic() {
        return cargoTaxDicMapper.selectList(new QueryWrapper<CargoTaxDictionary>().eq("ctd_status", 1));
    }

    @Override
    public void setInfo(Integer id, Integer op, String info) {
        CargoTaxDictionary ctd = cargoTaxDicMapper.selectOne(new QueryWrapper<CargoTaxDictionary>().eq("ctd_id", id));
        switch (op) {
            case 0:
                ctd.setCtdStatus(2);
                break;
            case 1:
                ctd.setCtdTax(new BigDecimal(info));
                break;
            case 2:
                ctd.setCtdName(info);
            default:
                break;
        }
        cargoTaxDicMapper.updateById(ctd);
    }

    @Override
    public void add(CargoTaxDictionary ctd) {
        cargoTaxDicMapper.insert(ctd);
    }
}
