package com.mybookstore.mybookstorebackend.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{userid}")
@Component
public class WebSocketServer {

    //实例一个session，这个session是websocket的session
    private Session session;

    //存放websocket的集合
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    //接收sid
    private String userid = "";

    //前端请求时一个websocket时
    @OnOpen
    public void onOpen(Session session, @PathParam("userid") String userid) {
        this.session = session;
        webSocketSet.add(this);
        this.userid = userid;
        System.out.println("【websocket消息】有新的连接 uid "+ userid +", 总数: " + webSocketSet.size());
    }

    //前端关闭时一个websocket时
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("【websocket消息】连接断开 uid " + this.userid + ", 总数: "+ webSocketSet.size());
    }

    //前端向后端发送消息
    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端 uid " + this.userid + " 发来的消息: " + message);
    }

    //新增一个方法用于主动向客户端发送消息
    public void sendMessage(String message, String uid) {
        for (WebSocketServer webSocket: webSocketSet) {
            try {
                if(webSocket.userid.equals(uid)){
                    System.out.println("【websocket消息】向客户端 uid " + uid + " 发送消息: "+ message);
                    webSocket.session.getBasicRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
