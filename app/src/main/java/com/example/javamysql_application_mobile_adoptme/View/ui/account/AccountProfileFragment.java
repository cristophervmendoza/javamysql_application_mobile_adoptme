package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.R;

public class AccountProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_profile, container, false);

        // Botón Back
        ImageButton btnBack = root.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            // Volver al AccountFragment
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // Aquí puedes agregar lógica para cargar y editar los datos del perfil
        // EditText inputFullName = root.findViewById(R.id.input_full_name);
        // inputFullName.setText(userData.getName());

        // EditText inputEmail = root.findViewById(R.id.input_email);
        // inputEmail.setText(userData.getEmail());
        // etc.

        return root;
    }
}
