package com.example.javamysql_application_mobile_adoptme.adapter;

import android.content.Context;
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
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.model.MascotaEntidad;
import com.example.javamysql_application_mobile_adoptme.model.BaseResponse;
import com.example.javamysql_application_mobile_adoptme.service.AdopcionApiService;
import com.example.javamysql_application_mobile_adoptme.service.ApiCliente;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private final OnMascotaClickListener clickListener;
    private List<MascotaEntidad> listaMascotas;
    private final Context context;
    private final int userId;


    public interface OnMascotaClickListener {
        void onMascotaClick(MascotaEntidad mascota);
    }


    public MascotaAdapter(Context context, List<MascotaEntidad> listaMascotas, OnMascotaClickListener clickListener, int userId) {
        this.context = context;
        this.listaMascotas = listaMascotas;
        this.clickListener = clickListener;
        this.userId = userId;
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
            toggleFavorite(mascota.getIdMascota(), holder.imgFavoriteHeart);
        });


        holder.imgFavoriteHeart.setImageResource(R.drawable.ic_heart_outline);
        holder.imgFavoriteHeart.setColorFilter(ContextCompat.getColor(context, android.R.color.white));
    }

    @Override
    public int getItemCount() {
        return listaMascotas != null ? listaMascotas.size() : 0;
    }


    private void toggleFavorite(int mascotaId, ImageView heartIcon) {
        Log.d("FAV_DEBUG", "User ID used: " + userId + ", Mascota ID: " + mascotaId);
        if (userId <= 0) {
            Toast.makeText(context, "Debes iniciar sesión para añadir favoritos.", Toast.LENGTH_SHORT).show();
            return;
        }

        AdopcionApiService service = ApiCliente.getApiService();
        service.toggleFavorite(userId, mascotaId).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse> call, @NonNull Response<BaseResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BaseResponse body = response.body();
                    if (body.isSuccess()) {
                        String estado = body.getMensaje();
                        Toast.makeText(context, "Favorito: " + estado, Toast.LENGTH_SHORT).show();


                        if ("Agregado".equals(estado)) {

                            heartIcon.setImageResource(R.drawable.ic_heart_filled);
                            heartIcon.setColorFilter(ContextCompat.getColor(context, android.R.color.holo_red_dark));
                        } else {
                            // Cambiar a corazón vacío (blanco)
                            heartIcon.setImageResource(R.drawable.ic_heart_outline);
                            heartIcon.setColorFilter(ContextCompat.getColor(context, android.R.color.white));
                        }
                    } else {
                        Toast.makeText(context, "Error al actualizar favorito: " + body.getMensaje(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Fallo en el servidor al añadir favorito.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse> call, @NonNull Throwable t) {
                Log.e("FAVORITOS_API", "Error de red: " + t.getMessage());
                Toast.makeText(context, "Error de red al intentar añadir favorito.", Toast.LENGTH_LONG).show();
            }
        });
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
                    Log.e("ADAPTER_IMAGE", "Error al decodificar Base64: " + e.getMessage());
                    imgMascotaFoto.setImageResource(R.drawable.ic_placeholder_error);
                }
            } else {
                imgMascotaFoto.setImageResource(R.drawable.ic_placeholder_default);
            }
        }
    }
}