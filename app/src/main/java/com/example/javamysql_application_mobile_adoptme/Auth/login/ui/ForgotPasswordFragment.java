package com.example.javamysql_application_mobile_adoptme.Auth.login.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;
import com.example.javamysql_application_mobile_adoptme.Auth.login.ui.VerifyCodeRegFragment;
import com.example.javamysql_application_mobile_adoptme.R;

public class ForgotPasswordFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        // Referencias
        ImageButton btnBack = view.findViewById(R.id.btn_back);
        EditText emailInput = view.findViewById(R.id.email_input);
        AppCompatButton btnSendOtp = view.findViewById(R.id.btn_send_otp);

        // Botón Atrás
        btnBack.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        // Botón Send OTP
        btnSendOtp.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí envías el OTP por email (backend)
            // Por ahora solo navegamos a VerifyCodeFragment
            Toast.makeText(getContext(), "OTP sent to " + email, Toast.LENGTH_SHORT).show();

            // Navegar a VerifyCodeFragment
            ((AuthActivity) getActivity()).loadFragment(new VerifyCodeRegFragment(), true);
        });

        return view;
    }
}
