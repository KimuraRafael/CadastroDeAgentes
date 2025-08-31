package com.dev.kimura.CadastroAgentes.Agentes;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteService {

    private AgenteRepository agenteRepository;

    public AgenteService(AgenteRepository agenteRepository){
         this.agenteRepository = agenteRepository;
     }

     // Função para listar meus Agentes
    public List<AgenteModel> listarAgentes(){

         return agenteRepository.findAll();
    }

    // Função para listar um agente específico
    public AgenteModel listarAgentePorId(Long id){

        Optional<AgenteModel> agentePorId = agenteRepository.findById(id);

        return agentePorId.orElse(null);
    }

    // Função para criar um novo agente
    public AgenteModel criarNovoAgente(AgenteModel agenteModel){

        return agenteRepository.save(agenteModel);
    }

    // Delete tem que ser um metodo void pois não há necessidade retornar nada para o servidor
    public void deletarAgentePorId(Long id){
        agenteRepository.deleteById(id);
    }
}
