package com.example.javamysql_application_mobile_adoptme.View.ui.account;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.card.MaterialCardView;
import androidx.appcompat.widget.AppCompatButton;

import java.util.HashSet;
import java.util.Set;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;
import com.example.javamysql_application_mobile_adoptme.R;

public class AccountFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        // ========================= PROFILE CARD =========================
        /*
        LinearLayout profileCard = root.findViewById(R.id.profile_card);
        profileCard.setOnClickListener(v -> {
            loadFragment(new AccountProfileFragment());
        });
        */

        // ========================= MORE OPTIONS =========================
        /*
        ImageButton btnMoreOptions = root.findViewById(R.id.btn_more_options);
        btnMoreOptions.setOnClickListener(v -> {
            Toast.makeText(getContext(), "More Options", Toast.LENGTH_SHORT).show();
        });
        */

        // ========================= OPTION 1: MY PET PREFERENCES =========================

        LinearLayout optionPetPreferences = root.findViewById(R.id.option_pet_preferences);
        optionPetPreferences.setOnClickListener(v -> {
            showPetPreferencesDialog();
        });

        // ========================= OPTION 2: MY PROFILE =========================
        LinearLayout optionProfile = root.findViewById(R.id.option_profile);
        optionProfile.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.accountProfileFragment);
        });



        // ========================= OPTION 3: NOTIFICATIONS =========================

        LinearLayout optionNotifications = root.findViewById(R.id.option_notifications);
        optionNotifications.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.accountNotificationsFragment);
        });


        // ========================= OPTION 4: ACCOUNT & SECURITY =========================

        LinearLayout optionSecurity = root.findViewById(R.id.option_security);
        optionSecurity.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.accountSecurityFragment);
        });


        // ========================= OPTION 5: LINKED ACCOUNTS =========================
        /*
        LinearLayout optionLinkedAccounts = root.findViewById(R.id.option_linked_accounts);
        optionLinkedAccounts.setOnClickListener(v -> {
            loadFragment(new LinkedAccountsFragment());
        });
        */

        // ========================= OPTION 6: APP APPEARANCE =========================

        LinearLayout optionAppearance = root.findViewById(R.id.option_appearance);
        optionAppearance.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.accountAppearanceFragment);
        });


        // ========================= OPTION 7: DATA & ANALYTICS =========================
        /*
        LinearLayout optionDataAnalytics = root.findViewById(R.id.option_data_analytics);
        optionDataAnalytics.setOnClickListener(v -> {
            loadFragment(new DataAnalyticsFragment());
        });
        */

        // ========================= OPTION 8: HELP & SUPPORT =========================

        LinearLayout optionHelp = root.findViewById(R.id.option_help);
        optionHelp.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.accountHelpFragment);
        });


        // ========================= OPTION 9: INVITE FRIENDS =========================
        /*
        LinearLayout optionInvite = root.findViewById(R.id.option_invite);
        optionInvite.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Join AdoptMe!");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out AdoptMe app to find your perfect pet companion!");
            startActivity(Intent.createChooser(shareIntent, "Invite Friends"));
        });
        */

        // ========================= OPTION 10: LOGOUT =========================

        LinearLayout optionLogout = root.findViewById(R.id.option_logout);
        optionLogout.setOnClickListener(v -> {
            showLogoutDialog();

        });


        return root;
    }








    private void showPetPreferencesDialog() {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pet_preferences);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // Obtener colores del tema - CORREGIDO
        TypedArray ta = requireContext().getTheme().obtainStyledAttributes(
                new int[]{
                        R.attr.grid_menu,
                        com.google.android.material.R.attr.colorSurface,
                        com.google.android.material.R.attr.colorOnSurface
                }
        );
        int orangeColor = ta.getColor(0, 0);
        int surfaceColor = ta.getColor(1, 0);
        int onSurfaceColor = ta.getColor(2, 0);
        ta.recycle();

        Set<String> selectedPreferences = new HashSet<>();
        selectedPreferences.add("Cats"); // Por defecto

        // Referencias a chips
        MaterialCardView chipDogs = dialog.findViewById(R.id.chip_dogs);
        MaterialCardView chipCats = dialog.findViewById(R.id.chip_cats);
        MaterialCardView chipRabbits = dialog.findViewById(R.id.chip_rabbits);
        MaterialCardView chipBirds = dialog.findViewById(R.id.chip_birds);
        MaterialCardView chipReptiles = dialog.findViewById(R.id.chip_reptiles);
        MaterialCardView chipFish = dialog.findViewById(R.id.chip_fish);
        MaterialCardView chipPrimates = dialog.findViewById(R.id.chip_primates);
        MaterialCardView chipOther = dialog.findViewById(R.id.chip_other);

        // Botones
        TextView btnCancel = dialog.findViewById(R.id.btn_cancel);
        AppCompatButton btnOk = dialog.findViewById(R.id.btn_ok);

        // Click listeners
        chipDogs.setOnClickListener(v -> toggleChip(dialog, chipDogs, "Dogs", selectedPreferences, orangeColor, surfaceColor, onSurfaceColor));
        chipCats.setOnClickListener(v -> toggleChip(dialog, chipCats, "Cats", selectedPreferences, orangeColor, surfaceColor, onSurfaceColor));
        chipRabbits.setOnClickListener(v -> toggleChip(dialog, chipRabbits, "Rabbits", selectedPreferences, orangeColor, surfaceColor, onSurfaceColor));
        chipBirds.setOnClickListener(v -> toggleChip(dialog, chipBirds, "Birds", selectedPreferences, orangeColor, surfaceColor, onSurfaceColor));
        chipReptiles.setOnClickListener(v -> toggleChip(dialog, chipReptiles, "Reptiles", selectedPreferences, orangeColor, surfaceColor, onSurfaceColor));
        chipFish.setOnClickListener(v -> toggleChip(dialog, chipFish, "Fish", selectedPreferences, orangeColor, surfaceColor, onSurfaceColor));
        chipPrimates.setOnClickListener(v -> toggleChip(dialog, chipPrimates, "Primates", selectedPreferences, orangeColor, surfaceColor, onSurfaceColor));
        chipOther.setOnClickListener(v -> toggleChip(dialog, chipOther, "Other", selectedPreferences, orangeColor, surfaceColor, onSurfaceColor));

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnOk.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Preferences saved: " + selectedPreferences.toString(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

    private void toggleChip(Dialog dialog, MaterialCardView chip, String preference, Set<String> selectedPreferences, int orangeColor, int surfaceColor, int onSurfaceColor) {
        // Obtener el LinearLayout que contiene el ícono y texto
        LinearLayout chipContent = (LinearLayout) chip.getChildAt(0);
        ImageView icon = (ImageView) chipContent.getChildAt(0);
        TextView text = (TextView) chipContent.getChildAt(1);

        if (selectedPreferences.contains(preference)) {
            // Deseleccionar - CORREGIDO
            selectedPreferences.remove(preference);
            chip.setCardBackgroundColor(surfaceColor);
            chip.setStrokeWidth(2);
            chip.setStrokeColor(onSurfaceColor); // AGREGADO: Establecer color del borde
            icon.setColorFilter(onSurfaceColor);
            text.setTextColor(onSurfaceColor);
        } else {
            // Seleccionar
            selectedPreferences.add(preference);
            chip.setCardBackgroundColor(orangeColor);
            chip.setStrokeWidth(0);
            int whiteColor = requireContext().getColor(R.color.white);
            icon.setColorFilter(whiteColor);
            text.setTextColor(whiteColor);
        }
    }


    private void showLogoutDialog() {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_logout);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // Botones
        TextView btnCancel = dialog.findViewById(R.id.btn_cancel);
        AppCompatButton btnLogout = dialog.findViewById(R.id.btn_logout);

        // Botón Cancel
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        // Botón Yes, Logout
        btnLogout.setOnClickListener(v -> {
            // Limpiar sesión (SharedPreferences, tokens, etc.)
            // SharedPreferences prefs = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
            // prefs.edit().clear().apply();

            // Navegar a AuthActivity y cerrar UsersActivity
            Intent intent = new Intent(getActivity(), AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();

            dialog.dismiss();
        });

        dialog.show();
    }

    // Método helper para cargar fragments
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_users, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
