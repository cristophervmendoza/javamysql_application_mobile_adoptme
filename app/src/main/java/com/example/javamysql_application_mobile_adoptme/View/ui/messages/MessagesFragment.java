package com.example.javamysql_application_mobile_adoptme.View.ui.messages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.adapter.ChatAdapter;
import com.example.javamysql_application_mobile_adoptme.model.ChatMessage;
import com.example.javamysql_application_mobile_adoptme.model.ChatResponse;
import com.example.javamysql_application_mobile_adoptme.service.ApiCliente;
import com.example.javamysql_application_mobile_adoptme.service.MensajesApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesFragment extends Fragment {

    private RecyclerView recyclerMessages;
    private LinearLayout emptyState;
    private int idUsuarioLogeado;

    public MessagesFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        recyclerMessages = view.findViewById(R.id.recycler_messages);
        emptyState = view.findViewById(R.id.empty_state_messages);

        recyclerMessages.setLayoutManager(new LinearLayoutManager(getContext()));

        idUsuarioLogeado = getUserSession();


        Button btnNuevoMensaje = view.findViewById(R.id.btnNuevoMensaje);
        btnNuevoMensaje.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putInt("idEmisor", idUsuarioLogeado);
            args.putInt("idReceptor", 1);
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_messages_to_chat, args);
        });


        cargarConversaciones();

        return view;
    }


    private int getUserSession() {
        SharedPreferences prefs = requireActivity()
                .getSharedPreferences("UserSession", Context.MODE_PRIVATE);

        return prefs.getInt("id_usuario", -1);
    }

    private void cargarConversaciones() {

        MensajesApiService api = ApiCliente.getCliente().create(MensajesApiService.class);
        Call<ChatResponse> call = api.obtenerChat(idUsuarioLogeado);

        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {

                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    List<ChatMessage> lista = response.body().getData();

                    if (lista.isEmpty()) {
                        emptyState.setVisibility(View.VISIBLE);
                        recyclerMessages.setVisibility(View.GONE);
                    } else {
                        emptyState.setVisibility(View.GONE);
                        recyclerMessages.setVisibility(View.VISIBLE);

                        ChatAdapter adapter =
                                new ChatAdapter(getContext(), lista, idUsuarioLogeado, messageClick);

                        recyclerMessages.setAdapter(adapter);
                    }

                } else {
                    Toast.makeText(getContext(), "Error al cargar mensajes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final ChatAdapter.OnMessageClick messageClick = mensaje -> {

        Bundle args = new Bundle();
        args.putInt("idEmisor", idUsuarioLogeado);
        args.putInt("idReceptor", mensaje.getIdEmisor());

        Navigation.findNavController(requireView())
                .navigate(R.id.action_messages_to_chat, args);

    };
}
