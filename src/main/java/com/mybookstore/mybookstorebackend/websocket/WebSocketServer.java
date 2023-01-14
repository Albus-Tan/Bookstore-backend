package com.mybookstore.mybookstorebackend.websocket;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {

    public WebSocketServer() {
        //每当有一个连接，都会执行一次构造方法
        System.out.println("[WebSocketServer] 新的连接");
    }

    private static final AtomicInteger COUNT = new AtomicInteger();

    // uid 与对应的 Session
    private static final ConcurrentHashMap<String, Session> SESSIONS = new ConcurrentHashMap<>();

    public void sendMessage(Session toSession, String message) {
        if (toSession != null) {
            try {
                toSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("[WebSocketServer] 对方不在线");
        }
    }

    public void sendMessageToUser(String uid, String message) {
        System.out.println("[WebSocketServer] 准备向 uid 为 " + uid + " 的用户发送消息："+ message);
        Session toSession = SESSIONS.get(uid);
        sendMessage(toSession, message);
    }


    @OnMessage
    public void onMessage(String message) {
        System.out.println("[WebSocketServer] 服务器收到消息：" + message);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        if (SESSIONS.get(userId) != null) {
            return;
        }
        SESSIONS.put(userId, session);
        COUNT.incrementAndGet();
        System.out.println("[WebSocketServer] uid 为 " + userId + " 的用户上线了，当前在线人数：" + COUNT);

    }

    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        SESSIONS.remove(userId);
        COUNT.decrementAndGet();
        System.out.println("[WebSocketServer] uid 为 " + userId + " 的用户下线了，当前在线人数：" + COUNT);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("[WebSocketServer] 发生错误");
        throwable.printStackTrace();
    }
}



//
//import org.springframework.stereotype.Component;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//@ServerEndpoint("/websocket/{userid}")
//@Component
//public class WebSocketServer {
//
//    //实例一个session，这个session是websocket的session
//    private Session session;
//
//    //存放websocket的集合
//    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
//
//    //接收sid
//    private String userid = "";
//
//    //前端请求时一个websocket时
//    @OnOpen
//    public void onOpen(Session session, @PathParam("userid") String userid) {
//        this.session = session;
//        webSocketSet.add(this);
//        this.userid = userid;
//        System.out.println("【websocket消息】有新的连接 uid "+ userid +", 总数: " + webSocketSet.size());
//    }
//
//    //前端关闭时一个websocket时
//    @OnClose
//    public void onClose() {
//        webSocketSet.remove(this);
//        System.out.println("【websocket消息】连接断开 uid " + this.userid + ", 总数: "+ webSocketSet.size());
//    }
//
//    //前端向后端发送消息
//    @OnMessage
//    public void onMessage(String message) {
//        System.out.println("【websocket消息】收到客户端 uid " + this.userid + " 发来的消息: " + message);
//    }
//
//    //新增一个方法用于主动向客户端发送消息
//    public void sendMessage(String message, String uid) {
//        for (WebSocketServer webSocket: webSocketSet) {
//            try {
//                if(webSocket.userid.equals(uid)){
//                    System.out.println("【websocket消息】向客户端 uid " + uid + " 发送消息: "+ message);
//                    webSocket.session.getBasicRemote().sendText(message);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
