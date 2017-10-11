package com.forsrc.boot.resource.ws.chat;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatTextWebSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        System.out.println("--> " + message);
        String msg = message.getPayload();
        msg = String.format("\r\n%s\r\n$", msg);
        Iterator<WebSocketSession> it = sessions.iterator();
        while (it.hasNext()) {
            WebSocketSession webSocketSession = it.next();
            if (!webSocketSession.isOpen()) {
                sessions.remove(webSocketSession);
                continue;
            }

            webSocketSession.sendMessage(new TextMessage(msg));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // the messages will be broadcasted to all users.
        sessions.add(session);
        session.sendMessage(new TextMessage("$"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}