package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.R;
import com.google.android.material.card.MaterialCardView;

import java.util.HashSet;
import java.util.Set;

public class AccountPetPreferencesFragment extends Fragment {

    private Set<String> selectedPreferences = new HashSet<>();
    private int orangeColor;
    private int surfaceColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_pet_preferences, container, false);

        // Obtener colores del tema
        TypedArray ta = requireContext().getTheme().obtainStyledAttributes(new int[]{R.attr.grid_menu, com.google.android.material.R.attr.colorSurface});
        orangeColor = ta.getColor(0, 0);
        surfaceColor = ta.getColor(1, 0);
        ta.recycle();

        // Botón Back
        ImageButton btnBack = root.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // Chips
        MaterialCardView chipDogs = root.findViewById(R.id.chip_dogs);
        MaterialCardView chipCats = root.findViewById(R.id.chip_cats);
        MaterialCardView chipRabbits = root.findViewById(R.id.chip_rabbits);
        MaterialCardView chipBirds = root.findViewById(R.id.chip_birds);
        MaterialCardView chipReptiles = root.findViewById(R.id.chip_reptiles);
        MaterialCardView chipFish = root.findViewById(R.id.chip_fish);
        MaterialCardView chipPrimates = root.findViewById(R.id.chip_primates);
        MaterialCardView chipOther = root.findViewById(R.id.chip_other);

        // Cats seleccionado por defecto
        selectedPreferences.add("Cats");

        // Click listeners para chips
        chipDogs.setOnClickListener(v -> toggleChip(chipDogs, "Dogs"));
        chipCats.setOnClickListener(v -> toggleChip(chipCats, "Cats"));
        chipRabbits.setOnClickListener(v -> toggleChip(chipRabbits, "Rabbits"));
        chipBirds.setOnClickListener(v -> toggleChip(chipBirds, "Birds"));
        chipReptiles.setOnClickListener(v -> toggleChip(chipReptiles, "Reptiles"));
        chipFish.setOnClickListener(v -> toggleChip(chipFish, "Fish"));
        chipPrimates.setOnClickListener(v -> toggleChip(chipPrimates, "Primates"));
        chipOther.setOnClickListener(v -> toggleChip(chipOther, "Other"));

        // Botones Cancel y OK
        TextView btnCancel = root.findViewById(R.id.btn_cancel);
        AppCompatButton btnOk = root.findViewById(R.id.btn_ok);

        btnCancel.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        btnOk.setOnClickListener(v -> {
            // Guardar preferencias
            Toast.makeText(getContext(), "Preferences saved: " + selectedPreferences.toString(), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return root;
    }

    private void toggleChip(MaterialCardView chip, String preference) {
        if (selectedPreferences.contains(preference)) {
            // Deseleccionar
            selectedPreferences.remove(preference);
            chip.setCardBackgroundColor(surfaceColor);
            chip.setStrokeWidth(2);

            // Cambiar color del ícono y texto a colorOnSurface
            ImageView icon = (ImageView) ((android.view.ViewGroup) chip.getChildAt(0)).getChildAt(0);
            TextView text = (TextView) ((android.view.ViewGroup) chip.getChildAt(0)).getChildAt(1);

            TypedArray ta = requireContext().getTheme().obtainStyledAttributes(new int[]{com.google.android.material.R.attr.colorOnSurface});
            int textColor = ta.getColor(0, 0);
            ta.recycle();

            icon.setColorFilter(textColor);
            text.setTextColor(textColor);
        } else {
            // Seleccionar
            selectedPreferences.add(preference);
            chip.setCardBackgroundColor(orangeColor);
            chip.setStrokeWidth(0);

            // Cambiar color del ícono y texto a blanco
            ImageView icon = (ImageView) ((android.view.ViewGroup) chip.getChildAt(0)).getChildAt(0);
            TextView text = (TextView) ((android.view.ViewGroup) chip.getChildAt(0)).getChildAt(1);

            int whiteColor = requireContext().getColor(R.color.white);
            icon.setColorFilter(whiteColor);
            text.setTextColor(whiteColor);
        }
    }
}
