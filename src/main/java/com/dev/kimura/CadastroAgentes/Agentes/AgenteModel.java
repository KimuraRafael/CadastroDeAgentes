package com.dev.kimura.CadastroAgentes.Agentes;

import com.dev.kimura.CadastroAgentes.Enums.TipoAgentes;
import com.dev.kimura.CadastroAgentes.Tarefas.TarefasModel;
import jakarta.persistence.*;

import java.util.List;

// A Entity transforma a classe em uma entidade do BD
// JPA = Java Persistence API, e dele que vem as informações relacionadas a Banco de Dados
@Entity
@Table(name = "agentes")
public class AgenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private int idade;
    private TipoAgentes tipoAgentes;
    private List<TarefasModel> tarefas;

    public AgenteModel(){

    }

    public AgenteModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public TipoAgentes getTipoAgentes() {return tipoAgentes;}

    public void setTipoAgentes(TipoAgentes tipoAgentes) {this.tipoAgentes = tipoAgentes;}
}
