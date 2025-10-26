package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.R;

public class AccountNotificationsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_notifications, container, false);

        // Botón Back
        ImageButton btnBack = root.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // Switches
        SwitchCompat switchAdoptionUpdates = root.findViewById(R.id.switch_adoption_updates);
        SwitchCompat switchMatchingPreferences = root.findViewById(R.id.switch_matching_preferences);
        SwitchCompat switchFavoritesUpdates = root.findViewById(R.id.switch_favorites_updates);
        SwitchCompat switchSecurityAlerts = root.findViewById(R.id.switch_security_alerts);
        SwitchCompat switchEventReminders = root.findViewById(R.id.switch_event_reminders);
        SwitchCompat switchShelterUpdates = root.findViewById(R.id.switch_shelter_updates);
        SwitchCompat switchCommunityEngagement = root.findViewById(R.id.switch_community_engagement);
        SwitchCompat switchGeneralAppUpdates = root.findViewById(R.id.switch_general_app_updates);
        SwitchCompat switchImportantAnnouncements = root.findViewById(R.id.switch_important_announcements);
        SwitchCompat switchAppTipsTutorials = root.findViewById(R.id.switch_app_tips_tutorials);

        // Aquí puedes guardar el estado cuando cambien
        /*
        switchAdoptionUpdates.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Guardar en SharedPreferences o enviar al servidor
        });
        */

        return root;
    }
}
