package com.cuii.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysUserVo {
    private Integer id;
    private String username;
    private String nickname;

    private String avatar;

    private String address;
    private Date birthday;
    private String email;

    private String phone;

    private String gender;

    private Integer age;

    private Date createTime;

    private List<String> roleNames;

}
