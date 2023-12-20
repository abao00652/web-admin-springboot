package com.cuii.socket;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.cuii.service.SysChatMessageService;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/im/chat/{token}")
@Slf4j
public class SocketService {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    /**
     * 用户ID
     */
    private String userId;

    // 用来存在线连接用户信息
    private static ConcurrentHashMap<String,Session> sessionPool = new ConcurrentHashMap<>();
    private static SysChatMessageService chatMessageService;

    @Autowired
    public void setChatMessageService(SysChatMessageService chatMessageService){
        this.chatMessageService = chatMessageService;
    }


    /**
     * 链接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value="token")String token) {
        try {
            userId = (String) StpUtil.getLoginIdByToken(token);
            if (userId != null){
                log.info("webSocket链接成功:{}",userId);
                sessionPool.put(userId,session);
                session.getAsyncRemote().sendText("你好呀，成功建立链接");
            }else {
                log.info("webSocket链接失败!");
                session.close();
            }
            log.info("依赖注入是否成功! {}",chatMessageService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("用户{}掉线了",userId);
        sessionPool.remove(userId);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("接受到消息:{}",message);
    }

    /** 发送错误时的处理
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

    }

    /**
     * 发送消息
     */
    public static void sendMessage(Integer sendId,Integer toId,String message){
        if (toId == null){
            return;
        }
        if (sessionPool.contains(toId.toString())) {
            ChatMessageContext context = new ChatMessageContext();
            context.setSendId(sendId);
            context.setContext(message);
            sessionPool.get(toId.toString()).getAsyncRemote().sendText(JSONObject.toJSONString(context));
        }
    }

}
