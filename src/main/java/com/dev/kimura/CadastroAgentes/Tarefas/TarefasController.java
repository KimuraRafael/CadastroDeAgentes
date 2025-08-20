package com.dev.kimura.CadastroAgentes.Tarefas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefasController {

    private TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping("/listarTarefa")
    public List<TarefasModel> listarTarefas(){

        return tarefasService.listarTarefas();
    }

    @GetMapping("/listarTarefa/{id}")
    public TarefasModel listarTarefasPorId(@PathVariable Long id){
        return tarefasService.listarTarefaPorId(id);
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
