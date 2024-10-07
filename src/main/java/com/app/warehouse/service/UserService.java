package com.app.warehouse.service;

import com.app.warehouse.common.R;
import com.app.warehouse.model.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
public interface UserService extends IService<Users> {

    R<HashMap<String, Object>> login(String userName, String password);

    R<String> logout(String userNumbers);

    R addUser(Users user);

    R<String> register(Users user);

    R updateUserById(Users user);
}
