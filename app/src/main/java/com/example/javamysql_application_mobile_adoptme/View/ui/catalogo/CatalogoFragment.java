package com.example.javamysql_application_mobile_adoptme.View.ui.catalogo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.navigation.Navigation;
import androidx.core.os.BundleKt;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.adapter.MascotaAdapter;
import com.example.javamysql_application_mobile_adoptme.model.CatalogoRespuesta;
import com.example.javamysql_application_mobile_adoptme.model.MascotaEntidad;
import com.example.javamysql_application_mobile_adoptme.service.AdopcionApiService;
import com.example.javamysql_application_mobile_adoptme.service.ApiCliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CatalogoFragment extends Fragment implements MascotaAdapter.OnMascotaClickListener {

    private static final String TAG = "CATALOGO_FRAG";
    private RecyclerView recyclerView;
    private MascotaAdapter adapter;

    public CatalogoFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_catalogo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recycler_view_catalogo);


        adapter = new MascotaAdapter(new ArrayList<>(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // 2. Ejecutar la llamada a la API
        cargarCatalogoMascotas();
    }

    private void cargarCatalogoMascotas() {
        // ... (Tu lógica de API idéntica a la anterior) ...
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

                    } else {
                        Log.w(TAG, "Error de la API: " + body.getMensaje());
                    }
                } else {
                    Log.e(TAG, "Fallo HTTP: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CatalogoRespuesta> call, @NonNull Throwable t) {
                Log.e(TAG, "Error de conexión/red: " + t.getMessage(), t);
            }
        });
    }


    @Override
    public void onMascotaClick(MascotaEntidad mascota) {

        Bundle bundle = new Bundle();


        bundle.putSerializable("mascota_data", mascota);


        try {
            Navigation.findNavController(requireView()).navigate(R.id.navigation_mascota_detalle, bundle);
        } catch (Exception e) {
            Log.e(TAG, "Error de navegación: El destino navigation_mascota_detalle no está definido.", e);
            Toast.makeText(getContext(), "Error: Destino de detalle no encontrado.", Toast.LENGTH_LONG).show();
        }
    }
}