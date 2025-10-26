package com.example.javamysql_application_mobile_adoptme.Auth.login.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;
import com.example.javamysql_application_mobile_adoptme.Auth.login.ui.ResetPasswordFragment;
import com.example.javamysql_application_mobile_adoptme.R;

public class VerifyCodeRegFragment extends Fragment {

    private EditText[] otpDigits;
    private int currentDigitIndex = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verify_code_reg, container, false);

        otpDigits = new EditText[]{
                view.findViewById(R.id.otp_digit_1),
                view.findViewById(R.id.otp_digit_2),
                view.findViewById(R.id.otp_digit_3),
                view.findViewById(R.id.otp_digit_4)
        };

        ImageButton btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
    private void verifyOTP() {
        StringBuilder otp = new StringBuilder();
        for (EditText digit : otpDigits) {
            otp.append(digit.getText().toString());
        }

        String otpCode = otp.toString();

        if (otpCode.equals("1234")) {
            Toast.makeText(getContext(), "OTP verified successfully!", Toast.LENGTH_SHORT).show();

            ((AuthActivity) getActivity()).loadFragment(new ResetPasswordFragment(), true);
        } else {
            Toast.makeText(getContext(), "Invalid OTP code", Toast.LENGTH_SHORT).show();

            for (EditText digit : otpDigits) {
                digit.setText("");
            }
            currentDigitIndex = 0;
        }
    }
}
