package com.cuii.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.cuii.entity.SysMenu;
import com.cuii.mapper.SysMenuMapper;
import com.cuii.service.SysMenuService;
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService{

    @Override
    public int batchInsert(List<SysMenu> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public List<SysMenu> getMyList(Integer id) {
        return list();
    }
}
