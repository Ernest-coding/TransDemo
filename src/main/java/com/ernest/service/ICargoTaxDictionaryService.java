package com.ernest.service;

import com.ernest.pojo.entity.CargoTaxDictionary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 货品税额信息字典表 服务类
 * </p>
 *
 * @author Ernest
 * @since 2022-08-02
 */
public interface ICargoTaxDictionaryService {

    List<CargoTaxDictionary> allDic();

    void setInfo(Integer id, Integer op, String info);

    void add(CargoTaxDictionary ctd);
}
