package com.cuii.controller;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuii.common.Result;
import com.cuii.dto.PageParam;
import com.cuii.entity.SysUser;
import com.cuii.error.ServerException;
import com.cuii.service.SysUserService;
import com.cuii.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sys/user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private SysUserService userService;

    /**
     * 查询用户列表
     */
    @GetMapping("/list")
    public Result<List<SysUserVo>> queryList(PageParam pageParam, SysUser user){
        log.info("查询用户列表:{},{}",pageParam,user);
        QueryWrapper<SysUser> query = createQuery(user);
        Page<SysUser> page = userService.page(new Page<>(pageParam.getCurrent(), pageParam.getPageSize()), query);

        List<SysUser> records = page.getRecords();
        List<SysUserVo> list = new ArrayList<>();
        Result<List<SysUserVo>> success = Result.success(list);
        for (SysUser record : records) {
            SysUserVo vo = new SysUserVo();
            list.add(vo);
            BeanUtils.copyProperties(record,vo);
            vo.setRoleNames(userService.getRolesName(vo.getId()));
        }
        success.setTotal(page.getTotal());
        log.info("查询用户列表:{}",success);
        return success;
    }

    /**
     * 重置密码
     */
    @PostMapping("/reset/{id}")
    public Result<String> updatePwd(@PathVariable Integer id){
        log.info("重置密码:{}",id);
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(BCrypt.hashpw("123456"));
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody SysUser user){
        log.info("添加用户:{}",user);
        user.setCreateTime(new Date());
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            throw new ServerException("用户名不能为空!");
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            throw new ServerException("密码不能为空!");
        }
        userService.saveUser(user);
        return Result.success();
    }

    /**
     * 获取用户
     * @return
     */
    @GetMapping("/{id}")
    public Result<SysUser> getOne(@PathVariable Integer id){
        log.info("获取用户:{}",id);
        return Result.success(userService.getById(id));
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping
    public Result<String> updateUser(@RequestBody SysUser user){
        log.info("修改用户,{}",user);
        user.setPassword(null);
        user.setUsername(null);
        user.setCreateTime(null);
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 分配用户角色
     */
    @PostMapping("/upd/roles/{id}")
    public Result<String> updateRole(@PathVariable Integer id,@RequestBody List<String> roleKeys){
        if (roleKeys.isEmpty()){
            throw new ServerException("最少分配一个角色给用户!");
        }
        userService.updateRole(id,roleKeys);
        return Result.success();
    }


}
