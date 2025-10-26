package com.example.javamysql_application_mobile_adoptme.View.ui.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.R;

public class AccountHelpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_help, container, false);

        // Botón Back
        ImageButton btnBack = root.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // Opciones
        LinearLayout optionFaq = root.findViewById(R.id.option_faq);
        LinearLayout optionContactSupport = root.findViewById(R.id.option_contact_support);
        LinearLayout optionPrivacyPolicy = root.findViewById(R.id.option_privacy_policy);
        LinearLayout optionTermsService = root.findViewById(R.id.option_terms_service);
        LinearLayout optionPartner = root.findViewById(R.id.option_partner);
        LinearLayout optionJobVacancy = root.findViewById(R.id.option_job_vacancy);
        LinearLayout optionAccessibility = root.findViewById(R.id.option_accessibility);
        LinearLayout optionFeedback = root.findViewById(R.id.option_feedback);
        LinearLayout optionAboutUs = root.findViewById(R.id.option_about_us);
        LinearLayout optionRateUs = root.findViewById(R.id.option_rate_us);
        LinearLayout optionVisitWebsite = root.findViewById(R.id.option_visit_website);
        LinearLayout optionSocialMedia = root.findViewById(R.id.option_social_media);

        // Listeners (comentados para habilitar después)
        /*
        optionFaq.setOnClickListener(v -> {
            Toast.makeText(getContext(), "FAQ", Toast.LENGTH_SHORT).show();
        });

        optionContactSupport.setOnClickListener(v -> {
            // Abrir email o chat de soporte
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:support@adoptme.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support Request");
            startActivity(Intent.createChooser(emailIntent, "Contact Support"));
        });

        optionPrivacyPolicy.setOnClickListener(v -> {
            // Abrir Privacy Policy (URL o pantalla)
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://adoptme.com/privacy"));
            startActivity(browserIntent);
        });

        optionTermsService.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://adoptme.com/terms"));
            startActivity(browserIntent);
        });

        optionPartner.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Partner", Toast.LENGTH_SHORT).show();
        });

        optionJobVacancy.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://adoptme.com/jobs"));
            startActivity(browserIntent);
        });

        optionAccessibility.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Accessibility", Toast.LENGTH_SHORT).show();
        });

        optionFeedback.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Feedback", Toast.LENGTH_SHORT).show();
        });

        optionAboutUs.setOnClickListener(v -> {
            Toast.makeText(getContext(), "About us", Toast.LENGTH_SHORT).show();
        });

        optionRateUs.setOnClickListener(v -> {
            // Abrir Google Play Store
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + requireContext().getPackageName()));
            startActivity(rateIntent);
        });

        optionVisitWebsite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://adoptme.com"));
            startActivity(browserIntent);
        });

        optionSocialMedia.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Follow us on Social Media", Toast.LENGTH_SHORT).show();
        });
        */

        return root;
    }
}
