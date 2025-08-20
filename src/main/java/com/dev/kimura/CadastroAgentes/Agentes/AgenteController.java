package com.dev.kimura.CadastroAgentes.Agentes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AgenteController {

    private AgenteService agenteService;

    AgenteController(AgenteService agenteService){
        this.agenteService = agenteService;
    }

    @GetMapping("/")
    public String bemVindo(){
        return "Bem vindo! este projeto é obra de Rafael Kimura, e provavelmente ele ainda estava treinando Spring!";
    }

    // Adicionar um Agente (Create)
    @PostMapping("/criar")
    public String criarAgente(){
        return "algo";
    }

    // Exibir todos os Agentes (Read)
    @GetMapping("/todos")
    public List<AgenteModel> listarAgentes(){
        return agenteService.listarAgentes();
    }

    // Exibir apenas um Agente (Read)
    @GetMapping("/todosID")
    public String exibirAgentePorID(){
        return "Mostrar agentes por ID";
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
