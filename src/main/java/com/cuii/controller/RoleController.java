package com.cuii.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuii.common.Result;
import com.cuii.entity.SysRole;
import com.cuii.service.SysRoleService;
import com.cuii.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserService userService;

    @GetMapping("/list")
    public Result<List<SysRole>> list(){
        List<SysRole> list = roleService.list();
        return Result.success(list);
    }

    /**
     * 获取用户权限列表
     */
    @GetMapping("/user/{id}")
    public Result<List<String>> getMyRoleList(@PathVariable Integer id){
        List<String> roles = userService.getRoles(id);
        return Result.success(roles);
    }



    @PostMapping
    public Result<String> save(@RequestBody SysRole role){
        List<SysRole> list = roleService.list(new QueryWrapper<SysRole>().eq(SysRole.COL_ROLE_KEY, role.getRoleKey()));
        if (!list.isEmpty()){
            role.setId(null);
            roleService.save(role);
        }else {
            return Result.error("角色关键字不能重复!");
        }
        return Result.success();
    }

    @PutMapping
    public Result<String> put(@RequestBody SysRole role){
        role.setRoleKey(null);
        roleService.updateById(role);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Integer id){
        return Result.success();
    }


}
