package com.app.warehouse.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WMSUser implements UserDetails {

    @Resource
    private Users user;

    private List<String> permissions;

    @JSONField(serialize = false)  // 不进行序列化
    private List<GrantedAuthority> authorities;

    // 返回权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String isRoot = String.valueOf(user.getAuthority().get人员档案管理());
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(isRoot);
        authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.get密码();
    }

    @Override
    public String getUsername() {
        return user.get人员代码();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
