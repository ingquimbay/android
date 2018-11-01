package com.example.clases;

import java.util.Date;
import java.util.Map;

/**
 * Created by carlitos on 11/5/17.
 */

public class MensajeRecibir extends ChatMessage {

    private Long hora;

    public MensajeRecibir(String messageText, String sender, Map<String, String> timestamp) {    }

    public MensajeRecibir(Long hora) { this.hora = hora; }

    public MensajeRecibir(String messageText, String messageUser, Long hora) {
        super(messageText, messageUser);
        this.hora = hora;
    }

    public Long getHora() { return hora; }

    public void setHora(Long hora) { this.hora = hora; }
}
