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
    private User user;

    private List<String> permissions = new ArrayList<>(); // 初始化

    @JSONField(serialize = false)  // 不进行序列化
    private List<GrantedAuthority> authorities;

    @Resource
    private Authority authority;

    // 返回权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities = new ArrayList<>();

        // 确保 authority 已初始化
        if (authority != null) {
            if (authority.get人员档案管理() != null && authority.get人员档案管理() == 1) {
                authorities.add(new SimpleGrantedAuthority("PERMISSION_PERSONNEL_MANAGEMENT"));
            }
            if (authority.get物料档案管理() != null && authority.get物料档案管理() == 1) {
                authorities.add(new SimpleGrantedAuthority("PERMISSION_MATERIAL_MANAGEMENT"));
            }
            if (authority.get进出仓管理() != null && authority.get进出仓管理() == 1) {
                authorities.add(new SimpleGrantedAuthority("PERMISSION_WAREHOUSE_MANAGEMENT"));
            }
            if (authority.get权限管理() != null && authority.get权限管理() == 1) {
                authorities.add(new SimpleGrantedAuthority("PERMISSION_AUTHORITY_MANAGEMENT"));
            }
            if (authority.get统计打印() != null && authority.get统计打印() == 1) {
                authorities.add(new SimpleGrantedAuthority("PERMISSION_STATISTICS_PRINTING"));
            }
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user != null ? user.get密码() : null; // 防止 user 为 null
    }

    @Override
    public String getUsername() {
        return user != null ? user.get人员代码() : null; // 防止 user 为 null
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

    @Override
    public String toString() {
        return "WMSUser{" +
                "user=" + user +
                ", permissions=" + permissions +
                ", authorities=" + authorities +
                '}';
    }
}
