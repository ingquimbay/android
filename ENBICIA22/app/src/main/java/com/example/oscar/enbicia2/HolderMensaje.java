package com.example.oscar.enbicia2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by carlitos on 11/5/17.
 */

public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView mensaje;
    private TextView hora;

    public HolderMensaje(View itemView) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.text_nombre_usuario);
        mensaje = (TextView) itemView.findViewById(R.id.text_mensaje_llega);
        hora = (TextView) itemView.findViewById(R.id.text_tiempo_mensaje);
    }

    public TextView getNombre() { return nombre; }

    public void setNombre(TextView nombre) { this.nombre = nombre; }

    public TextView getMensaje() { return mensaje; }

    public void setMensaje(TextView mensaje) { this.mensaje = mensaje; }

    public TextView getHora() { return hora; }

    public void setHora(TextView hora) { this.hora = hora; }
}

