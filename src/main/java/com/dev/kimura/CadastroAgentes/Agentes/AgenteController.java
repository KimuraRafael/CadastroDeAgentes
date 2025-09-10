package com.dev.kimura.CadastroAgentes.Agentes;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class AgenteController {

    private AgenteService agenteService;

    AgenteController(AgenteService agenteService){
        this.agenteService = agenteService;
    }

    @GetMapping("/")
    public String bemVindo(){
        return "Bem vindo! este projeto é obra de Rafael Kimura, e provavelmente ele ainda estava treinando Spring!";
    }



    // Exibir todos os Agentes (Read)
    @GetMapping("/listarAgente")
    public List<AgenteDTO> listarAgentes(){
        return agenteService.listarAgentes();
    }

    // Exibir apenas um Agente (Read)
    @GetMapping("/listarAgente/{id}")
    public AgenteDTO listarAgentePorID(@PathVariable Long id){
        return agenteService.listarAgentePorId(id);
    }

    // Adicionar um Agente (Create)
    @PostMapping("/criarAgente")
    public AgenteDTO criarAgente(@RequestBody AgenteDTO agente){
        return agenteService.criarNovoAgente(agente);
    }

    // Alterar os dados dos Agentes (Update)
    @PutMapping ("/atualizarAgente/{id}")
    public AgenteDTO atualizarAgentePorID(@PathVariable Long id, @RequestBody AgenteDTO agenteAtualizado){
       return agenteService.atualizarAgente(id, agenteAtualizado);
    }

    // Deletar um Agente (Delete)
    @DeleteMapping ("/deletarAgente/{id}")
    public void deletarAgentePorID(@PathVariable Long id){
        agenteService.deletarAgentePorId(id);
    }



}
