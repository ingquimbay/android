package com.android.carlitos.chattychat;

/**
 * Created by carlitos on 11/6/17.
 */

public class messageIn extends message {

    private Long hora;

    public messageIn() {  }


    public messageIn(Long hora) { this.hora = hora; }

    public messageIn(String messageText, String messageUser, Long hora) {
        super(messageText, messageUser);
        this.hora = hora;
    }

    public Long getHora() { return hora; }

    public void setHora(Long hora) { this.hora = hora; }
}
