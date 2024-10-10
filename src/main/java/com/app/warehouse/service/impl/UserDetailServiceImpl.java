package com.app.warehouse.service.impl;

import com.app.warehouse.dao.UserMapper;
import com.app.warehouse.model.User;
import com.app.warehouse.model.WMSUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("人员代码", userName));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        WMSUser wmsUser = new WMSUser();
        wmsUser.setUser(user);
        return wmsUser;
    }
}
