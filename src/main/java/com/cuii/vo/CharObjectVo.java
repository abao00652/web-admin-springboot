package com.cuii.vo;

import lombok.Data;

import java.util.Date;

/**
 * 聊天状态
 */
@Data
public class CharObjectVo {

    private Integer userId;
    private Date lastMessageTime;
    private String content;
    private String avatar;
    private String username;
    private String nickname;

    private Integer unreadCut = 0;
}
