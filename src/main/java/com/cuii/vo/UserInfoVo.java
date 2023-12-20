package com.cuii.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVo{
	//用户名称
	private String username;
	private String nickname;
	private String gender;
	private String avatar;
	private Integer age;
	private String address;
	private Date birthday;
	private String email;
	private String phone;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

}