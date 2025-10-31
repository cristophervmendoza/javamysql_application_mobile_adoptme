package com.example.javamysql_application_mobile_adoptme.Auth.login.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;
import com.example.javamysql_application_mobile_adoptme.R;
import com.example.javamysql_application_mobile_adoptme.data.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterFragment extends Fragment {

    TextInputEditText nameInput, lastnameInput, dniInput, emailInput, phoneInput, passwordInput, confirmPasswordInput;
    CheckBox termsCheckbox;
    Button btnRegister;
    TextView loginLink;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        nameInput = view.findViewById(R.id.name_input);
        lastnameInput = view.findViewById(R.id.lastname_input);
        dniInput = view.findViewById(R.id.dni_input);
        emailInput = view.findViewById(R.id.email_input);
        phoneInput = view.findViewById(R.id.phone_input);
        passwordInput = view.findViewById(R.id.password_input);
        confirmPasswordInput = view.findViewById(R.id.confirm_password_input);
        termsCheckbox = view.findViewById(R.id.terms_checkbox);
        btnRegister = view.findViewById(R.id.btn_signup);
        loginLink = view.findViewById(R.id.login_link);


        btnRegister.setOnClickListener(v -> registerUser());


        loginLink.setOnClickListener(v -> {
            ((AuthActivity) getActivity()).loadFragment(new LoginFragment(), false);
        });

        return view;
    }

    private void registerUser() {
        String nombre = nameInput.getText().toString().trim();
        String apellido = lastnameInput.getText().toString().trim();
        String dni = dniInput.getText().toString().trim();
        String correo = emailInput.getText().toString().trim();
        String telefono = phoneInput.getText().toString().trim();
        String contrasena = passwordInput.getText().toString().trim();
        String confirmar = confirmPasswordInput.getText().toString().trim();


        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || correo.isEmpty() ||
                telefono.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty()) {
            Toast.makeText(getContext(), "⚠️ Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            emailInput.setError("Correo no válido");
            emailInput.requestFocus();
            return;
        }


        if (!dni.matches("\\d{8}")) {
            dniInput.setError("El DNI debe tener 8 dígitos numéricos");
            dniInput.requestFocus();
            return;
        }


        if (!telefono.matches("\\d{9}")) {
            phoneInput.setError("El teléfono debe tener 9 dígitos");
            phoneInput.requestFocus();
            return;
        }


        if (contrasena.length() < 6) {
            passwordInput.setError("La contraseña debe tener al menos 6 caracteres");
            passwordInput.requestFocus();
            return;
        }


        if (!contrasena.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$")) {
            passwordInput.setError("Debe tener mayúsculas, minúsculas y números");
            passwordInput.requestFocus();
            return;
        }


        if (!contrasena.equals(confirmar)) {
            confirmPasswordInput.setError("Las contraseñas no coinciden");
            confirmPasswordInput.requestFocus();
            return;
        }


        if (!termsCheckbox.isChecked()) {
            Toast.makeText(getContext(), "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
            return;
        }

        //Verificar si el usuario existe localmente
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        if (dbHelper.userExists(correo)) {
            Toast.makeText(getContext(), "El usuario ya existe localmente", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar localmente
        boolean insertado = dbHelper.insertUser(nombre, apellido, dni, correo, telefono, contrasena);
        if (insertado) {
            Toast.makeText(getContext(), "Usuario guardado localmente ✅", Toast.LENGTH_SHORT).show();
            // Enviar datos al servidor remoto
            enviarRegistroServidor(nombre, apellido, dni, correo, telefono, contrasena);
        } else {
            Toast.makeText(getContext(), "Error al guardar localmente ❌", Toast.LENGTH_SHORT).show();
        }
    }


    private void enviarRegistroServidor(String nombre, String apellido, String dni,
                                        String correo, String telefono, String contrasena) {
        new Thread(() -> {
            try {
                URL url = new URL("https://adoptme-backendphp-emfwe5fbg5f8gpc6.chilecentral-01.azurewebsites.net/registrar_usuario.php");

                String postData =
                        "nombre=" + URLEncoder.encode(nombre, "UTF-8") + "&" +
                                "apellido=" + URLEncoder.encode(apellido, "UTF-8") + "&" +
                                "dni=" + URLEncoder.encode(dni, "UTF-8") + "&" +
                                "correo=" + URLEncoder.encode(correo, "UTF-8") + "&" +
                                "telefono=" + URLEncoder.encode(telefono, "UTF-8") + "&" +
                                "contrasena=" + URLEncoder.encode(contrasena, "UTF-8");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);

                // Enviar datos al servidor
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(postData.getBytes());
                    os.flush();
                }

                // Leer respuesta del servidor
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                conn.disconnect();


                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                    limpiarCampos(); //
                });

            } catch (Exception e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        }).start();
    }

    private void limpiarCampos() {
        nameInput.setText("");
        lastnameInput.setText("");
        dniInput.setText("");
        emailInput.setText("");
        phoneInput.setText("");
        passwordInput.setText("");
        confirmPasswordInput.setText("");
        termsCheckbox.setChecked(false);
    }
}
