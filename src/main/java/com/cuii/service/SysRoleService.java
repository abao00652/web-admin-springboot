package com.cuii.service;

import com.cuii.entity.SysRole;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SysRoleService extends IService<SysRole>{


    int batchInsert(List<SysRole> list);

}
