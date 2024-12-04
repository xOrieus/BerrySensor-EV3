package com.example.berrysensor;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berrysensor.adapters.SensoresAdapter;
import com.example.berrysensor.datos.Repositorio;
import com.example.berrysensor.model.Sensor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MostrarSensoresActivity extends AppCompatActivity {

    private RecyclerView sensoresRecyclerView;
    private SensoresAdapter adapter;
    private List<Sensor> sensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_sensores);

        sensoresRecyclerView = findViewById(R.id.listaSensoresRecyclerView);
        sensoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensores = new ArrayList<>();
        //inicia la db de firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("sensores").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                if (doc != null) {
                                    Sensor sensor = doc.toObject(Sensor.class);
                                    sensores.add(sensor);
                                }
                            }
                            adapter = new SensoresAdapter(sensores);
                            sensoresRecyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(MostrarSensoresActivity.this,"Error al obtener los sensores",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        adapter = new SensoresAdapter(sensores);
        sensoresRecyclerView.setAdapter(adapter);
    }
}