package com.example.proyectofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorTareas extends RecyclerView.Adapter<AdaptadorTareas.ViewHolder> {

    private Context context;
    private List<Tareas> listaTareas;
    private OnTareaClickListener listener;
    private OnTareaLongClickListener longClickListener;

    // Interfaz para notificar a MainActivity sobre la tarea seleccionada
    public interface OnTareaClickListener {
        void onTareaClick(Tareas tarea);
    }

    // Nueva interfaz para manejar el long click
    public interface OnTareaLongClickListener {
        void onTareaLongClick(View vista, Tareas tarea);
    }

    public AdaptadorTareas(Context context, List<Tareas> listaTareas, OnTareaClickListener listener, OnTareaLongClickListener longClickListener) {
        this.context = context;
        this.listaTareas = listaTareas;
        this.listener = listener;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tareas tarea = listaTareas.get(position);
        holder.txtTitulo.setText(tarea.getTitulo());
        holder.txtDescripcion.setText(tarea.getDescripcion());
        holder.txtGrupo.setText(tarea.getGrupo());
        holder.txtFechaLimite.setText(tarea.getFechaLimite());
        holder.chkRealizada.setChecked(tarea.isRealizada());

        // Al hacer clic en el ítem, se comunica a MainActivity la tarea seleccionada
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTareaClick(tarea);
            }
        });

        // Al hacer un long click, se invoca el longClickListener para mostrar el menú flotante
        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onTareaLongClick(v, tarea); // Llama al long click listener
                return true; // Evita que el evento se propague
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtDescripcion, txtGrupo, txtFechaLimite;
        CheckBox chkRealizada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtGrupo = itemView.findViewById(R.id.txtGrupo);
            txtFechaLimite = itemView.findViewById(R.id.txtFechaLimite);
            chkRealizada = itemView.findViewById(R.id.chkRealizada);
        }
    }
}




