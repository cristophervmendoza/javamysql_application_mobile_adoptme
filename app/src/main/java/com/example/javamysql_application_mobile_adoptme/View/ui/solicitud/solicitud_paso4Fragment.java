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

public class solicitud_paso4Fragment extends Fragment {

    private EditText etMotivo, etPlanCuidado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_solicitud_paso4, container, false);

        etMotivo = view.findViewById(R.id.et_motivo_adopcion);
        etPlanCuidado = view.findViewById(R.id.et_plan_cuidado);

        Button btnAnterior = view.findViewById(R.id.btn_anterior);
        Button btnSiguiente = view.findViewById(R.id.btn_siguiente);

        NavController navController =
                Navigation.findNavController(requireActivity(),
                        R.id.nav_host_fragment_activity_users);


        btnAnterior.setOnClickListener(v -> navController.navigate(R.id.action_paso4_to_paso3));


        btnSiguiente.setOnClickListener(v -> {
            guardarDatos();
            navController.navigate(R.id.action_paso4_to_paso5);
        });

        return view;
    }

    private void guardarDatos() {
        SolicitudDataHolder.data.setMotivoAdopcion(
                etMotivo.getText().toString().trim()
        );

        SolicitudDataHolder.data.setPlanCuidado(
                etPlanCuidado.getText().toString().trim()
        );
    }
}
