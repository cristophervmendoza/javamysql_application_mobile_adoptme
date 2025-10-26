package com.example.javamysql_application_mobile_adoptme.Auth.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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

import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.View.UsersActivity;

public class ResetPasswordFragment extends Fragment {

    private EditText newPasswordInput;
    private EditText confirmPasswordInput;
    private ImageButton toggleNewPassword;
    private ImageButton toggleConfirmPassword;
    private boolean isNewPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        // Referencias
        ImageButton btnBack = view.findViewById(R.id.btn_back);
        newPasswordInput = view.findViewById(R.id.new_password_input);
        confirmPasswordInput = view.findViewById(R.id.confirm_password_input);
        toggleNewPassword = view.findViewById(R.id.toggle_new_password);
        toggleConfirmPassword = view.findViewById(R.id.toggle_confirm_password);
        AppCompatButton btnSavePassword = view.findViewById(R.id.btn_save_password);

        // Botón Atrás
        btnBack.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        // Toggle visibility New Password
        toggleNewPassword.setOnClickListener(v -> {
            isNewPasswordVisible = !isNewPasswordVisible;
            if (isNewPasswordVisible) {
                newPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT);
                toggleNewPassword.setImageResource(R.drawable.ic_visibility);
            } else {
                newPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                toggleNewPassword.setImageResource(R.drawable.ic_visibility_off);
            }
            newPasswordInput.setSelection(newPasswordInput.getText().length());
        });

        // Toggle visibility Confirm Password
        toggleConfirmPassword.setOnClickListener(v -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            if (isConfirmPasswordVisible) {
                confirmPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT);
                toggleConfirmPassword.setImageResource(R.drawable.ic_visibility);
            } else {
                confirmPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                toggleConfirmPassword.setImageResource(R.drawable.ic_visibility_off);
            }
            confirmPasswordInput.setSelection(confirmPasswordInput.getText().length());
        });

        // Botón Save Password
        btnSavePassword.setOnClickListener(v -> {
            String newPassword = newPasswordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            if (newPassword.length() < 6) {
                Toast.makeText(getContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí guardas la nueva contraseña en tu backend
            Toast.makeText(getContext(), "Password reset successfully!", Toast.LENGTH_SHORT).show();

            // Ir al Login o UsersActivity
            Intent intent = new Intent(getActivity(), UsersActivity.class);
            startActivity(intent);
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        return view;
    }
}
