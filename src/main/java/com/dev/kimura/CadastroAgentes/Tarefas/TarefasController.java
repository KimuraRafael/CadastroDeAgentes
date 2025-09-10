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
    public List<TarefasDTO> listarTarefas(){

        return tarefasService.listarTarefas();
    }

    @GetMapping("/listarTarefa/{id}")
    public TarefasDTO listarTarefasPorId(@PathVariable Long id){

        return tarefasService.listarTarefaPorId(id);
    }


    @PostMapping("/criarTarefa")
    public TarefasDTO criarTarefa(@RequestBody TarefasDTO tarefasDTO){

        return tarefasService.criarTarefa(tarefasDTO);
    }

    @PutMapping("/atualizaTarefa/{id}")
    public TarefasDTO atualizaTarefa(@PathVariable Long id, @RequestBody TarefasDTO tarefaAtualizada){

        return tarefasService.atualizaTarefas(id, tarefaAtualizada);
    }

    @DeleteMapping("/deletarTarefa/{id}")
    public void deletarTarefaPorId(@PathVariable Long id){

        tarefasService.deletarTarefaPorId(id);
    }

}
