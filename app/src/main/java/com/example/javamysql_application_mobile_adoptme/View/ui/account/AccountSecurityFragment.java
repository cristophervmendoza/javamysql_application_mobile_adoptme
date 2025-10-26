package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.R;

public class AccountSecurityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_security, container, false);

        // Botón Back
        ImageButton btnBack = root.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // Switches
        SwitchCompat switchBiometricId = root.findViewById(R.id.switch_biometric_id);
        SwitchCompat switchFaceId = root.findViewById(R.id.switch_face_id);
        SwitchCompat switchSmsAuthenticator = root.findViewById(R.id.switch_sms_authenticator);

        // Opciones con navegación
        LinearLayout optionGoogleAuthenticator = root.findViewById(R.id.option_google_authenticator);
        LinearLayout optionChangePassword = root.findViewById(R.id.option_change_password);
        LinearLayout optionDeviceManagement = root.findViewById(R.id.option_device_management);
        LinearLayout optionDeactivateAccount = root.findViewById(R.id.option_deactivate_account);
        LinearLayout optionDeleteAccount = root.findViewById(R.id.option_delete_account);

        // Listeners para switches
        /*
        switchBiometricId.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(getContext(), "Biometric ID: " + isChecked, Toast.LENGTH_SHORT).show();
        });
        */

        // Listeners para opciones
        /*
        optionGoogleAuthenticator.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Google Authenticator", Toast.LENGTH_SHORT).show();
        });

        optionChangePassword.setOnClickListener(v -> {
            // Navegar a pantalla de cambio de contraseña
            Toast.makeText(getContext(), "Change Password", Toast.LENGTH_SHORT).show();
        });

        optionDeviceManagement.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Device Management", Toast.LENGTH_SHORT).show();
        });

        optionDeactivateAccount.setOnClickListener(v -> {
            // Mostrar diálogo de confirmación
            new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Deactivate Account")
                .setMessage("Are you sure you want to deactivate your account?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(getContext(), "Account deactivated", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
        });

        optionDeleteAccount.setOnClickListener(v -> {
            // Mostrar diálogo de confirmación (más fuerte)
            new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Delete Account")
                .setMessage("This action cannot be undone. All your data will be permanently deleted.")
                .setPositiveButton("Delete", (dialog, which) -> {
                    Toast.makeText(getContext(), "Account deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
        });
        */

        return root;
    }
}
