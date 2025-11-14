package com.example.javamysql_application_mobile_adoptme.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.model.MascotaEntidad;
import java.util.List;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    // Interfaz para manejar el clic fuera del Adaptador
    private final OnMascotaClickListener clickListener;
    private List<MascotaEntidad> listaMascotas;

    // Constructor que recibe el listener
    public MascotaAdapter(List<MascotaEntidad> listaMascotas, OnMascotaClickListener clickListener) {
        this.listaMascotas = listaMascotas;
        this.clickListener = clickListener;
    }

    public void setMascotas(List<MascotaEntidad> nuevasMascotas) {
        this.listaMascotas = nuevasMascotas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        MascotaEntidad mascota = listaMascotas.get(position);
        holder.bind(mascota);



        holder.btnVerMas.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onMascotaClick(mascota);
            }
        });


        holder.imgFavoriteHeart.setOnClickListener(v -> {
            Log.d("ADAPTER_CLICK", "Clic en Favoritos para Mascota ID: " + mascota.getIdMascota());
        });
    }

    @Override
    public int getItemCount() {
        return listaMascotas != null ? listaMascotas.size() : 0;
    }


    public interface OnMascotaClickListener {
        void onMascotaClick(MascotaEntidad mascota);
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMascotaFoto;
        TextView tvMascotaNombre;
        TextView tvMascotaRazaEdad;
        TextView tvMascotaUbicacionRefugio;

        ImageView imgFavoriteHeart;
        Button btnVerMas;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMascotaFoto = itemView.findViewById(R.id.img_mascota_foto);
            tvMascotaNombre = itemView.findViewById(R.id.tv_mascota_nombre);
            tvMascotaRazaEdad = itemView.findViewById(R.id.tv_mascota_raza_edad);
            tvMascotaUbicacionRefugio = itemView.findViewById(R.id.tv_mascota_ubicacion_refugio);
            imgFavoriteHeart = itemView.findViewById(R.id.img_favorite_heart);
            btnVerMas = itemView.findViewById(R.id.btn_ver_mas);
        }

        public void bind(MascotaEntidad mascota) {
            tvMascotaNombre.setText(mascota.getNombre());
            tvMascotaRazaEdad.setText("Raza: " + mascota.getRaza() + " | Edad: " + mascota.getEdad());
            tvMascotaUbicacionRefugio.setText("Refugio: " + mascota.getNombreRefugio() + " | Ubicación: " + mascota.getUbicacion());

            // --- LÓGICA DE DECODIFICACIÓN DE IMAGEN (Revisión Final) ---
            String base64Image = mascota.getFotoPrincipalBase64();

            if (base64Image != null && !base64Image.isEmpty()) {
                try {
                    String cleanBase64 = base64Image.replaceAll("data:image/\\w+;base64,", "").replaceAll("\\s", "");
                    byte[] imageBytes = Base64.decode(cleanBase64, Base64.NO_WRAP);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                    if (decodedImage != null) {
                        imgMascotaFoto.setImageBitmap(decodedImage);
                    } else {
                        imgMascotaFoto.setImageResource(R.drawable.ic_placeholder_error);
                    }

                } catch (Exception e) {
                    Log.e("MascotaAdapter", "Error al decodificar Base64: " + e.getMessage());
                    imgMascotaFoto.setImageResource(R.drawable.ic_placeholder_error);
                }
            } else {
                imgMascotaFoto.setImageResource(R.drawable.ic_placeholder_default);
            }
        }
    }
}