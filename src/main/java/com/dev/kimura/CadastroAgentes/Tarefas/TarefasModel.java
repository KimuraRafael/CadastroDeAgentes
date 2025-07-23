package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteModel;
import jakarta.persistence.*;

@Entity
@Table(name = "tarefas")
public class TarefasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String gravidade;
    private AgenteModel agentes;

    public TarefasModel(){

    }

    public TarefasModel(String descricao, String gravidade, AgenteModel agentes) {
        this.descricao = descricao;
        this.gravidade = gravidade;
        this.agentes = agentes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public AgenteModel getAgentes() {
        return agentes;
    }

    public void setAgentes(AgenteModel agentes) {
        this.agentes = agentes;
    }
}
