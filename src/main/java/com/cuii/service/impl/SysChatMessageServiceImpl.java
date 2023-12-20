package com.cuii.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuii.vo.CharMessageVo;
import com.cuii.vo.CharObjectVo;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuii.entity.SysChatMessage;
import java.util.List;
import com.cuii.mapper.SysChatMessageMapper;
import com.cuii.service.SysChatMessageService;
@Service
public class SysChatMessageServiceImpl extends ServiceImpl<SysChatMessageMapper, SysChatMessage> implements SysChatMessageService{

    @Override
    public int batchInsert(List<SysChatMessage> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public List<CharObjectVo> getToChatObject(Integer id) {
        List<CharObjectVo> list = baseMapper.getToChatObject(id);
        for (CharObjectVo charObjectVo : list) {
            charObjectVo.setUnreadCut(baseMapper.getUnreadMessageCut(id,charObjectVo.getUserId()));
        }
        return list;
    }


    @Override
    public void initChatUser(Integer sendId, Integer toId) {
        QueryWrapper<SysChatMessage> wrapper = new QueryWrapper<>();
        wrapper.eq(SysChatMessage.COL_SEND_ID,toId).eq(SysChatMessage.COL_TO_ID,sendId);
        long count = count(wrapper);
        if (count == 0){
            SysChatMessage message = new SysChatMessage();
            message.setSendId(toId);
            message.setToId(sendId);
            message.setContent("");
            message.setStatus(-1);
            save(message);
        }
    }

    @Override
    public List<CharMessageVo> getCharMessageVo(Integer id, Integer tId) {
        return null;
    }

    @Override
    public void readAllMessage(Integer id, Integer tid) {
        baseMapper.readAllMessage(id,tid);
    }

    @Override
    public List<SysChatMessage> getMessageList(Integer loginId, Integer tId) {

        return baseMapper.getMessageList(loginId,tId);

    }


}
