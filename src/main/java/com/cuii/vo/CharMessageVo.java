package com.cuii.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CharMessageVo {

    private Integer id;
    //消息内容
    private String content;
    //发送时间
    private Date createTime;
    //消息冗余载体
    private String source;
    //消息状态
    private Integer status;

}
