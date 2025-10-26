package com.example.javamysql_application_mobile_adoptme.Auth.login.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;
import com.example.javamysql_application_mobile_adoptme.R;

public class StartUpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_up, container, false);

        // Referencias a los botones
        AppCompatButton loginButton = view.findViewById(R.id.login_button);
        AppCompatButton signupButton = view.findViewById(R.id.signup_button);

        // Click listener para Login
        loginButton.setOnClickListener(v -> {
            ((AuthActivity) getActivity()).loadFragment(new LoginFragment(), true);
        });

        // Click listener para Sign Up
        signupButton.setOnClickListener(v -> {
            ((AuthActivity) getActivity()).loadFragment(new RegisterFragment(), true);
        });

        return view;
    }
}
