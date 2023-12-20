package com.cuii.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "sys_menu")
public class SysMenu {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 路径
     */
    @TableField(value = "`path`")
    private String path;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 微标(右上角)
     */
    @TableField(value = "badge")
    private String badge;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 类似英文名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 外部链接
     */
    @TableField(value = "link")
    private String link;

    /**
     * 需要的权限
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 是否缓存
     */
    @TableField(value = "cacheable")
    private Boolean cacheable;

    /**
     * 是否渲染菜单
     */
    @TableField(value = "render_Menu")
    private Boolean renderMenu;

    /**
     * 打开方式
     */
    @TableField(value = "target")
    private String target;

    /**
     * 父节点id
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_PATH = "path";

    public static final String COL_COMPONENT = "component";

    public static final String COL_BADGE = "badge";

    public static final String COL_ICON = "icon";

    public static final String COL_NAME = "name";

    public static final String COL_LINK = "link";

    public static final String COL_PERMISSION = "permission";

    public static final String COL_CACHEABLE = "cacheable";

    public static final String COL_RENDER_MENU = "render_Menu";

    public static final String COL_TARGET = "target";

    public static final String COL_PARENT_ID = "parent_id";
}