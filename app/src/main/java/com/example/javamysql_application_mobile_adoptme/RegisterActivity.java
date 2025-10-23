package com.example.adoptmev5;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextNombres, editTextApellidos, editTextDni, editTextEmail, editTextTelefono, editTextPassword;
    private CheckBox checkboxTerms;
    private Button buttonRegister;
    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias
        editTextNombres = findViewById(R.id.editText_nombres);
        editTextApellidos = findViewById(R.id.editText_apellidos);
        editTextDni = findViewById(R.id.editText_dni);
        editTextEmail = findViewById(R.id.editText_email);
        editTextTelefono = findViewById(R.id.editText_telefono);
        editTextPassword = findViewById(R.id.editText_password);
        checkboxTerms = findViewById(R.id.checkbox_terms);
        buttonRegister = findViewById(R.id.button_register);
        textViewLogin = findViewById(R.id.textView_login);

        // Acción al registrarse
        buttonRegister.setOnClickListener(v -> {
            if (validarCampos()) {
                registrarUsuario();
            }
        });

        textViewLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private boolean validarCampos() {
        String nombres = editTextNombres.getText().toString().trim();
        String apellidos = editTextApellidos.getText().toString().trim();
        String dni = editTextDni.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String telefono = editTextTelefono.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(nombres) || TextUtils.isEmpty(apellidos) ||
                TextUtils.isEmpty(dni) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(telefono) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (dni.length() != 8) {
            Toast.makeText(this, "El DNI debe tener 8 dígitos", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (telefono.length() != 9) {
            Toast.makeText(this, "El teléfono debe tener 9 dígitos", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!checkboxTerms.isChecked()) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
