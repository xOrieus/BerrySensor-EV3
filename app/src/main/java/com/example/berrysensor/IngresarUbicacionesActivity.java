package com.example.berrysensor;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.berrysensor.datos.Repositorio;
import com.example.berrysensor.model.Ubicacion;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class IngresarUbicacionesActivity extends AppCompatActivity {

    private EditText nombreUbicacionEditText;
    private EditText descripcionUbicacionEditText;
    private List<Ubicacion> ubicaciones;
    private Button ingresarUbicacionButton;
    private Button mostrarUbicacionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_ubicaciones);

        ubicaciones = Repositorio.getInstance().ubicaciones;

        nombreUbicacionEditText = findViewById(R.id.nombreUbicacionEditText);
        descripcionUbicacionEditText = findViewById(R.id.descripcionUbicacionEditText);
        ingresarUbicacionButton = findViewById(R.id.ingresarUbicacionButton);
        mostrarUbicacionButton = findViewById(R.id.mostrarUbicacionButton);

        ingresarUbicacionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //Iniocializa la db de Firestore
               FirebaseFirestore db = FirebaseFirestore.getInstance();

               String nombre = nombreUbicacionEditText.getText().toString();

               if (nombre.isEmpty()) {
                   Toast.makeText(IngresarUbicacionesActivity.this, "El nombre es obligatorio",
                           Toast.LENGTH_SHORT).show();
                   return;
               }

               if (nombre.length() < 5) {
                   Toast.makeText(IngresarUbicacionesActivity.this, "El nombre tiene un mínimo de 5 caracteres",
                           Toast.LENGTH_SHORT).show();
                   return;
               }

               if (nombre.length() > 15) {
                   Toast.makeText(IngresarUbicacionesActivity.this, "El nombre tiene un máximo de 15 caracteres",
                           Toast.LENGTH_SHORT).show();
                   return;
               }


               String descripcion = descripcionUbicacionEditText.getText().toString();

               if (descripcion.length() > 30) {
                   Toast.makeText(IngresarUbicacionesActivity.this, "La descripción tiene un máximo de 30 caracteres",
                           Toast.LENGTH_SHORT).show();
                   return;
               }

               Ubicacion nuevo = new Ubicacion(nombre, descripcion);
               ubicaciones.add(nuevo);
                    db.collection("ubicaciones").document().set(nuevo)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(IngresarUbicacionesActivity.this, "Ubicación ingresada correctamente",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(IngresarUbicacionesActivity.this, "Error al ingresar Ubicación",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
               Toast.makeText(IngresarUbicacionesActivity.this, "Ubicación ingresada correctamente",
                       Toast.LENGTH_LONG).show();
               finish();
           }
        });

        mostrarUbicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngresarUbicacionesActivity.this, MostrarUbicacionesActivity.class);
                startActivity(intent);
            }

        });
    }
}