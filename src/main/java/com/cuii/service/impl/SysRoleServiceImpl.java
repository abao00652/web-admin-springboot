package com.cuii.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuii.entity.SysRole;
import com.cuii.mapper.SysRoleMapper;
import java.util.List;
import com.cuii.service.SysRoleService;
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{

    @Override
    public int batchInsert(List<SysRole> list) {
        return baseMapper.batchInsert(list);
    }
}
