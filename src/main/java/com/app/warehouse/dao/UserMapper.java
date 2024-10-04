package com.app.warehouse.dao;

import com.app.warehouse.model.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
@Mapper
public interface UserMapper extends BaseMapper<Users> {

}