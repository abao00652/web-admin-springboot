package com.cuii.socket;

import lombok.Data;

@Data
public class ChatMessageContext {

    //区分消息类型，如果是1表示普通消息，如果是2表示新建联系人消息
    private Integer type;

    //消息内容
    private String context;

    //发送给谁
    private Integer sendId;

}
