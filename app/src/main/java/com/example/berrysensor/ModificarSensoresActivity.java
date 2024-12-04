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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ModificarSensoresActivity extends AppCompatActivity {

    private EditText nombreSensorEditText;
    private EditText descripcionSensorEditText;
    private EditText idealSensorEditText;
    private Spinner ubicacionSensorSpinner;
    private Spinner tipoSensorSpinner;
    private List<TipoSensor> tipos;
    private List<Ubicacion> ubicaciones;
    private List<Sensor> sensores;
    private Button ModificarSensorButton;
    private Button buscarSensorButton;
    private Button mostrarSensoresButton;
    private Button eliminarSensorButton;

    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_sensores);

        sensores = Repositorio.getInstance().sensores;
        tipos = Repositorio.getInstance().tipos;
        ubicaciones = Repositorio.getInstance().ubicaciones;

        nombreSensorEditText = findViewById(R.id.nombreSensorEditText);
        descripcionSensorEditText = findViewById(R.id.descripcionSensorEditText);
        idealSensorEditText = findViewById(R.id.idealSensorEditText);
        tipoSensorSpinner = findViewById(R.id.tipoSensorSpinner);
        ubicacionSensorSpinner = findViewById(R.id.ubicacionSensorSpinner);

        ModificarSensorButton = findViewById(R.id.ModificarSensorButton);
        buscarSensorButton = findViewById(R.id.buscarSensorButton);
        eliminarSensorButton = findViewById(R.id.eliminarSensorButton);

        mostrarSensoresButton = findViewById(R.id.mostrarSensoresButton);

        ArrayAdapter<TipoSensor> tipoAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, tipos);
        tipoSensorSpinner.setAdapter(tipoAdapter);

        ArrayAdapter<Ubicacion> ubicacionAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, ubicaciones);
        ubicacionSensorSpinner.setAdapter(ubicacionAdapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ModificarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sensor != null) {
                    db.collection("sensores").whereEqualTo("nombre", sensor.getNombre()).get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot doc : task.getResult()) {
                                            String id = doc.getId();

                                            String nombre = nombreSensorEditText.getText().toString();
                                            sensor.setNombre(nombre);

                                            String descripcion = descripcionSensorEditText.getText().toString();
                                            sensor.setDescripcion(descripcion);

                                            float ideal = Float.parseFloat(idealSensorEditText.getText().toString());
                                            if (ideal <= 0) {
                                                Toast.makeText(ModificarSensoresActivity.this, "El ideal debe ser un valor positivo",
                                                        Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            sensor.setIdeal(ideal);

                                            TipoSensor tipoSensor = (TipoSensor) tipoSensorSpinner.getSelectedItem();
                                            sensor.setTipoSensor(tipoSensor);

                                            Ubicacion ubicacion = (Ubicacion) ubicacionSensorSpinner.getSelectedItem();
                                            sensor.setUbicacion(ubicacion);

                                            DocumentReference docRef = db.collection("sensores")
                                                    .document(id);
                                            //test
                                            //docRef.update("descripcion", sensor.getDescripcion(), "ideal", sensor.getIdeal(), tipoSensor, sensor.getTipoSensor(), ubicacion, sensor.getUbicacion())
                                            docRef.update("nombre", sensor.getNombre())
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(ModificarSensoresActivity.this,"Sensor modificado correctamente en BD",
                                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    });

                                            docRef.update("descripcion", sensor.getDescripcion())
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {

                                                        }
                                                    });
                                            docRef.update("ideal", sensor.getIdeal())
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {

                                                        }
                                                    });

                                            docRef.update("tipoSensor", new TipoSensor(tipoSensor.getNombre()))
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {

                                                        }
                                                    });

                                            docRef.update("ubicacion", new Ubicacion(ubicacion.getNombre(), ubicacion.getDescripcion()))
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {

                                                        }
                                                    });
                                        }
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ModificarSensoresActivity.this,"Error al modificar Sensor",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                }

            }
        });

        buscarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nombreSensorEditText.getText().toString();
                db.collection("sensores").whereEqualTo("nombre", nombre).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot doc : task.getResult()) {
                                        if (doc.get("nombre").equals(nombre)) {
                                            sensor = doc.toObject(Sensor.class);
                                        }
                                    }
                                    descripcionSensorEditText.setText(sensor.getDescripcion());
                                    idealSensorEditText.setText(String.valueOf(sensor.getIdeal()));
                                    // recorre los tipos de sensor y si coincide, se muestra
                                    for (int i = 0; i < tipos.size(); i++) {
                                        if (tipos.get(i).getNombre().equals(sensor.getTipoSensor().getNombre())) {
                                            tipoSensorSpinner.setSelection(i);
                                            break;
                                        }
                                    }

                                    // spinner ubicacion
                                    for (int i = 0; i < ubicaciones.size(); i++) {
                                        if (ubicaciones.get(i).getNombre().equals(sensor.getUbicacion().getNombre())) {
                                            ubicacionSensorSpinner.setSelection(i);
                                            break;
                                        }
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ModificarSensoresActivity.this,"Error al buscar Sensor",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

        eliminarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sensor != null) {
                    db.collection("sensores").whereEqualTo("nombre", sensor.getNombre()).get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot doc : task.getResult()) {
                                            String id = doc.getId();

                                            db.collection("sensores").document(id)
                                                    .delete()
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(ModificarSensoresActivity.this,"Sensor eliminado correctamente",
                                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(ModificarSensoresActivity.this,"Error al eliminar Sensor",
                                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                        }
                                    }
                                }
                            });
                }
            }
        });

        mostrarSensoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // iniciar MostrarSensoresActivity
                Intent intent = new Intent(ModificarSensoresActivity.this, MostrarSensoresActivity.class);
                startActivity(intent);
            }
        });
    }

    public void buscar(View view) {
        // declaración de una variable entera
        int duracion = Toast.LENGTH_LONG;
        // declaración de una variable de tipo secuencia de caracteres (String)
        CharSequence mensaje = "Sensor no encontrado";
        // creación y lazanmiento de un aviso emergente
        Toast toast = Toast.makeText(this, mensaje, duracion);
        toast.show();
    }

    public void eliminar(View view) {
        int duracion = Toast.LENGTH_LONG;
        CharSequence mensaje = "Sensor eliminado correctamente";
        Toast toast = Toast.makeText(this, mensaje, duracion);
        toast.show();
    }
}