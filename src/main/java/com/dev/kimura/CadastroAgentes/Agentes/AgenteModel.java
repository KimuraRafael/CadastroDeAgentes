package com.dev.kimura.CadastroAgentes.Agentes;

import com.dev.kimura.CadastroAgentes.Enums.TipoAgentes;
import com.dev.kimura.CadastroAgentes.Tarefas.TarefasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// A Entity transforma a classe em uma entidade do BD
// JPA = Java Persistence API, e dele que vem as informações relacionadas a Banco de Dados
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agentes")
public class AgenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private int idade;

    private TipoAgentes tipoAgentes;

    // ManyToOne é a notation para definir o relacionamento de N para 1
    @ManyToOne
    @JoinColumn(name = "tarefas_id") //Definindo Chave Estrangeira
    private TarefasModel tarefas;
}
