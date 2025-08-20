package com.dev.kimura.CadastroAgentes.Agentes;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenteService {

    private AgenteRepository agenteRepository;

     public AgenteService(AgenteRepository agenteRepository){
         this.agenteRepository = agenteRepository;
     }

     //Listar meus Agentes
    public List<AgenteModel> listarAgentes(){

         return agenteRepository.findAll();
    }
}
