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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.berrysensor.datos.Repositorio;
import com.example.berrysensor.model.Sensor;
import com.example.berrysensor.model.TipoSensor;
import com.example.berrysensor.model.Ubicacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.List;

public class IngresarSensoresActivity extends AppCompatActivity {

    private EditText nombreSensorEditText;
    private EditText descripcionSensorEditText;
    private EditText idealSensorEditText;
    private Spinner ubicacionSensorSpinner;
    private Spinner tipoSensorSpinner;
    private List<TipoSensor> tipos;
    private List<Ubicacion> ubicaciones;
    private List<Sensor> sensores;
    private Button ingresarSensorButton;
    private Button mostrarSensoresButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_sensores);

        sensores = Repositorio.getInstance().sensores;
        tipos = Repositorio.getInstance().tipos;
        ubicaciones = Repositorio.getInstance().ubicaciones;

        nombreSensorEditText = findViewById(R.id.nombreSensorEditText);
        descripcionSensorEditText = findViewById(R.id.descripcionSensorEditText);
        idealSensorEditText = findViewById(R.id.idealSensorEditText); 
        tipoSensorSpinner = findViewById(R.id.tipoSensorSpinner);
        ubicacionSensorSpinner = findViewById(R.id.ubicacionSensorSpinner);
        ingresarSensorButton = findViewById(R.id.ingresarSensorButton);
        mostrarSensoresButton = findViewById(R.id.mostrarSensoresButton);

        ArrayAdapter<TipoSensor> tipoAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, tipos);
        tipoSensorSpinner.setAdapter(tipoAdapter);

        //Inicializa la base de datos para mostrar la colección de ubicaciones de Firestore al ingresar
        // la ubicación (se le añade al adapter las ubicaciones obtenida)
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("ubicaciones").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ubicaciones.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Ubicacion ubicacion = document.toObject(Ubicacion.class);
                                ubicaciones.add(ubicacion);
                            }

                            ArrayAdapter<Ubicacion> ubicacionAdapter = new ArrayAdapter<>(
                                    IngresarSensoresActivity.this,
                                    R.layout.spinner_item,
                                    ubicaciones
                            );
                            ubicacionSensorSpinner.setAdapter(ubicacionAdapter);
                        } else {
                            Toast.makeText(IngresarSensoresActivity.this, "Error al encontrar ubicaciones",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        ingresarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Inicializa la db de firestore
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                String nombre = nombreSensorEditText.getText().toString();

                if (nombre.isEmpty()) {
                    Toast.makeText(IngresarSensoresActivity.this, "El nombre es obligatorio",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nombre.length() < 5) {
                    Toast.makeText(IngresarSensoresActivity.this, "El nombre tiene un mínimo de 5 caracteres",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nombre.length() > 15) {
                    Toast.makeText(IngresarSensoresActivity.this, "El nombre tiene un máximo de 15 caracteres",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                String descripcion = descripcionSensorEditText.getText().toString();
                if (descripcion.length() > 30) {
                    Toast.makeText(IngresarSensoresActivity.this, "La descripción tiene un máximo de 30 caracteres",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                float ideal = Float.parseFloat(idealSensorEditText.getText().toString());
                if (ideal <= 0) {
                    Toast.makeText(IngresarSensoresActivity.this, "El ideal debe ser un valor positivo",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                TipoSensor tipoSensor = (TipoSensor) tipoSensorSpinner.getSelectedItem();
                Ubicacion ubicacion = (Ubicacion) ubicacionSensorSpinner.getSelectedItem();

                Sensor nuevo = new Sensor(nombre, descripcion, ideal, tipoSensor, ubicacion);
                sensores.add(nuevo);
                    db.collection("sensores").document().set(nuevo)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(IngresarSensoresActivity.this, "Sensor ingresado correctamente",
                                            Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(IngresarSensoresActivity.this, "Error al ingresar sensor",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    Toast.makeText(IngresarSensoresActivity.this, "Sensor ingresado correctamente",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
        });

        mostrarSensoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngresarSensoresActivity.this, MostrarSensoresActivity.class);
                startActivity(intent);
            }

        });
    }
}