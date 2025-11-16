package com.example.javamysql_application_mobile_adoptme.View.ui.solicitud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.View.ui.solicitud.memory.SolicitudDataHolder;

public class solicitud_paso3Fragment extends Fragment {

    private EditText etOtrasMascotas, etExperiencia, etTiempoCuidado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_solicitud_paso3, container, false);


        etOtrasMascotas = view.findViewById(R.id.et_otras_mascotas);
        etExperiencia = view.findViewById(R.id.et_experiencia_previa);
        etTiempoCuidado = view.findViewById(R.id.et_tiempo_cuidado);

        Button btnAnterior = view.findViewById(R.id.btn_anterior);
        Button btnSiguiente = view.findViewById(R.id.btn_siguiente);

        NavController navController =
                Navigation.findNavController(requireActivity(),
                        R.id.nav_host_fragment_activity_users);


        btnAnterior.setOnClickListener(v -> navController.navigate(R.id.action_paso3_to_paso2));


        btnSiguiente.setOnClickListener(v -> {
            guardarDatos();
            navController.navigate(R.id.action_paso3_to_paso4);
        });

        return view;
    }

    private void guardarDatos() {

        // Guardar en memoria temporal
        SolicitudDataHolder.data.setOtrasMascotas(
                etOtrasMascotas.getText().toString().trim()
        );

        SolicitudDataHolder.data.setExperienciaPrev(
                etExperiencia.getText().toString().trim()
        );

        SolicitudDataHolder.data.setTiempoCuidado(
                etTiempoCuidado.getText().toString().trim()
        );
    }
}
