package models;

import java.io.Serializable;

public class Tarefas implements Serializable { // para passar dados de uma act para outra
    private String tarefaNome;
    private Long id; //por causa do banco de dados

    public String getTarefaNome() {
        return tarefaNome;
    }

    public void setTarefaNome(String tarefaNome) {
        this.tarefaNome = tarefaNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
