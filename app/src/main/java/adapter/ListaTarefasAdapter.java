package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.aplicacoessql.R;
import models.Tarefas;

public class ListaTarefasAdapter extends RecyclerView.Adapter<ListaTarefasAdapter.MyViewHolder> {

    private List<Tarefas> tarefasList;

    public ListaTarefasAdapter(List<Tarefas> listaTarefas) {
        this.tarefasList = listaTarefas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterlayoutlistatarefas,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefas tarefas = tarefasList.get(position);
        holder.tarefa.setText(tarefas.getTarefaNome());
    }

    @Override
    public int getItemCount() {
        return this.tarefasList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tarefa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.texto_adapter_layout_tarefas_id);
        }
    }
}
