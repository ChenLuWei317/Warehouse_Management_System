package com.app.warehouse.service.impl;

import com.app.warehouse.common.R;
import com.app.warehouse.model.WMSUser;
import com.app.warehouse.model.Users;
import com.app.warehouse.dao.UserMapper;
import com.app.warehouse.service.UserService;
import com.app.warehouse.utils.JwtUtil;
import com.app.warehouse.utils.RedisUtil;
import com.app.warehouse.utils.Commons;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {

    @Resource
    RedisUtil redisUtil;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    AuthenticationManager authenticationManager;

    UserMapper userMapper;

    public Users getUserWithAuthority(String userName) {
        return userMapper.selectUserWithAuthority(userName);
    }



    @Override
    public R<HashMap<String, Object>> login(String userName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

        Authentication authenticate = null;

        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return R.error("请检查用户名和密码");
        }

        WMSUser wmsUser = (WMSUser) authenticate.getPrincipal();

        String userNumbers = wmsUser.getUser().get人员代码();


        // 验证成功

        Map<String, Object> map = new HashMap<>();
        map.put("userNumbers", userNumbers);
        redisUtil.set(Commons.REDIS_KEY_WMS_USER + userNumbers, wmsUser, RedisUtil.ONE_HOUR);

        String token = JwtUtil.sign(map);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);

        // 动态获取用户权限
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) wmsUser.getAuthorities();
        boolean isSuperAdmin = authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("PERMISSION_PERSONNEL_MANAGEMENT"));

        if (isSuperAdmin) {
            resultMap.put("identify", "superAdmin");
        } else {
            resultMap.put("identify", "admin");
        }

        return R.success(resultMap);
    }

    @Override
    public R<String> logout(String userNumbers) {
        redisUtil.delete(Commons.REDIS_KEY_WMS_USER + userNumbers);
        return R.success();
    }

    @Override
    public R addUser(Users user) {
        // 判断是否已经存在
        String userNumbers = user.get人员代码();
        Users users = query().eq("user_numbers", userNumbers).one();
        if (!Objects.isNull(users)) {
            return R.error("用户已存在");
        }
        user.set密码(passwordEncoder.encode("123456"));
        save(user);
        return R.success();
    }
    @Override
    @Transactional
    public R<String> register(Users user) {
        // 校验用户输入
        if (!checkStringNumbers(user.get人员代码(), 18)) {
            return R.error("Incorrectly formatted User ID number !!");
        }

        // 检查用户是否已存在
        Users existingUser = query().eq("人员代码", user.get人员代码()).one();
        if (existingUser != null) {
            return R.error("User already exists!");
        }

        // 使用 BCrypt 编码密码
        String encodedPassword = passwordEncoder.encode(user.get密码());
        user.set密码(encodedPassword);

        // 保存用户
        if (save(user)) {
            return R.success("Registration successful!");
        }

        return R.error("Registration failed!");
    }

    // 校验字符串中的数字格式
    private boolean checkStringNumbers(String numberString, int size) {
        return numberString != null && numberString.length() == size && numberString.matches("\\d+");
    }


    @Override
    public R updateUserById(Users user){
        // 判断是否已经存在
        String userNumbers = user.get人员代码();
        Users users = query().eq("user_numbers", userNumbers).one();
        if (!Objects.isNull(users)) {
            updateById(user);
            return R.success("用户信息更新成功");
        }
        return R.error("用户不存在");
//更新操作

    }
}
