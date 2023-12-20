package com.cuii.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.cuii.common.Result;
import com.cuii.dto.UserLoginDto;
import com.cuii.dto.UserRegisterDto;
import com.cuii.entity.SysUser;
import com.cuii.service.SysUserService;
import com.cuii.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService userService;



    /**
     * 登录接口
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginDto dto){
        log.info("用户登录:{}",dto);
        return Result.success(userService.login(dto.getUsername(),dto.getPassword()));
    }

    /**
     * 注册接口
     * @return
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDto dto){
        log.info("用户注册:{}",dto);
        return Result.success();
    }

    /**
     * 获取用户信息接口
     * @return
     */
    @GetMapping("/account")
    public Result<UserInfoVo> getUserInfo(){
        int loginId = StpUtil.getLoginIdAsInt();
        SysUser user = userService.getById(loginId);
        UserInfoVo vo = new UserInfoVo();
        BeanUtils.copyProperties(user,vo);
        List<String> roles = userService.getRoles(loginId);
        Result<UserInfoVo> result
                =
                Result.success(vo);
        result.put("role",roles).put("permissions",roles);
        return result;
    }



    /**
     * 修改用户个人信息
     * @param user
     * @return
     */
    @PostMapping("/account")
    public Result<String> updateInfo(@RequestBody SysUser user){
        user.setId(StpUtil.getLoginIdAsInt());
        user.setPassword(null);
        user.setUsername(null);
        user.setCreateTime(null);
        user.setUpdateTime(new Date());
        log.info("修改用户个人信息,{}",user);
        userService.updateById(user);
        return Result.success();
    }



}
