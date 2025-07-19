package com.dev.kimura.CadastroAgentes;

import jakarta.persistence.*;

// A Entity transforma a classe em uma entidade do BD
// JPA = Java Persistence API, e dele que vem as informações relacionadas a Banco de Dados
@Entity
@Table(name = "agentes")
public class AgenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String email;
    int idade;

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
}
