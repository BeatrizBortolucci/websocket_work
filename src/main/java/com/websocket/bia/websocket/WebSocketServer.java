package com.websocket.bia.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketServer {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Conexão aberta: " + session.getId());
    }

    @OnMessage
    public String onMessage(String message) {
        return "Mensagem recebida: " + message;
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Conexão fechada: " + session.getId());
    }
}
