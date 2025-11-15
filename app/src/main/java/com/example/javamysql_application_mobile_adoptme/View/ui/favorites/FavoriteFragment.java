package com.example.javamysql_application_mobile_adoptme.View.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.adapter.FavoritePetAdapter;
import com.example.javamysql_application_mobile_adoptme.model.FavoriteResponse;
import com.example.javamysql_application_mobile_adoptme.model.MascotaEntidad;
import com.example.javamysql_application_mobile_adoptme.service.AdopcionApiService;
import com.example.javamysql_application_mobile_adoptme.service.ApiCliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerFavorites;
    private LinearLayout emptyState;
    /*private int userId = 24;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerFavorites = view.findViewById(R.id.recycler_favorites);
        emptyState = view.findViewById(R.id.empty_state);

        recyclerFavorites.setLayoutManager(new GridLayoutManager(getContext(), 2));

        cargarFavoritos();

        return view;
    }*/
    private int userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerFavorites = view.findViewById(R.id.recycler_favorites);
        emptyState = view.findViewById(R.id.empty_state);


        SharedPreferences prefs = getActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        userId = prefs.getInt("id_usuario", -1);

        if (userId == -1) {
            Toast.makeText(getContext(), "Error: Usuario no identificado", Toast.LENGTH_SHORT).show();
            return view;
        }


        recyclerFavorites.setLayoutManager(new GridLayoutManager(getContext(), 2));

        cargarFavoritos();

        return view;
    }

    private void cargarFavoritos() {
        AdopcionApiService api = ApiCliente.getApiService();

        api.listarFavoritos(userId).enqueue(new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(@NonNull Call<FavoriteResponse> call,
                                   @NonNull Response<FavoriteResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    List<MascotaEntidad> favoritos = response.body().getData();

                    if (favoritos == null || favoritos.isEmpty()) {
                        emptyState.setVisibility(View.VISIBLE);
                        recyclerFavorites.setVisibility(View.GONE);
                    } else {
                        emptyState.setVisibility(View.GONE);
                        recyclerFavorites.setVisibility(View.VISIBLE);

                        FavoritePetAdapter adapter =
                                new FavoritePetAdapter(favoritos);

                        recyclerFavorites.setAdapter(adapter);
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<FavoriteResponse> call, @NonNull Throwable t) {
                emptyState.setVisibility(View.VISIBLE);
            }
        });
    }
}
