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

import com.example.berrysensor.adapters.UbicacionesAdapter;
import com.example.berrysensor.datos.Repositorio;
import com.example.berrysensor.model.Ubicacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MostrarUbicacionesActivity extends AppCompatActivity {

    private RecyclerView ubicacionesRecyclerView;
    private UbicacionesAdapter adapter;
    private List<Ubicacion> ubicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ubicaciones);

        ubicacionesRecyclerView = findViewById(R.id.listaUbicacionesRecyclerView);
        ubicacionesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ubicaciones = new ArrayList<>();
        //inicializar la bd de firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("ubicaciones").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                if (doc != null) {
                                    Ubicacion ubicacion = doc.toObject(Ubicacion.class);
                                    ubicaciones.add(ubicacion);
                                }
                            }
                            adapter = new UbicacionesAdapter(ubicaciones);
                            ubicacionesRecyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(MostrarUbicacionesActivity.this,"Error al obtener las ubicaciones",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

        adapter = new UbicacionesAdapter(ubicaciones);
        ubicacionesRecyclerView.setAdapter(adapter);

    }
}