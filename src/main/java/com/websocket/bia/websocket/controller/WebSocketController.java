package com.websocket.bia.websocket.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/ws")
public class WebSocketController {

    @PostMapping("/sendMessage")
    public void sendWebSocketMessage() {
        // Aqui você vai implementar o envio de mensagens via WebSocket
        // Usando algo como um WebSocketTemplate
    }
}
