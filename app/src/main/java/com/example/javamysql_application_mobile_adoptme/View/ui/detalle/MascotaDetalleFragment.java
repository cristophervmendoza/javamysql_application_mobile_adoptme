package com.example.javamysql_application_mobile_adoptme.View.ui.detalle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.model.MascotaEntidad;

public class MascotaDetalleFragment extends Fragment {

    private MascotaEntidad mascota;
    private static final String TAG = "DETALLE_FRAG";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {

            mascota = (MascotaEntidad) getArguments().getSerializable("mascota_data");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mascota_detalle, container, false);

        if (mascota != null) {

            ImageView imgDetalleFoto = view.findViewById(R.id.img_detalle_foto);
            TextView tvDetalleNombre = view.findViewById(R.id.tv_detalle_nombre);
            TextView tvDetalleEspecieRaza = view.findViewById(R.id.tv_detalle_especie_raza);
            TextView tvDetalleEdadTamanoSexo = view.findViewById(R.id.tv_detalle_edad_tamano_sexo);
            TextView tvDetalleSalud = view.findViewById(R.id.tv_detalle_salud);
            TextView tvDetalleUbicacion = view.findViewById(R.id.tv_detalle_ubicacion);
            TextView tvDetalleHistoria = view.findViewById(R.id.tv_detalle_historia);
            TextView tvDetalleRequisitos = view.findViewById(R.id.tv_detalle_requisitos);
            Button btnAdoptar = view.findViewById(R.id.btn_solicitar_adopcion);


            tvDetalleNombre.setText(mascota.getNombre());
            tvDetalleEspecieRaza.setText("Especie: " + mascota.getEspecie() + " | Raza: " + mascota.getRaza());
            tvDetalleEdadTamanoSexo.setText("Edad: " + mascota.getEdad() + " | Tamaño: " + mascota.getTamano() + " | Sexo: " + mascota.getSexo());
            tvDetalleSalud.setText("Salud: " + mascota.getEstadoSalud());
            tvDetalleUbicacion.setText("Ubicación: " + mascota.getUbicacion());


            tvDetalleHistoria.setText(mascota.getHistoria());
            tvDetalleRequisitos.setText(mascota.getRequisitos());

            String base64Image = mascota.getFotoPrincipalBase64();
            if (base64Image != null && !base64Image.isEmpty()) {
                try {
                    String cleanBase64 = base64Image.replaceAll("data:image/\\w+;base64,", "").replaceAll("\\s", "");
                    byte[] imageBytes = Base64.decode(cleanBase64, Base64.NO_WRAP);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    imgDetalleFoto.setImageBitmap(decodedImage);
                } catch (Exception e) {
                    Log.e(TAG, "Error al decodificar Base64 en detalle: " + e.getMessage());
                    imgDetalleFoto.setImageResource(R.drawable.ic_placeholder_error);
                }
            } else {
                imgDetalleFoto.setImageResource(R.drawable.ic_placeholder_default);
            }


            btnAdoptar.setOnClickListener(v -> {
                NavController navController =
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_users);

                navController.navigate(R.id.solicitudPaso1Fragment);
            });



        } else {
            Toast.makeText(getContext(), "Error: No se pudo cargar la mascota.", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Mascota data is null in DetalleFragment");
        }

        return view;
    }
}