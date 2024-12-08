package com.app.warehouse.dao;

import com.app.warehouse.model.物料表;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 物料表 Mapper 接口
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
@Mapper
public interface 物料表Mapper extends BaseMapper<物料表> {

    // 可根据需要添加自定义查询方法
    @Select("SELECT * FROM material WHERE 物料名称 = #{物料名称}")
    物料表 selectByName(String 物料名称);
}