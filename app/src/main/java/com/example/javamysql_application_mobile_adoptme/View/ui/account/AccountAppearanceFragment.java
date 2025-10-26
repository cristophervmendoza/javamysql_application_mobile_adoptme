package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.R;

public class AccountAppearanceFragment extends Fragment {

    private TextView txtCurrentTheme;
    private String currentTheme = "Light";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_appearance, container, false);

        // Botón Back
        ImageButton btnBack = root.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // TextViews
        txtCurrentTheme = root.findViewById(R.id.txt_current_theme);
        TextView txtCurrentLanguage = root.findViewById(R.id.txt_current_language);

        // Cargar tema guardado
        SharedPreferences prefs = requireContext().getSharedPreferences("AppSettings", requireContext().MODE_PRIVATE);
        currentTheme = prefs.getString("theme", "Light");
        txtCurrentTheme.setText(currentTheme);

        // Opciones
        LinearLayout optionTheme = root.findViewById(R.id.option_theme);
        LinearLayout optionAppLanguage = root.findViewById(R.id.option_app_language);

        // Click en Theme
        optionTheme.setOnClickListener(v -> {
            showThemeDialog();
        });

        // Click en App Language
        optionAppLanguage.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Language selection (coming soon)", Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    private void showThemeDialog() {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_choose_theme);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // Referencias
        RadioGroup radioGroup = dialog.findViewById(R.id.radio_group_theme);
        RadioButton radioSystemDefault = dialog.findViewById(R.id.radio_system_default);
        RadioButton radioLight = dialog.findViewById(R.id.radio_light);
        RadioButton radioDark = dialog.findViewById(R.id.radio_dark);
        TextView btnCancel = dialog.findViewById(R.id.btn_cancel);
        AppCompatButton btnOk = dialog.findViewById(R.id.btn_ok);

        // Seleccionar el tema actual
        if (currentTheme.equals("System Default")) {
            radioSystemDefault.setChecked(true);
        } else if (currentTheme.equals("Light")) {
            radioLight.setChecked(true);
        } else if (currentTheme.equals("Dark")) {
            radioDark.setChecked(true);
        }

        // Botón Cancel
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        // Botón OK
        btnOk.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == R.id.radio_system_default) {
                currentTheme = "System Default";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            } else if (selectedId == R.id.radio_light) {
                currentTheme = "Light";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else if (selectedId == R.id.radio_dark) {
                currentTheme = "Dark";
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            // Guardar en SharedPreferences
            SharedPreferences prefs = requireContext().getSharedPreferences("AppSettings", requireContext().MODE_PRIVATE);
            prefs.edit().putString("theme", currentTheme).apply();

            // Actualizar UI
            txtCurrentTheme.setText(currentTheme);

            dialog.dismiss();
            Toast.makeText(getContext(), "Theme changed to " + currentTheme, Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }
}
