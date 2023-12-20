package com.cuii.vo;

import java.util.List;
import lombok.Data;

@Data
public class MenuVo{
	//id
	private Integer id;
	//菜单名称
	private String title;
	//菜单徽标（右上角红色）
	private String badge;
	//浏览器路径
	private String path;
	//组件文件路径
	private String component;
	//菜单外链
	private String link;
	//类似英文名称的效果
	private String name;
	//菜单图标
	private String icon;
	//需要的权限
	private List<String> permission;
	//是否缓存菜单
	private Boolean cacheable;
	//是否渲染菜单
	private Boolean renderMenu;
	// '_self' | '_blank' 页面打开菜单
	private String target;
	//子菜单
	private List<MenuVo> children;
}