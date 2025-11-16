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

public class solicitud_paso1Fragment extends Fragment {

    private EditText etNombre, etCorreo, etTelefono, etDireccion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_solicitud_paso1, container, false);

        // 🔹 Referencias a los campos
        etNombre = view.findViewById(R.id.et_nombre_completo);
        etCorreo = view.findViewById(R.id.et_correo_electronico);
        etTelefono = view.findViewById(R.id.et_telefono);
        etDireccion = view.findViewById(R.id.et_direccion);

        Button btnSiguiente = view.findViewById(R.id.btn_siguiente);

        btnSiguiente.setOnClickListener(v -> {

            // 🔹 Guardar la información del Paso 1 en memoria temporal
            SolicitudDataHolder.data.setNombreCompleto(etNombre.getText().toString());
            SolicitudDataHolder.data.setCorreoElectronico(etCorreo.getText().toString());
            SolicitudDataHolder.data.setTelefono(etTelefono.getText().toString());
            SolicitudDataHolder.data.setDireccion(etDireccion.getText().toString());


            NavController navController =
                    Navigation.findNavController(requireActivity(),
                            R.id.nav_host_fragment_activity_users);

            navController.navigate(R.id.action_paso1_to_paso2);
        });

        return view;
    }
}
