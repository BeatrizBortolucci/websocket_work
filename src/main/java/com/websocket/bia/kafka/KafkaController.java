package com.websocket.bia.kafka;

  
    import lombok.RequiredArgsConstructor;

import org.springframework.kafka.event.KafkaEvent;
import org.springframework.web.bind.annotation.*;

import com.websocket.bia.service.KafkaJavaProducer;
    
    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/bibia")
    public class KafkaController {
        @SuppressWarnings("rawtypes")
        private final KafkaJavaProducer producer;
    
        @GetMapping
        public String beatrizDizOi() {
            return "Olá, sou a Beatriz Bortolucci Domingos";
        }
    
        @SuppressWarnings("unchecked")
        @PostMapping
        public void beatrizMandaMsg(@RequestBody final KafkaEvent event) {
            producer.send("cons_antifraude", event.getClass());
        }
    }