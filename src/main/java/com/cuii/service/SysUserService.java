package com.cuii.service;

import java.util.List;
import com.cuii.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SysUserService extends IService<SysUser>{


    int batchInsert(List<SysUser> list);

    /**
     * 登录接口
     */
    String login(String username, String password);

    /**
     *
     * @param userId
     * @return
     */
    List<String> getRoles(Integer userId);

    /**
     * 获取所有角色名称
     */
    List<String> getRolesName(Integer id);

    void saveUser(SysUser user);


    void updateRole(Integer id, List<String> roleKeys);
}
