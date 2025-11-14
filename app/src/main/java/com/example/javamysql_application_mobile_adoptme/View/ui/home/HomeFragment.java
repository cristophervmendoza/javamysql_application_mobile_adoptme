package com.example.javamysql_application_mobile_adoptme.View.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; // Usamos TextView para el botón "View All"

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation; // Usaremos la versión de androidx.navigation

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.View.ui.account.MapaFragment;
import com.example.javamysql_application_mobile_adoptme.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inicialización de ViewModel y View Binding
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 🗺️ Acción del botón del mapa (tu lógica existente)
        // Asumo que tienes un botón 'btnMap' en tu layout
        // Si no lo tienes, usa root.findViewById(R.id.id_de_tu_mapa_btn)
        if (binding.btnMap != null) {
            binding.btnMap.setOnClickListener(v -> {
                androidx.navigation.Navigation.findNavController(v)
                        .navigate(R.id.navigation_mapa);
            });
        }



        TextView btnViewAll = root.findViewById(R.id.btn_view_all_near);


        if (btnViewAll != null) {
            btnViewAll.setOnClickListener(v -> {

                androidx.navigation.Navigation.findNavController(v)
                        .navigate(R.id.navigation_catalogo);
            });
        }



        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}