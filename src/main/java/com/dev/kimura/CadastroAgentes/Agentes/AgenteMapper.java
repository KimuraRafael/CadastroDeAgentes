package com.dev.kimura.CadastroAgentes.Agentes;

import org.springframework.stereotype.Component;

@Component
public class AgenteMapper {

    public AgenteModel map(AgenteDTO agenteDTO){
        AgenteModel agenteModel = new AgenteModel();
        agenteModel.setId(agenteDTO.getId());
        agenteModel.setTipoAgentes(agenteDTO.getTipoAgentes());
        agenteModel.setNome(agenteDTO.getNome());
        agenteModel.setIdade(agenteDTO.getIdade());
        agenteModel.setEmail(agenteDTO.getEmail());
        agenteModel.setNivelDeDemanda(agenteDTO.getNivelDeDemanda());
        agenteModel.setUrlImage(agenteDTO.getUrlImage());
        agenteModel.setIdade(agenteDTO.getIdade());

        return agenteModel;
    }

    public AgenteDTO map(AgenteModel agenteModel){
        AgenteDTO agenteDTO = new AgenteDTO();
        agenteDTO.setId(agenteModel.getId());
        agenteDTO.setTipoAgentes(agenteModel.getTipoAgentes());
        agenteDTO.setNome(agenteModel.getNome());
        agenteDTO.setIdade(agenteModel.getIdade());
        agenteDTO.setEmail(agenteModel.getEmail());
        agenteDTO.setNivelDeDemanda(agenteModel.getNivelDeDemanda());
        agenteDTO.setUrlImage(agenteModel.getUrlImage());
        agenteDTO.setIdade(agenteModel.getIdade());

        return agenteDTO;
    }
}
