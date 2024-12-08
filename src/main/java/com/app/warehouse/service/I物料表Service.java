package com.app.warehouse.service;

import com.app.warehouse.model.物料表;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
public interface I物料表Service extends IService<物料表> {

    void add物料(物料表 物料);

    void delete物料(int id);

    void update物料(物料表 物料);

    List<物料表> search物料(String keyword);
}
