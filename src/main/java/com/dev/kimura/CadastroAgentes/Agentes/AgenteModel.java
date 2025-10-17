package com.dev.kimura.CadastroAgentes.Agentes;

import com.dev.kimura.CadastroAgentes.Enums.TipoAgentes;
import com.dev.kimura.CadastroAgentes.Tarefas.TarefasModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

// A Entity transforma a classe em uma entidade do BD
// JPA = Java Persistence API, e dele que vem as informações relacionadas a Banco de Dados
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tarefas")
@Table(name = "agentes")

public class AgenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    @Column(name = "Foto_Agente")
    private String urlImage;


    @Column(name = "Tipo_Agente")
    private TipoAgentes tipoAgentes;

    @Column(name = "responsabilidade")
    private String responsabilidade;

    // ManyToOne é a notation para definir o relacionamento de N para 1
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tarefas_id") //Definindo Chave Estrangeira
    private TarefasModel tarefas;
}
