package com.example.javamysql_application_mobile_adoptme.View.ui.messages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.adapter.ChatAdapter;
import com.example.javamysql_application_mobile_adoptme.model.BaseResponse;
import com.example.javamysql_application_mobile_adoptme.model.ChatMessage;
import com.example.javamysql_application_mobile_adoptme.model.ChatResponse;
import com.example.javamysql_application_mobile_adoptme.service.ApiCliente;
import com.example.javamysql_application_mobile_adoptme.service.MensajesApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment implements ChatAdapter.OnMessageClick {

    private RecyclerView recyclerChat;
    private EditText txtMensaje;
    private ImageButton btnEnviar;

    private ChatAdapter adapter;
    private List<ChatMessage> listaMensajes = new ArrayList<>();

    private int idUsuarioLogeado;
    private int idReceptor;

    private MensajesApiService apiService;

    public ChatFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerChat = view.findViewById(R.id.recyclerChat);
        txtMensaje = view.findViewById(R.id.edtMensaje);
        btnEnviar = view.findViewById(R.id.btnEnviar);

        recyclerChat.setLayoutManager(new LinearLayoutManager(requireContext()));

        idUsuarioLogeado = getUserSession();   // ← YA FUNCIONA BIEN

        if (getArguments() != null) {
            idReceptor = getArguments().getInt("idReceptor", 0);
        }

        apiService = ApiCliente.getCliente().create(MensajesApiService.class);

        adapter = new ChatAdapter(requireContext(), listaMensajes, idUsuarioLogeado, this);
        recyclerChat.setAdapter(adapter);

        cargarMensajes();
        btnEnviar.setOnClickListener(v -> enviarMensaje());

        return view;
    }

    private int getUserSession() {
        SharedPreferences prefs = requireActivity()
                .getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        return prefs.getInt("id_usuario", -1);
    }

    private void cargarMensajes() {

        Call<ChatResponse> call = apiService.obtenerChat(idUsuarioLogeado);

        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    listaMensajes.clear();
                    listaMensajes.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();

                    if (!listaMensajes.isEmpty()) {
                        recyclerChat.scrollToPosition(listaMensajes.size() - 1);
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al cargar mensajes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enviarMensaje() {

        String contenido = txtMensaje.getText().toString().trim();

        if (contenido.isEmpty()) {
            Toast.makeText(requireContext(), "Escribe un mensaje", Toast.LENGTH_SHORT).show();
            return;
        }

        ChatMessage nuevo = new ChatMessage();
        nuevo.setContenidoMensaje(contenido);
        nuevo.setIdEmisor(idUsuarioLogeado);
        nuevo.setIdReceptor(idReceptor);

        listaMensajes.add(nuevo);
        adapter.notifyItemInserted(listaMensajes.size() - 1);
        recyclerChat.scrollToPosition(listaMensajes.size() - 1);

        txtMensaje.setText("");

        Call<BaseResponse> call = apiService.enviarMensaje(
                idUsuarioLogeado,
                idReceptor,
                contenido
        );

        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    ChatMessage respuestaAutomatica = new ChatMessage();
                    respuestaAutomatica.setContenidoMensaje("Hemos recibido tu mensaje.");
                    respuestaAutomatica.setIdEmisor(1);
                    respuestaAutomatica.setIdReceptor(idUsuarioLogeado);

                    listaMensajes.add(respuestaAutomatica);
                    adapter.notifyItemInserted(listaMensajes.size() - 1);
                    recyclerChat.scrollToPosition(listaMensajes.size() - 1);

                } else {
                    Toast.makeText(requireContext(), "No se pudo enviar el mensaje", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(ChatMessage mensaje) {
        Toast.makeText(requireContext(), "Mensaje: " + mensaje.getIdMensaje(), Toast.LENGTH_SHORT).show();
    }
}
