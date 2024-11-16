package com.websocket.bia.service;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaJavaProducer<T> {
    private Producer<String, T> producer;

    public KafkaJavaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        this.producer = new KafkaProducer<String, T>(props);
    }

    public void send(String topic, T message) {
        ProducerRecord<String, T> record = new ProducerRecord<>(topic, message);
        producer.send(record);
    }

    public void sendMessage(String message) {
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }
}

