package helper;

import java.util.List;

import models.Tarefas;

public interface iTarefasDAO {

    public boolean salvar(Tarefas tarefas);
    public boolean deletar(Tarefas tarefas);
    public boolean atualizar(Tarefas tarefas);
    public List<Tarefas> listarTarefas();

}
