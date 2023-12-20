package com.cuii.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuii.error.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.cuii.entity.SysUser;
import com.cuii.mapper.SysUserMapper;
import com.cuii.service.SysUserService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Override
    public int batchInsert(List<SysUser> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public String login(String username, String password) {
        SysUser user = getOne(new QueryWrapper<SysUser>().eq(SysUser.COL_USERNAME, username));
        log.info("当前查询的登录用户:{}",user);
        if (user == null){
            throw new ServerException("用户名不存在!");
        }
        log.info("加密后的密码为:{}",BCrypt.hashpw(password));
        if (!BCrypt.checkpw(password,user.getPassword())){
            throw new ServerException("密码错误!");
        }
        StpUtil.login(user.getId());
        return StpUtil.getTokenInfo().tokenValue;
    }

    @Override
    public List<String> getRoles(Integer userId) {
        return baseMapper.getRoles(userId);
    }

    @Override
    public List<String> getRolesName(Integer id) {
        return baseMapper.getRolesName(id);
    }

    @Override
    public void saveUser(SysUser user) {
        SysUser db = getOne(new QueryWrapper<SysUser>().eq(SysUser.COL_USERNAME, user.getUsername()));
        if (db != null){
            throw new ServerException("用户名已经存在!");
        }
        user.setId(null);
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        save(user);
    }

    @Override
    @Transactional
    public void updateRole(Integer id, List<String> roleKeys) {
        baseMapper.clearRoles(id);
        for (String roleKey : roleKeys) {
            baseMapper.addRole(id,roleKey);
        }
    }

}
