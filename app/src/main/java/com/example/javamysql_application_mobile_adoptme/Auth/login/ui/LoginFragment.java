package com.example.javamysql_application_mobile_adoptme.Auth.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;
import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.View.UsersActivity;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.content.SharedPreferences;
import android.util.Log;



public class LoginFragment extends Fragment {

    TextInputEditText emailInput, passwordInput;
    Button btnLogin;
    TextView registerLink, forgotPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailInput = view.findViewById(R.id.email_input);
        passwordInput = view.findViewById(R.id.password_input);
        btnLogin = view.findViewById(R.id.btn_login);
        registerLink = view.findViewById(R.id.register_link);
        forgotPassword = view.findViewById(R.id.forgot_password);

        btnLogin.setOnClickListener(v -> loginUser());


        registerLink.setOnClickListener(v ->
                ((AuthActivity) getActivity()).loadFragment(new RegisterFragment(), false)
        );

        forgotPassword.setOnClickListener(v ->
                ((AuthActivity) getActivity()).loadFragment(new ForgotPasswordFragment(), true)
        );

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((AuthActivity) requireActivity()).loadFragment(new StartUpFragment(), false);
            }
        });

        return view;
    }

    // Login local de prueba (admin@adoptme.com - 123456)
    /*private void loginUser1() {
        String correo = emailInput.getText().toString().trim();
        String contrasena = passwordInput.getText().toString().trim();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String correoValido = "admin@adoptme.com";
        String contrasenaValida = "123456";

        if (correo.equals(correoValido) && contrasena.equals(contrasenaValida)) {
            Toast.makeText(getContext(), "Bienvenido a AdoptMe 🐾", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getActivity(), UsersActivity.class);
            startActivity(intent);
            requireActivity().finish();
        } else {
            Toast.makeText(getContext(), "❌ Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }*/


    private void loginUser() {
        String correo = emailInput.getText().toString().trim();
        String contrasena = passwordInput.getText().toString().trim();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            try {
                URL url = new URL("https://adoptme-backendphp-emfwe5fbg5f8gpc6.chilecentral-01.azurewebsites.net/login.php");
                String postData = "correo=" + URLEncoder.encode(correo, "UTF-8") +
                        "&contrasena=" + URLEncoder.encode(contrasena, "UTF-8");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(postData.getBytes());
                    os.flush();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                conn.disconnect();

                String jsonResponse = response.toString();

                requireActivity().runOnUiThread(() -> {
                    try {
                        JSONObject json = new JSONObject(jsonResponse);

                        if (json.has("success") && json.getBoolean("success")) {

                            JSONObject usuario = json.getJSONObject("usuario");
                            SharedPreferences prefs = requireActivity().getSharedPreferences("UserSession", getContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("id_usuario", usuario.optInt("id_usuario"));
                            editor.putString("nombre", usuario.optString("nombre"));
                            editor.putString("apellido", usuario.optString("apellido"));
                            editor.putString("correo", usuario.optString("correo"));
                            editor.putString("dni", usuario.optString("dni"));
                            editor.putString("telefono", usuario.optString("telefono"));
                            editor.apply();

                            Toast.makeText(getContext(), "Bienvenida, " + usuario.optString("nombre") + " 🐾", Toast.LENGTH_LONG).show();

                            limpiarCampos();

                            Intent intent = new Intent(getActivity(), UsersActivity.class);
                            startActivity(intent);
                            requireActivity().finish();

                        } else {
                            String errorMsg = json.optString("error", "❌ Credenciales incorrectas");
                            Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "Error al interpretar la respuesta del servidor", Toast.LENGTH_LONG).show();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Error de conexión: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        }).start();
    }



    private void limpiarCampos() {
        emailInput.setText("");
        passwordInput.setText("");
    }
}
