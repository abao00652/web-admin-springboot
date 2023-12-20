package com.cuii.service;

import java.util.List;
import com.cuii.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SysMenuService extends IService<SysMenu>{


    int batchInsert(List<SysMenu> list);

    //获取我的权限下的所有菜单
    List<SysMenu> getMyList(Integer id);

}
