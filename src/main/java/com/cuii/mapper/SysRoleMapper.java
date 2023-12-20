package com.cuii.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuii.entity.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    int batchInsert(@Param("list") List<SysRole> list);
}