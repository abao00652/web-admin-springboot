package com.cuii.service;

import com.cuii.entity.SysChatMessage;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuii.vo.CharMessageVo;
import com.cuii.vo.CharObjectVo;

public interface SysChatMessageService extends IService<SysChatMessage>{

    int batchInsert(List<SysChatMessage> list);

    /**
     * 获取所有跟我聊天的对象
     */
    List<CharObjectVo> getToChatObject(Integer id);

    /**
     * 初始化对方的聊天框
     */
    void initChatUser(Integer sendId,Integer toId);

    /**
     * 获取聊天记录
     */
    List<CharMessageVo> getCharMessageVo(Integer id,Integer tId);

    /**
     * 读取所有消息,对方消息
     */
    void readAllMessage(Integer id,Integer tid);

    /**
     * 读取列表
     */
    List<SysChatMessage> getMessageList(Integer loginId, Integer tId);
}
