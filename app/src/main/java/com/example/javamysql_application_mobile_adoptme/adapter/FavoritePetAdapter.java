package com.example.javamysql_application_mobile_adoptme.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;   // ← ESTE ERA EL IMPORT QUE FALTABA

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.model.MascotaEntidad;

import java.util.List;

public class FavoritePetAdapter extends RecyclerView.Adapter<FavoritePetAdapter.FavoriteViewHolder> {

    private List<MascotaEntidad> listaFavoritos;

    public FavoritePetAdapter(List<MascotaEntidad> listaFavoritos) {
        this.listaFavoritos = listaFavoritos;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_pet, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(listaFavoritos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaFavoritos != null ? listaFavoritos.size() : 0;
    }


    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPet;
        TextView tvName, tvAge;
        ImageButton btnFavorite;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPet = itemView.findViewById(R.id.img_pet);
            tvName = itemView.findViewById(R.id.tv_pet_name);
            tvAge = itemView.findViewById(R.id.tv_pet_age);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
        }

        public void bind(MascotaEntidad mascota) {

            tvName.setText(mascota.getNombre());
            tvAge.setText("Edad: " + mascota.getEdad());

            String base64Img = mascota.getFotoPrincipalBase64();

            if (base64Img != null && !base64Img.isEmpty()) {
                try {
                    byte[] bytes = Base64.decode(base64Img, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imgPet.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Log.e("FAVORITOS", "Error decodificando imagen: " + e.getMessage());
                    imgPet.setImageResource(R.drawable.ic_placeholder_default);
                }
            } else {
                imgPet.setImageResource(R.drawable.ic_placeholder_default);
            }
        }
    }
}
