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

    @GetMapping("/listarTarefas")
    public List<TarefasModel> listarTarefas(){

        return tarefasService.listarTarefas();
    }

    @GetMapping("/listarTarefa/{id}")
    public TarefasModel listarTarefasPorId(@PathVariable Long id){
        return tarefasService.listarTarefaPorId(id);
    }


    @PostMapping("/criarTarefa")
    public TarefasModel criarTarefa(@RequestBody TarefasModel tarefasModel){

        return tarefasService.criarTarefa(tarefasModel);
    }

    @PutMapping("/alterar")
    public String alterarTarefa(){
        return "Tarefa alterada com sucesso";
    }

    @DeleteMapping("/deletarTarefa/{id}")
    public void deletarTarefaPorId(@PathVariable Long id){

        tarefasService.deletarTarefaPorId(id);
    }

}
