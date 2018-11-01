package com.android.carlitos.chattychat;

import java.util.Map;

/**
 * Created by carlitos on 11/6/17.
 */

public class messageOut extends message {

    private Map hora;

    public messageOut() {  }

    public messageOut(String messageText, String messageUser, Map hora) {
        super(messageText, messageUser);
        this.hora = hora;
    }

    public Map getHora() { return hora; }

    public void setHora(Map hora) { this.hora = hora; }
}
