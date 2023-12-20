package com.cuii.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BaseController {

    public Integer getLoginId(){
        return StpUtil.getLoginIdAsInt();
    }

    public <T> QueryWrapper<T> createQuery(T o){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (o != null){
            Class<?> aClass = o.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                //如果不是静态变量
                if (!Modifier.isStatic(declaredField.getModifiers())) {
                    try {
                        declaredField.setAccessible(true);
                        Object o1 = declaredField.get(o);
                        TableField annotation = declaredField.getAnnotation(TableField.class);
                        if (annotation == null){
                            continue;
                        }
                        if (o1 instanceof String){
                            wrapper.like(annotation.value(),o1);
                        }
                        if (o1 instanceof Integer){
                            wrapper.eq(annotation.value(),o1);
                        }
                        if (o1 instanceof Double){
                            wrapper.eq(annotation.value(),o1);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return wrapper;
    }


}
