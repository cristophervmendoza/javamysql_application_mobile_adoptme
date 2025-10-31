package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.R;

public class AccountProfileFragment extends Fragment {

    EditText input_full_name, input_email, input_phone;
    TextView input_gender, input_birthdate;
    ImageView profile_image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_profile, container, false);

        input_full_name = view.findViewById(R.id.input_full_name);
        input_email = view.findViewById(R.id.input_email);
        input_phone = view.findViewById(R.id.input_phone);
        input_gender = view.findViewById(R.id.input_gender);
        input_birthdate = view.findViewById(R.id.input_birthdate);
        profile_image = view.findViewById(R.id.profile_image);

        cargarDatosUsuario();

        return view;
    }

    private void cargarDatosUsuario() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("UserSession", getContext().MODE_PRIVATE);

        String nombre = prefs.getString("nombre", "");
        String apellido = prefs.getString("apellido", "");
        String correo = prefs.getString("correo", "");
        String telefono = prefs.getString("telefono", "");

        input_full_name.setText(nombre + " " + apellido);
        input_email.setText(correo);
        input_phone.setText(telefono);


        input_gender.setText("Not specified");
        input_birthdate.setText("N/A");
    }


}
