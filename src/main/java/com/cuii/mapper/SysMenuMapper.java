package com.cuii.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuii.entity.SysMenu;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    int batchInsert(@Param("list") List<SysMenu> list);
}