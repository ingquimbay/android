package com.android.carlitos.chattychat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by carlitos on 11/6/17.
 */

public class HolderMessage extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView mensaje;
    private TextView hora;

    public HolderMessage(View itemView) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.text_message_name);
        mensaje = (TextView) itemView.findViewById(R.id.text_message_body);
        hora = (TextView) itemView.findViewById(R.id.text_message_time);
    }

    public TextView getNombre() { return nombre; }

    public void setNombre(TextView nombre) { this.nombre = nombre; }

    public TextView getMensaje() { return mensaje; }

    public void setMensaje(TextView mensaje) { this.mensaje = mensaje; }

    public TextView getHora() { return hora; }

    public void setHora(TextView hora) { this.hora = hora; }
}
