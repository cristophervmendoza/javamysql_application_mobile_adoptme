package com.example.javamysql_application_mobile_adoptme.View.ui.account;

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
        /*
        LinearLayout optionPetPreferences = root.findViewById(R.id.option_pet_preferences);
        optionPetPreferences.setOnClickListener(v -> {
            loadFragment(new PetPreferencesFragment());
        });
        */

        // ========================= OPTION 2: MY PROFILE =========================
        LinearLayout optionProfile = root.findViewById(R.id.option_profile);
        optionProfile.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.accountProfileFragment);
        });



        // ========================= OPTION 3: NOTIFICATIONS =========================
        /*
        LinearLayout optionNotifications = root.findViewById(R.id.option_notifications);
        optionNotifications.setOnClickListener(v -> {
            loadFragment(new NotificationsSettingsFragment());
        });
        */

        // ========================= OPTION 4: ACCOUNT & SECURITY =========================
        /*
        LinearLayout optionSecurity = root.findViewById(R.id.option_security);
        optionSecurity.setOnClickListener(v -> {
            loadFragment(new SecurityFragment());
        });
        */

        // ========================= OPTION 5: LINKED ACCOUNTS =========================
        /*
        LinearLayout optionLinkedAccounts = root.findViewById(R.id.option_linked_accounts);
        optionLinkedAccounts.setOnClickListener(v -> {
            loadFragment(new LinkedAccountsFragment());
        });
        */

        // ========================= OPTION 6: APP APPEARANCE =========================
        /*
        LinearLayout optionAppearance = root.findViewById(R.id.option_appearance);
        optionAppearance.setOnClickListener(v -> {
            loadFragment(new AppearanceFragment());
        });
        */

        // ========================= OPTION 7: DATA & ANALYTICS =========================
        /*
        LinearLayout optionDataAnalytics = root.findViewById(R.id.option_data_analytics);
        optionDataAnalytics.setOnClickListener(v -> {
            loadFragment(new DataAnalyticsFragment());
        });
        */

        // ========================= OPTION 8: HELP & SUPPORT =========================
        /*
        LinearLayout optionHelp = root.findViewById(R.id.option_help);
        optionHelp.setOnClickListener(v -> {
            loadFragment(new HelpSupportFragment());
        });
        */

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
        /*
        LinearLayout optionLogout = root.findViewById(R.id.option_logout);
        optionLogout.setOnClickListener(v -> {
            new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Limpiar sesión
                    // SharedPreferences prefs = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
                    // prefs.edit().clear().apply();

                    Intent intent = new Intent(getActivity(), AuthActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    requireActivity().finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
        });
        */

        return root;
    }

    // Método helper para cargar fragments
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_users, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
