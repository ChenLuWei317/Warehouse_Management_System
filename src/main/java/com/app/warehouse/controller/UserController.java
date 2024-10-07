package com.app.warehouse.controller;

import com.app.warehouse.common.R;
import com.app.warehouse.model.Users;
import com.app.warehouse.model.WMSUser;
import com.app.warehouse.model.dto.LoginDto;
import com.app.warehouse.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserServiceImpl userService;

    @GetMapping("/")
    public String helloUser() {
        return "users!";
    }

    @ResponseBody
    @GetMapping("/login")
    public R login(@ModelAttribute LoginDto loginDto) {
        return userService.login(loginDto.getUserName(), loginDto.getPassword());
    }

    @GetMapping("/logout")
    public R<String> logout(@AuthenticationPrincipal WMSUser wmsUser) {
        String userNumbers = wmsUser.getUsername();
        return userService.logout(userNumbers);
    }

    /**
     * @PreAuthorize("hasAuthority('1')")
     * 拥有人员档案管理权限的admin才能使用的方法
     */
    @PreAuthorize("hasAuthority(@authorityConstants.PERSONNEL_MANAGEMENT)")
    @GetMapping("/selectAll")
    public R<List<Users>> selectAll() {
        List<Users> userList = userService.list((Wrapper<Users>) null);
        return R.success(userList);
    }

    @PreAuthorize("hasAuthority(@authorityConstants.PERSONNEL_MANAGEMENT)")
    @GetMapping("/selectPage")
    public R<IPage<Users>> selectPage(Integer current, Integer size) {
        IPage<Users> page = new Page<>(current, size);
        userService.page(page);
        return R.success(page);
    }

    @PreAuthorize("hasAuthority(@authorityConstants.PERSONNEL_MANAGEMENT)")
    @PostMapping("/addUser")
    public R addUser(@RequestBody Users user) {
        if (!checkStringNumbers(user.get人员代码(), 18)) {
            return R.error("Incorrectly formatted User ID number !!");
        }
        return userService.addUser(user);
    }

    @PreAuthorize("hasAuthority(@authorityConstants.PERSONNEL_MANAGEMENT)")
    @PostMapping("/deleteByName")
    public R<String> deleteUserByName(@RequestBody Users user) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.eq("user_name", user.get人员代码());
        if (userService.remove(qw)) {
            return R.success("delete success!!");
        }
        return R.error("delete fails!!");
    }

    @PreAuthorize("hasAuthority(@authorityConstants.PERSONNEL_MANAGEMENT)")
    @PostMapping("/deleteById")
    public R<String> deleteUserById(@RequestBody Users user) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.eq("user_numbers", user.get人员代码());
        if (userService.remove(qw)) {
            return R.success("delete success!!");
        }
        return R.error("delete fails!!");
    }

    @PreAuthorize("hasAuthority(@authorityConstants.PERSONNEL_MANAGEMENT)")
    @PostMapping("/updateUserById")
    public R<String> updateUser(@RequestBody Users user) {
        if (!checkStringNumbers(user.get人员代码(), 18)) {
            return R.error("Incorrectly formatted User ID number !!");
        }
        return userService.updateUserById(user);
    }

    @PreAuthorize("permitAll") // 允许所有用户访问注册接口
    @PostMapping("/register")
    public R<String> register(@RequestBody Users user) {
        return userService.register(user);
    }


    /**
     * 校验字符串中的数字格式
     * size:数字的个数
     */
    private static boolean checkStringNumbers(String numberString, int size) {
        // 校验身份证格式
        if (numberString.length() == size) {
            if (numberString.substring(0, size - 1).matches("\\d+")) {
                return true;
            }
        }
        return false;
    }
}
