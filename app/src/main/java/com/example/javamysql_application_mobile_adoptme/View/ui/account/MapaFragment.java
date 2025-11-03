package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation; // 🔥 Importante para volver al Home

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.javamysql_application_mobile_adoptme.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);


        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.mapaGoogle);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        ImageButton btnBack = view.findViewById(R.id.btn_back_home);
        btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.navigation_home);
        });

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Surco = new LatLng(-12.103375,-76.9952395);
        mMap.addMarker(new MarkerOptions().position(Surco).title("Centro de adopciones de perros y gatos - Grupo Mozita"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Surco, 14));

        LatLng SJM = new LatLng(-12.1608441,-77.0150642);
        mMap.addMarker(new MarkerOptions().position(SJM).title("albergue Perruno Y Gatuno Tu Mejor Amigo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SJM, 14));

        LatLng Cercado = new LatLng(-12.098855,-76.9165648);
        mMap.addMarker(new MarkerOptions().position(Cercado).title("Huellitas Felices Perú - Albergue animales"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Cercado, 14));

        LatLng Surquillo = new LatLng(-12.1113334,-77.0606206);
        mMap.addMarker(new MarkerOptions().position(Surquillo).title("Salma Asociación Civil"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Surquillo , 14));

        LatLng Pachacamac = new LatLng(-12.1468031,-76.8754791);
        mMap.addMarker(new MarkerOptions().position(Pachacamac).title("Refugio Animazul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Pachacamac, 14));

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        mMap.setOnMapClickListener(latLng ->
                Toast.makeText(requireContext(),
                        "Coordenadas: " + latLng.latitude + ", " + latLng.longitude,
                        Toast.LENGTH_SHORT).show()
        );
    }
}
