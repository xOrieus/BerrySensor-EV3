package com.example.berrysensor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berrysensor.R;
import com.example.berrysensor.model.Sensor;

import java.util.List;

public class
SensoresAdapter extends RecyclerView.Adapter<SensoresAdapter.ViewHolder> {

    private List<Sensor> sensores;

    public SensoresAdapter(List<Sensor> sensores) {
        this.sensores = sensores;
    }
    // enlazar el layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_mostrar_sensores, parent, false);
        return new ViewHolder(view);
    }
    // getNombre viene del model Sensor
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewNombre().setText(sensores.get(position).getNombre());
        holder.getTextViewDescripcion().setText(sensores.get(position).getDescripcion());
        // se pone como float el valor y se le pasa como string
        float idealValor = sensores.get(position).getIdeal();
        holder.getTextViewIdeal().setText(String.valueOf(idealValor));
        holder.getTextViewUbicacion().setText(sensores.get(position).getUbicacion().getNombre());
        holder.getTextViewTipo().setText(sensores.get(position).getTipoSensor().getNombre());
    }

    @Override
    public int getItemCount() {
        return sensores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private TextView textViewDescripcion;
        private TextView textViewIdeal;
        private TextView textViewTipo;
        private TextView textViewUbicacion;


        public ViewHolder(View view) {
            super(view);
            textViewNombre = view.findViewById(R.id.textViewNombre);
            textViewDescripcion = view.findViewById(R.id.textViewDescripcion);
            textViewIdeal = view.findViewById(R.id.textViewIdeal);
            textViewTipo = view.findViewById(R.id.textViewTipo);
            textViewUbicacion = view.findViewById(R.id.textViewUbicacion);
        }
        public TextView getTextViewNombre(){
            return textViewNombre;
        }

        public TextView getTextViewDescripcion() {
            return textViewDescripcion;
        }
        public TextView getTextViewIdeal() {
            return textViewIdeal;
        }

        public TextView getTextViewTipo() {
            return textViewTipo;
        }

        public TextView getTextViewUbicacion() {
            return textViewUbicacion;
        }
    }
}
