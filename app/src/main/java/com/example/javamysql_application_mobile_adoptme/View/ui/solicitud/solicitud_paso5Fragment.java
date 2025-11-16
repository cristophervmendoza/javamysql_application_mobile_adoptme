package com.example.javamysql_application_mobile_adoptme.View.ui.solicitud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.View.ui.solicitud.memory.SolicitudDataHolder;
import com.example.javamysql_application_mobile_adoptme.model.BaseResponse;
import com.example.javamysql_application_mobile_adoptme.service.ApiCliente;
import com.example.javamysql_application_mobile_adoptme.service.AdopcionApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class solicitud_paso5Fragment extends Fragment {

    private TextView tvNombre, tvCorreo, tvTelefono, tvVivienda;
    private CheckBox cbTerminos, cbVeracidad;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_solicitud_paso5, container, false);

        tvNombre = view.findViewById(R.id.tv_resumen_nombre);
        tvCorreo = view.findViewById(R.id.tv_resumen_correo);
        tvTelefono = view.findViewById(R.id.tv_resumen_telefono);
        tvVivienda = view.findViewById(R.id.tv_resumen_vivienda);

        cbTerminos = view.findViewById(R.id.cb_acepta_terminos);
        cbVeracidad = view.findViewById(R.id.cb_confirma_veracidad);

        Button btnAnterior = view.findViewById(R.id.btn_anterior);
        Button btnConfirmar = view.findViewById(R.id.btn_confirmar);

        NavController navController =
                Navigation.findNavController(requireActivity(),
                        R.id.nav_host_fragment_activity_users);

        btnAnterior.setOnClickListener(v -> navController.navigate(R.id.action_paso5_to_paso4));

        cargarResumen();

        btnConfirmar.setOnClickListener(v -> {
            if (!cbTerminos.isChecked()) {
                Toast.makeText(getContext(),
                        "Debes aceptar los términos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!cbVeracidad.isChecked()) {
                Toast.makeText(getContext(),
                        "Debes confirmar la veracidad", Toast.LENGTH_SHORT).show();
                return;
            }


            SolicitudDataHolder.data.setAceptaTerminos(true);
            SolicitudDataHolder.data.setConfirmaVeracidad(true);


            enviarSolicitud();
        });


        return view;
    }

    private void cargarResumen() {
        tvNombre.setText(SolicitudDataHolder.data.getNombreCompleto());
        tvCorreo.setText(SolicitudDataHolder.data.getCorreoElectronico());
        tvTelefono.setText(SolicitudDataHolder.data.getTelefono());
        tvVivienda.setText(SolicitudDataHolder.data.getTipoVivienda());
    }

    private void confirmarSolicitud() {

        if (!cbTerminos.isChecked()) {
            Toast.makeText(getContext(), "Debes aceptar los términos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!cbVeracidad.isChecked()) {
            Toast.makeText(getContext(), "Debes confirmar veracidad", Toast.LENGTH_SHORT).show();
            return;
        }

        SolicitudDataHolder.data.setAceptaTerminos(true);
        SolicitudDataHolder.data.setConfirmaVeracidad(true);

        enviarSolicitud();
    }

    private void enviarSolicitud() {

        AdopcionApiService api =
                ApiCliente.getCliente().create(AdopcionApiService.class);
        int acepta = SolicitudDataHolder.data.isAceptaTerminos() ? 1 : 0;
        int verifica = SolicitudDataHolder.data.isConfirmaVeracidad() ? 1 : 0;


        Call<BaseResponse> call = api.enviarSolicitud(
                SolicitudDataHolder.idUsuario,
                SolicitudDataHolder.idMascota,
                SolicitudDataHolder.data.getNombreCompleto(),
                SolicitudDataHolder.data.getCorreoElectronico(),
                SolicitudDataHolder.data.getTelefono(),
                SolicitudDataHolder.data.getDireccion(),
                SolicitudDataHolder.data.getTipoVivienda(),
                SolicitudDataHolder.data.getComposicionFamiliar(),
                SolicitudDataHolder.data.getNinosEnHogar(),
                SolicitudDataHolder.data.getAlergiasAnimales(),
                SolicitudDataHolder.data.getOtrasMascotas(),
                SolicitudDataHolder.data.getExperienciaPrev(),
                SolicitudDataHolder.data.getTiempoCuidado(),
                SolicitudDataHolder.data.getMotivoAdopcion(),
                SolicitudDataHolder.data.getPlanCuidado(),
                acepta,
                verifica
        );


        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), "Solicitud enviada correctamente",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Error al enviar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
