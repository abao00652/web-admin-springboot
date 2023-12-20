package com.cuii.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuii.entity.SysUser;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper extends BaseMapper<SysUser> {
    int batchInsert(@Param("list") List<SysUser> list);

    List<String> getRoles(Integer userId);


    @Select("SELECT t2.role_name FROM (SELECT role_key FROM sys_user_role where user_id = #{id} ) t1,sys_role t2 where t1.role_key = t2.role_key")
    List<String> getRolesName(@Param("id") Integer id);


    @Delete("DELETE FROM sys_user_role WHERE user_id = #{id}")
    void clearRoles(@Param("id") Integer id);

    @Insert("INSERT INTO sys_user_role VALUES(null,#{id},#{roleKey})")
    void addRole(@Param("id") Integer id, @Param("roleKey") String roleKey);
}