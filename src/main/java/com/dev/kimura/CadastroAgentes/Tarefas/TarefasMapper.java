package com.dev.kimura.CadastroAgentes.Tarefas;

import org.springframework.stereotype.Component;

@Component
public class TarefasMapper {


    public TarefasModel map(TarefasDTO tarefasDTO){

        TarefasModel tarefasModel = new TarefasModel();
        tarefasModel.setId(tarefasDTO.getId());
        tarefasModel.setAgentes(tarefasDTO.getAgentes());
        tarefasModel.setDescricao(tarefasDTO.getDescricao());
        tarefasModel.setResponsabilidade(tarefasDTO.getResponsabilidade());
        return tarefasModel;
    }
    public TarefasDTO map(TarefasModel tarefasModel){

        TarefasDTO tarefasDTO = new TarefasDTO();
        tarefasDTO.setId(tarefasModel.getId());
        tarefasDTO.setAgentes(tarefasModel.getAgentes());
        tarefasDTO.setDescricao(tarefasModel.getDescricao());
        tarefasDTO.setResponsabilidade(tarefasModel.getResponsabilidade());
        return tarefasDTO;
    }
}
