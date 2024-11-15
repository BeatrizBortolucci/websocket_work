package com.websocket.bia;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandlerImpl extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, org.springframework.web.socket.TextMessage message) {
        try {
            // Processa a mensagem recebida via WebSocket
            String receivedMessage = message.getPayload();
            System.out.println("Mensagem recebida: " + receivedMessage);

            // Responde ao cliente
            session.sendMessage(new org.springframework.web.socket.TextMessage("Mensagem recebida: " + receivedMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
