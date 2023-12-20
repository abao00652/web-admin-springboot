package com.cuii.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_chat_message")
public class SysChatMessage {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发送人id
     */
    @TableField(value = "send_id")
    private Integer sendId;

    /**
     * 接受人id
     */
    @TableField(value = "to_id")
    private Integer toId;

    /**
     * 消息类型
     */
    @TableField(value = "message_type")
    private Integer messageType;

    /**
     * 消息内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 载体
     */
    @TableField(value = "`source`")
    private String source;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    public static final String COL_ID = "id";

    public static final String COL_SEND_ID = "send_id";

    public static final String COL_TO_ID = "to_id";

    public static final String COL_MESSAGE_TYPE = "message_type";

    public static final String COL_CONTENT = "content";

    public static final String COL_SOURCE = "source";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_STATUS = "status";
}