package com.example.javamysql_application_mobile_adoptme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.model.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {


    public interface OnMessageClick {
        void onClick(ChatMessage mensaje);
    }


    private final List<ChatMessage> listaMensajes;
    private final Context context;
    private final int userActual;
    private final OnMessageClick listener;


    public ChatAdapter(Context context, List<ChatMessage> lista, int userActual, OnMessageClick listener) {
        this.context = context;
        this.listaMensajes = lista;
        this.userActual = userActual;
        this.listener = listener;
    }


    @Override
    public int getItemViewType(int position) {
        ChatMessage msg = listaMensajes.get(position);

        return (msg.getIdEmisor() == userActual) ? 1 : 0;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) { // Mensaje Enviado (Derecha)
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_message_right, parent, false);
        } else { // Mensaje Recibido (Izquierda)
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_message_left, parent, false);
        }

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage mensaje = listaMensajes.get(position);
        holder.txtMensaje.setText(mensaje.getContenidoMensaje());



        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(mensaje);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMensaje;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMensaje = itemView.findViewById(R.id.txtMensaje);

        }
    }


    public void agregarMensaje(ChatMessage mensaje) {
        listaMensajes.add(mensaje);
        notifyItemInserted(listaMensajes.size() - 1);
    }
}