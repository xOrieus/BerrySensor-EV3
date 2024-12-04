package com.example.berrysensor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class GestionSensoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // asociar el Activity con su Layout
        setContentView(R.layout.activity_gestion_sensores);

        // asociar el recurso de Layout (botón) con la variable
        Button ingresarsensoresButton = findViewById(R.id.ingresarsensoresButton);
        Button modificarsensoresButton = findViewById(R.id.modificarsensoresButton);
        Button mostrarsensoresButton = findViewById(R.id.mostrarsensoresButton);

        // asociar el evento OnClic a cada botón
        ingresarsensoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar IngresarSensoresActivity
                Intent intent = new Intent(GestionSensoresActivity.this, IngresarSensoresActivity.class);
                startActivity(intent);
            }
        });

        modificarsensoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar ModificarSensoresActivity
                Intent intent = new Intent(GestionSensoresActivity.this, ModificarSensoresActivity.class);
                startActivity(intent);
            }
        });

        mostrarsensoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar MostrarSensoresActivity
                Intent intent = new Intent(GestionSensoresActivity.this, MostrarSensoresActivity.class);
                startActivity(intent);
            }
        });
    }




}





