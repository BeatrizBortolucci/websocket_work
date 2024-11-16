package com.websocket.bia.websocket;

import org.springframework.beans.factory.annotation.Autowired;

import com.websocket.bia.service.KafkaJavaProducer;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketServer {
     @SuppressWarnings("rawtypes")
    @Autowired
    private KafkaJavaProducer kafkaProducerService;
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Conexão aberta: " + session.getId());
    }

    @OnMessage
    public String onMessage(String message) {
        // Envia a mensagem recebida ao Kafka
        kafkaProducerService.sendMessage(message);

        // Retorna uma resposta para o cliente WebSocket
        return "Mensagem recebida e enviada ao Kafka: " + message;
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Conexão fechada: " + session.getId());
    }
}
