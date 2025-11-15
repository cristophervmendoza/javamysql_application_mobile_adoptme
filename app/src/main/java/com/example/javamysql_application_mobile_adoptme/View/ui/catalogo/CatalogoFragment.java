package com.example.javamysql_application_mobile_adoptme.View.ui.catalogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.navigation.Navigation; // Para navegar
import androidx.navigation.fragment.NavHostFragment;


import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.adapter.MascotaAdapter;
import com.example.javamysql_application_mobile_adoptme.model.CatalogoRespuesta;
import com.example.javamysql_application_mobile_adoptme.model.MascotaEntidad;
import com.example.javamysql_application_mobile_adoptme.service.AdopcionApiService;
import com.example.javamysql_application_mobile_adoptme.service.ApiCliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class CatalogoFragment extends Fragment implements MascotaAdapter.OnMascotaClickListener {

    private static final String TAG = "CATALOGO_FRAG";
    private RecyclerView recyclerView;
    private MascotaAdapter adapter;
    private int currentUserId = -1;
    public CatalogoFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_catalogo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedPreferences prefs = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        currentUserId = prefs.getInt("id_usuario", -1);


        recyclerView = view.findViewById(R.id.recycler_view_catalogo);


        adapter = new MascotaAdapter(getContext(), new ArrayList<>(), this, currentUserId);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerView.setAdapter(adapter);


        cargarCatalogoMascotas();
    }


    @Override
    public void onMascotaClick(MascotaEntidad mascota) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("mascota_data", mascota);


        NavHostFragment.findNavController(this).navigate(R.id.navigation_mascota_detalle, bundle);
    }


    private void cargarCatalogoMascotas() {
        AdopcionApiService apiService = ApiCliente.getApiService();
        Call<CatalogoRespuesta> call = apiService.getCatalogoMascotas();

        call.enqueue(new Callback<CatalogoRespuesta>() {
            @Override
            public void onResponse(@NonNull Call<CatalogoRespuesta> call, @NonNull Response<CatalogoRespuesta> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CatalogoRespuesta body = response.body();

                    if (body.isSuccess() && body.getListaMascotas() != null) {
                        List<MascotaEntidad> mascotas = body.getListaMascotas();

                        adapter.setMascotas(mascotas);
                        Log.i(TAG, "Catálogo cargado. Total: " + mascotas.size());

                        if (mascotas.isEmpty()) {
                            Toast.makeText(getContext(), "No hay mascotas disponibles.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.w(TAG, "Error de API: " + body.getMensaje());
                        Toast.makeText(getContext(), "Error al cargar la lista: " + body.getMensaje(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.e(TAG, "Fallo HTTP: " + response.code());
                    Toast.makeText(getContext(), "Fallo al conectar con el servidor.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CatalogoRespuesta> call, @NonNull Throwable t) {
                Log.e(TAG, "Error de conexión/red: " + t.getMessage(), t);
                Toast.makeText(getContext(), "Error de red: No se pudo cargar el catálogo.", Toast.LENGTH_LONG).show();
            }
        });
    }
}