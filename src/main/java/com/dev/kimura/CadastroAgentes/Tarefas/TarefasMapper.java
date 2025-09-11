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
        tarefasModel.setResponsabilidade(tarefasDTO.getResponsabilidade());

        if (tarefasDTO.getAgentes() != null) {
            List<AgenteModel> agentes = tarefasDTO.getAgentes().stream()
                    .map(dto -> {
                        AgenteModel agente = new AgenteModel();
                        agente.setId(dto.getId());
                        agente.setNome(dto.getNome());
                        agente.setEmail(dto.getEmail());
                        agente.setIdade(dto.getIdade());
                        agente.setNivelDeDemanda(dto.getNivelDeDemanda());
                        agente.setUrlImage(dto.getUrlImage());
                        agente.setTipoAgentes(TipoAgentes.valueOf(String.valueOf(dto.getTipoAgentes())));
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
        tarefasDTO.setResponsabilidade(tarefasModel.getResponsabilidade());

        if (tarefasModel.getAgentes() != null) {
            List<AgenteDTO> agentesDTO = tarefasModel.getAgentes().stream()
                    .map(agente -> {
                        AgenteDTO dto = new AgenteDTO();
                        dto.setId(agente.getId());
                        dto.setNome(agente.getNome());
                        dto.setEmail(agente.getEmail());
                        dto.setIdade(agente.getIdade());
                        dto.setNivelDeDemanda(agente.getNivelDeDemanda());
                        dto.setUrlImage(agente.getUrlImage());
                        dto.setTipoAgentes(TipoAgentes.valueOf(agente.getTipoAgentes().toString()));
                        return dto;
                    }).toList();
            tarefasDTO.setAgentes(agentesDTO); // só setar os DTOs, nunca as entidades
        }

        return tarefasDTO;
    }
}
