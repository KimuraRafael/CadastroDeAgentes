package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteDTO;
import com.dev.kimura.CadastroAgentes.Agentes.AgenteModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefasDTO {

    private Long id;
    private String descricao;
    private String nivelDemanda;
    private List<AgenteDTO> agentes;
}
