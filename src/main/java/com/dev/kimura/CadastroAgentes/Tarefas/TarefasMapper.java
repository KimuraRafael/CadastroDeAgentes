package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TarefasMapper {

    public TarefasModel map(TarefasDTO tarefasDTO) {
        TarefasModel tarefasModel = new TarefasModel();
        tarefasModel.setId(tarefasDTO.getId());
        tarefasModel.setDescricao(tarefasDTO.getDescricao());
        tarefasModel.setNivelDemanda(tarefasDTO.getNivelDemanda());
        return tarefasModel;
    }

    public TarefasDTO map(TarefasModel tarefasModel) {
        TarefasDTO tarefasDTO = new TarefasDTO();
        tarefasDTO.setId(tarefasModel.getId());
        tarefasDTO.setDescricao(tarefasModel.getDescricao());
        tarefasDTO.setNivelDemanda(tarefasModel.getNivelDemanda());

        if (tarefasModel.getAgentes() != null) {
            List<AgenteDTO> agentesDTO = tarefasModel.getAgentes().stream()
                    .map(agente -> {
                        AgenteDTO dto = new AgenteDTO();
                        dto.setId(agente.getId());
                        dto.setNome(agente.getNome());
                        dto.setEmail(agente.getEmail());
                        dto.setIdade(agente.getIdade());
                        dto.setResponsabilidade(agente.getResponsabilidade());
                        dto.setUrlImage(agente.getUrlImage());
                        dto.setTipoAgentes(agente.getTipoAgentes());
                        return dto;
                    }).toList();
            tarefasDTO.setAgentes(agentesDTO);
        }

        return tarefasDTO;
    }
}

