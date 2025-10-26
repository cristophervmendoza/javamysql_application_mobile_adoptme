package com.example.javamysql_application_mobile_adoptme.Auth.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;
import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.View.UsersActivity;

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button btnLogin = view.findViewById(R.id.btn_login);
        TextView forgotPassword = view.findViewById(R.id.forgot_password);
        TextView registerLink = view.findViewById(R.id.register_link);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UsersActivity.class);
            startActivity(intent);
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        forgotPassword.setOnClickListener(v -> {
            ((AuthActivity) getActivity()).loadFragment(new ForgotPasswordFragment(), true);
        });

        registerLink.setOnClickListener(v -> {
            ((AuthActivity) getActivity()).loadFragment(new RegisterFragment(), false);
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((AuthActivity) requireActivity()).loadFragment(new StartUpFragment(), false);
            }
        });

        return view;
    }
}
