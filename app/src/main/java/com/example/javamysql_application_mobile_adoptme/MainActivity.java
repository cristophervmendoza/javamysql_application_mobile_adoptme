package com.example.javamysql_application_mobile_adoptme;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javamysql_application_mobile_adoptme.Auth.login.AuthActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        // Opción A: Forzar modo claro
//ForceTheme.setLightMode();

        // Opción B: Forzar modo oscuro
ForceTheme.setDarkMode();

        // Opción C: Seguir el sistema
        // ForceTheme.setSystemMode();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ImageView logo = findViewById(R.id.img_center_logo);

        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        final Animation exitAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_exit_animation);

        splashAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(() -> {
                    logo.startAnimation(exitAnimation);
                }, 500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        exitAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        logo.startAnimation(splashAnimation);
    }
}
