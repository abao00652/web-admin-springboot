package com.cuii.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuii.entity.SysChatMessage;
import java.util.List;

import com.cuii.vo.CharObjectVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysChatMessageMapper extends BaseMapper<SysChatMessage> {
    int batchInsert(@Param("list") List<SysChatMessage> list);

    /**
     * 获取所有跟我聊过天的人
     */
    @Select("SELECT\n" +
            "\tsend_id user_id,\n" +
            "\tt1.last_message_time,\n" +
            "\tt1.content,\n" +
            "\tt2.avatar,\n" +
            "\tt2.username,\n" +
            "\tt2.nickname \n" +
            "FROM\n" +
            "\t( SELECT send_id, MAX( create_time ) AS last_message_time, content FROM sys_chat_message WHERE to_id = #{id} GROUP BY send_id ORDER BY last_message_time DESC ) t1,\n" +
            "\tsys_user t2 \n" +
            "WHERE\n" +
            "\tt1.send_id = t2.id")
    List<CharObjectVo> getToChatObject(Integer id);

    /**
     * 获取所有未读消息数量
     */
    @Select("SELECT count(*) FROM sys_chat_message where send_id = #{sendId}   AND to_id = #{toId} AND STATUS = 0")
    Integer getUnreadMessageCut(@Param("sendId") Integer sendId, @Param("toId") Integer toId);

    @Update("UPDATE sys_chat_message SET `status` = 1 WHERE send_id = #{id}  AND to_id = #{tid} AND status = 0  ")
    void readAllMessage(@Param("id") Integer id, @Param("tid") Integer tid);

    @Select("SELECT * FROM sys_chat_message WHERE (send_id = #{send_id}  AND to_id = #{toId} ) or (send_id = #{send_id}  AND to_id = #{toId} )  ORDER BY create_time")
    List<SysChatMessage> getMessageList(@Param("send_id") Integer sendId, @Param("toId") Integer toId);

}