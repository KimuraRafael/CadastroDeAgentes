package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tarefas")
public class TarefasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nivel_de_demanda")
    private String nivelDemanda;

    // OneToMany é a notation que define o relacionamento de 1 para N
    @OneToMany(mappedBy = "tarefas")
    @JsonIgnore
    private List<AgenteModel> agentes;


}
