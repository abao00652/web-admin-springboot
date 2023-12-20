package com.cuii.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.cuii.common.Result;
import com.cuii.entity.SysMenu;
import com.cuii.service.SysMenuService;
import com.cuii.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MenuController extends BaseController {

    @Autowired
    private SysMenuService menuService;

    @GetMapping("/menu")
    public Result<List<MenuVo>> getMenus(){
        Integer loginId = getLoginId();
        List<SysMenu> list = menuService.getMyList(loginId);

        Map<Integer,MenuVo> map = new HashMap<>();

        for (SysMenu sysMenu : list) {
            MenuVo vo = new MenuVo();
            BeanUtils.copyProperties(sysMenu,vo);
            map.put(sysMenu.getId(),vo);
            vo.setChildren(new ArrayList<>());
        }
        for (SysMenu sysMenu : list) {
            if (map.containsKey(sysMenu.getParentId())) {
                //获取当前
                MenuVo vo = map.get(sysMenu.getId());
                MenuVo parentVo = map.get(sysMenu.getParentId());
                parentVo.getChildren().add(vo);
            }
        }
        List<MenuVo> res = list.stream().filter((item) -> item.getParentId() == null).map(item -> map.get(item.getId())).toList();
        return Result.success(res);
    }

    @PostMapping("/menu")
    public Result<String> save(@RequestBody SysMenu menu){
        log.info("添加菜单:{}",menu);
        return Result.success();
    }

    @PutMapping("/menu")
    public Result<String> update(@RequestBody SysMenu menu){
        menu.setPath(null);
        log.info("编辑菜单:{}",menu);
        return Result.success();
    }

    @DeleteMapping("/menu")
    public Result<String> delete(Integer id){
        log.info("删除菜单:{}",id);
        return Result.success();
    }
}
