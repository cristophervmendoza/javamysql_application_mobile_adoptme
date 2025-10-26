package com.example.javamysql_application_mobile_adoptme.Auth.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;
import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.View.UsersActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Referencias
        TextInputEditText nameInput = view.findViewById(R.id.name_input);
        TextInputEditText lastnameInput = view.findViewById(R.id.lastname_input);
        TextInputEditText dniInput = view.findViewById(R.id.dni_input);
        TextInputEditText emailInput = view.findViewById(R.id.email_input);
        TextInputEditText phoneInput = view.findViewById(R.id.phone_input);
        TextInputEditText passwordInput = view.findViewById(R.id.password_input);
        TextInputEditText confirmPasswordInput = view.findViewById(R.id.confirm_password_input);
        CheckBox termsCheckbox = view.findViewById(R.id.terms_checkbox);
        Button btnRegister = view.findViewById(R.id.btn_signup);
        TextView loginLink = view.findViewById(R.id.login_link);

        // Botón Sign Up
        btnRegister.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String lastname = lastnameInput.getText().toString().trim();
            String dni = dniInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            // Validaciones
            if (name.isEmpty() || lastname.isEmpty() || dni.isEmpty() ||
                    email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(getContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!termsCheckbox.isChecked()) {
                Toast.makeText(getContext(), "Please accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "Registration successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), UsersActivity.class);
            startActivity(intent);
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        // Link "Log In" → volver a LoginFragment
        loginLink.setOnClickListener(v -> {
            ((AuthActivity) getActivity()).loadFragment(new LoginFragment(), false);
        });

        // **IMPORTANTE: Controlar el botón Back**
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Volver a StartUpFragment
                ((AuthActivity) requireActivity()).loadFragment(new StartUpFragment(), false);
            }
        });

        return view;
    }
}
