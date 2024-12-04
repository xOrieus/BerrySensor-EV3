package com.example.berrysensor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GestionUbicacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestion_ubicaciones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // asociar el recurso de Layout (botón) con la variable
    Button ingresarUbicacionButton = findViewById(R.id.ingresarUbicacionButton);
    Button mostrarUbicacionButton = findViewById(R.id.mostrarUbicacionButton);


        ingresarUbicacionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // iniciar IngresarUbicacionesActivity
            Intent intent = new Intent(GestionUbicacionesActivity.this, IngresarUbicacionesActivity.class);
            startActivity(intent);
        }
    });

        mostrarUbicacionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // iniciar MostrarUbicacionesActivity
            Intent intent = new Intent(GestionUbicacionesActivity.this, MostrarUbicacionesActivity.class);
            startActivity(intent);
        }
    });


    }

}