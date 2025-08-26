package com.dev.kimura.CadastroAgentes.Agentes;

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
    public List<AgenteModel> listarAgentes(){
        return agenteService.listarAgentes();
    }

    // Exibir apenas um Agente (Read)
    @GetMapping("/listarAgente/{id}")
    public AgenteModel listarAgentePorID(@PathVariable Long id){

        return agenteService.listarAgentePorId(id);
    }

    // Adicionar um Agente (Create)
    @PostMapping("/criarAgente")
    public AgenteModel criarAgente(@RequestBody AgenteModel agente){

        return agenteService.criarNovoAgente(agente);
    }

    // Alterar os dados dos Agentes (Update)
    @PutMapping ("/alterarID")
    public String alteraAgentePorID(){
        return "Alterar agentes por ID";
    }

    // Deletar um Agente (Delete)

    @DeleteMapping ("/deletarID")
    public String deletarAgentePorID(){
        return "Agente Deletado por ID";
    }



}
