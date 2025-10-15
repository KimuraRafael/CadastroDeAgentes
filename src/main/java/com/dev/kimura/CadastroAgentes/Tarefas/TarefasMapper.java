package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteDTO;
import com.dev.kimura.CadastroAgentes.Agentes.AgenteModel;
import com.dev.kimura.CadastroAgentes.Enums.TipoAgentes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarefasMapper {

    // DTO → Model
    public TarefasModel map(TarefasDTO tarefasDTO) {
        TarefasModel tarefasModel = new TarefasModel();
        tarefasModel.setId(tarefasDTO.getId());
        tarefasModel.setDescricao(tarefasDTO.getDescricao());
        tarefasModel.setNivelDemanda(tarefasDTO.getNivelDemanda());

        if (tarefasDTO.getAgentes() != null) {
            List<AgenteModel> agentes = tarefasDTO.getAgentes().stream()
                    .map(dto -> {
                        AgenteModel agente = new AgenteModel();
                        agente.setId(dto.getId());
                        agente.setNome(dto.getNome());
                        agente.setEmail(dto.getEmail());
                        agente.setIdade(dto.getIdade());
                        agente.setResponsabilidade(dto.getResponsabilidade());
                        agente.setUrlImage(dto.getUrlImage());
                        agente.setTipoAgentes(dto.getTipoAgentes());
                        agente.setTarefas(tarefasModel); // relação bidirecional
                        return agente;
                    }).toList();
            tarefasModel.setAgentes(agentes);
        }

        return tarefasModel;
    }

    // Model → DTO
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
            tarefasDTO.setAgentes(agentesDTO); // só setar os DTOs, nunca as entidades
        }

        return tarefasDTO;
    }
}
