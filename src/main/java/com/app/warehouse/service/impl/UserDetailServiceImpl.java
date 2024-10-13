package com.app.warehouse.service.impl;

import com.app.warehouse.dao.AuthorityMapper;
import com.app.warehouse.dao.UserMapper;
import com.app.warehouse.model.Authority;
import com.app.warehouse.model.User;
import com.app.warehouse.model.WMSUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorityMapper authorityMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("人员代码", username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Authority authority = authorityMapper.selectOne(new QueryWrapper<Authority>().eq("人员代码", username));
        if (authority == null) {
            throw new UsernameNotFoundException("权限信息不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (authority.get人员档案管理() != null && authority.get人员档案管理() == 1) {
            System.out.println("拥有人员档案权限");

            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        }
        return new org.springframework.security.core.userdetails.User(
                user.get人员代码(),
                user.get密码(),
                authorities
        );
    }
}
