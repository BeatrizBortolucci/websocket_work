package com.websocket.bia.service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.websocket.bia.kafka.ClientRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ClientRepository clientRepository;
    @SuppressWarnings("rawtypes")
    private final KafkaJavaProducer producer;

    public boolean isCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0;
            int peso = 10;

            for (int i = 0; i < 9; i++) {
                int num = Integer.parseInt(cpf.substring(i, i + 1));
                soma += num * peso--;
            }

            int resto = 11 - (soma % 11);
            char digito1 = (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');

            soma = 0;
            peso = 11;

            for (int i = 0; i < 10; i++) {
                int num = Integer.parseInt(cpf.substring(i, i + 1));
                soma += num * peso--;
            }

            resto = 11 - (soma % 11);
            char digito2 = (resto == 10 || resto == 11) ? '0' : (char) (resto + '0');

            return digito1 == cpf.charAt(9) && digito2 == cpf.charAt(10);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @KafkaListener(topics = "cons_antifraude", groupId = "group_id")
    public void consume(String cpf) {
        var isCpfValid = this.isCpf(cpf);
        var response = false;

        if(isCpfValid) {
            var client = clientRepository.findClientByCpfCli(cpf);
            System.out.println(client);
            if(client.isPresent()) {
                System.out.println(client.get());
                if(client.get().getTrustedCli()) {
                    response = true;
                }
            }
        }

        producer.send("vered_antifraude", String.valueOf(response));
    }
}