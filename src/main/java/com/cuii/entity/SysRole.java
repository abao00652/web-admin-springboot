package com.cuii.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "sys_role")
public class SysRole {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色关键字
     */
    @TableField(value = "role_key")
    private String roleKey;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_ROLE_KEY = "role_key";
}