package com.websocket.bia.kafka;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "clientes")
public class Client {
    @Id
    Long id;
    
    String cpfCli;
    String nomeCli;
    String emailCli;

    String celularCli;
    String endResCli;
    String senhaCli;
    Date dataCadastroCli;
    Date niverCli;
    Boolean trustedCli;
}