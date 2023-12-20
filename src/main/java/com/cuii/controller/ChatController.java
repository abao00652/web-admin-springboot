package com.cuii.controller;

import com.cuii.common.Result;
import com.cuii.entity.SysChatMessage;
import com.cuii.service.SysChatMessageService;
import com.cuii.socket.SocketService;
import com.cuii.vo.CharObjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController {

    @Autowired
    private SysChatMessageService messageService;


    /**
     * 获取和我聊天的对象列表
     */
    @GetMapping("/objects")
    public Result<List<CharObjectVo>> getChatObject(){
        Integer loginId = getLoginId();
        List<CharObjectVo> toChatObject = messageService.getToChatObject(loginId);
        Integer sum = toChatObject.stream().map(e -> e.getUnreadCut()).reduce(0, (a, b) -> a + b);
        return Result.success(toChatObject).setTotal(sum);
    }

    /**
     * 发送消息
     */
    @PostMapping("/send/message")
    public Result<String> sendMessage(Integer toId,String message){
        Integer loginId = getLoginId();
        messageService.initChatUser(loginId,toId);
        SysChatMessage chatMessage = new SysChatMessage();
        chatMessage.setSendId(loginId);
        chatMessage.setToId(toId);
        chatMessage.setContent(message);
        chatMessage.setStatus(0);
        chatMessage.setCreateTime(new Date());
        messageService.save(chatMessage);
        SocketService.sendMessage(loginId,toId,message);
        return Result.success();
    }

    /**
     * 读取对方的所有消息
     */
    @GetMapping("/read/message")
    public Result<String> readMessage(Integer tId){
        Integer loginId = getLoginId();
        messageService.readAllMessage(loginId,tId);
        return Result.success();
    }

    /**
     * 获取消息列表
     */
    @GetMapping("/list/message")
    public Result<List<SysChatMessage>> getChatMessage(Integer tId){
        List<SysChatMessage> list = messageService.getMessageList(getLoginId(),tId);
        return Result.success(list);
    }

}
