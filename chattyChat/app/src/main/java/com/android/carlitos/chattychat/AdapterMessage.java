package com.android.carlitos.chattychat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by carlitos on 11/6/17.
 */

public class AdapterMessage extends RecyclerView.Adapter<HolderMessage> {

    private List<messageIn> listaMensajes = new ArrayList<>();
    private Context c;

    public AdapterMessage(Context c) {
        this.c = c;
    }

    public void addMensajeIn(messageIn m) {
        listaMensajes.add(m);
        notifyItemInserted(listaMensajes.size());
    }

    @Override
    public HolderMessage onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.incoming_message,parent,false);
        return new HolderMessage(v);
    }

    @Override
    public void onBindViewHolder(HolderMessage holder, int position) {
        holder.getNombre().setText(listaMensajes.get(position).getMessageUser().toString());
        holder.getMensaje().setText(listaMensajes.get(position).getMessageText().toString());
        Date d = new Date(listaMensajes.get(position).getHora());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        holder.getHora().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() { return listaMensajes.size(); }

}
