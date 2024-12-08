package com.app.warehouse.service.impl;

import com.app.warehouse.model.物料表;
import com.app.warehouse.dao.物料表Mapper;
import com.app.warehouse.service.I物料表Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
@Service
public class 物料表ServiceImpl extends ServiceImpl<物料表Mapper, 物料表> implements I物料表Service {

    @Override
    public void add物料(物料表 物料) {
        // 调用Mapper的insert方法添加物料
        this.baseMapper.insert(物料);
    }

    @Override
    public void delete物料(int id) {
        // 调用Mapper的deleteById方法根据ID删除物料
        this.baseMapper.deleteById(id);
    }

    @Override
    public void update物料(物料表 物料) {
        // 调用Mapper的update方法更新物料
        this.baseMapper.updateById(物料);
    }

    @Override
    public List<物料表> search物料(String keyword) {
        // 使用MyBatis-Plus的Wrapper进行模糊查询
        QueryWrapper<物料表> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("物料名称", keyword); // 假设搜索条件是物料名称
        return this.baseMapper.selectList(queryWrapper);
    }
}