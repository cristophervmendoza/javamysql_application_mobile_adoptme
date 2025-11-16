package com.example.javamysql_application_mobile_adoptme.View.ui.solicitud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.View.ui.solicitud.memory.SolicitudDataHolder;

public class solicitud_paso2Fragment extends Fragment {

    private RadioGroup rgTipoVivienda, rgNinosHogar;
    private EditText etComposicionFamiliar, etAlergias;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_solicitud_paso2, container, false);


        rgTipoVivienda = view.findViewById(R.id.rg_tipo_vivienda);
        rgNinosHogar = view.findViewById(R.id.rg_ninos_hogar);
        etComposicionFamiliar = view.findViewById(R.id.et_composicion_familiar);
        etAlergias = view.findViewById(R.id.et_alergias);

        Button btnAnterior = view.findViewById(R.id.btn_anterior);
        Button btnSiguiente = view.findViewById(R.id.btn_siguiente);

        NavController navController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_users);

        // ⬅ VOLVER
        btnAnterior.setOnClickListener(v ->
                navController.navigate(R.id.action_paso2_to_paso1)
        );

        // ➡ AVANZAR
        btnSiguiente.setOnClickListener(v -> {
            if (validarCampos()) {
                guardarDatos();
                navController.navigate(R.id.action_paso2_to_paso3);
            }
        });

        return view;
    }


    private boolean validarCampos() {

        if (rgTipoVivienda.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Selecciona el tipo de vivienda", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etComposicionFamiliar.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "Describe tu composición familiar", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (rgNinosHogar.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Indica si hay niños en el hogar", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void guardarDatos() {


        RadioButton rbTipoVivienda = requireView().findViewById(rgTipoVivienda.getCheckedRadioButtonId());
        SolicitudDataHolder.data.setTipoVivienda(rbTipoVivienda.getText().toString());


        SolicitudDataHolder.data.setComposicionFamiliar(
                etComposicionFamiliar.getText().toString().trim()
        );


        RadioButton rbNinos = requireView().findViewById(rgNinosHogar.getCheckedRadioButtonId());
        SolicitudDataHolder.data.setNinosEnHogar(rbNinos.getText().toString());


        SolicitudDataHolder.data.setAlergiasAnimales(
                etAlergias.getText().toString().trim()
        );
    }
}
