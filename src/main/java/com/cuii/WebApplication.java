package com.cuii;

import cn.dev33.satoken.secure.SaSecureUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
@MapperScan("com.cuii.mapper")
public class WebApplication {
    public static void main(String[] args)  {
        SpringApplication.run(WebApplication.class,args);
    }
}