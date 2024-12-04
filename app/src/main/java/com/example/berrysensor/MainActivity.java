package com.example.berrysensor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // asociar el Activity con su Layout
        setContentView(R.layout.activity_main);

        // asociar el recurso de Layout (botón) con la variable
        Button ubicacionButton = findViewById(R.id.ubicacionButton);
        Button sensoresButton = findViewById(R.id.sensoresButton);
        Button registroButton = findViewById(R.id.registroButton);

        // asociar el evento OnClic a cada botón
        ubicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar GestionUbicacionesActivity
                Intent intent = new Intent(MainActivity.this, GestionUbicacionesActivity.class);
                startActivity(intent);
            }
        });

        sensoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar GestionSensoresActivity
                Intent intent = new Intent(MainActivity.this, GestionSensoresActivity.class);
                startActivity(intent);
            }
        });

        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar RegistroActivity
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }




}





