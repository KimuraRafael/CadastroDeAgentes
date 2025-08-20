package com.dev.kimura.CadastroAgentes.Tarefas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarefas")
public class TarefasController {


    @GetMapping("/listar")
    public String listarTarefas(){
        return "Tarefas listadas com sucesso";
    }


    @PostMapping("/criar")
    public String criarTarefa(){
        return "Tarefa criada com sucesso";
    }

    @PutMapping("/alterar")
    public String alterarTarefa(){
        return "Tarefa alterada com sucesso";
    }

    @DeleteMapping("/deletar")
    public String deletarTarefa(){
        return "Tarefa deletada com sucesso";
    }

}
