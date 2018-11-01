package com.example.oscar.enbicia2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clases.MensajeRecibir;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by carlitos on 11/5/17.
 */

public class AdapterMensaje extends RecyclerView.Adapter<HolderMensaje> {

    private List<MensajeRecibir> listaMensajes = new ArrayList<>();
    private Context c;

    public AdapterMensaje(Context c) {
        this.c = c;
    }

    public void addMensaje(MensajeRecibir m) {
        listaMensajes.add(m);
        notifyItemInserted(listaMensajes.size());
    }

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.message_sent,parent,false);
        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        holder.getNombre().setText(listaMensajes.get(position).getMessageText().toString());
        holder.getMensaje().setText(listaMensajes.get(position).getMessageText().toString());
        Date d = new Date(listaMensajes.get(position).getHora());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        holder.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() { return listaMensajes.size(); }
}
