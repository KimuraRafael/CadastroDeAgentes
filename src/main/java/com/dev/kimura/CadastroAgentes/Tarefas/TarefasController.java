package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tarefas")
public class TarefasController {

    private final TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping("/listarTarefas")
    public ResponseEntity<List<TarefasDTO>> listarTarefas(){

        List<TarefasDTO> tarefasEncontradas = tarefasService.listarTarefas();

        return ResponseEntity.ok(tarefasEncontradas);
    }

    @GetMapping("/listarTarefa/{id}")
    public ResponseEntity<String> listarTarefasPorId(@PathVariable Long id){

        if(tarefasService.listarTarefaPorId(id) != null){
        TarefasDTO tarefaEncontrada = tarefasService.listarTarefaPorId(id);
        return ResponseEntity.ok("Tarefa Retornarda: "+ tarefaEncontrada.getDescricao());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada na agenda dos Agentes");

    }


    @PostMapping("/criarTarefa")
    public ResponseEntity<String> criarTarefa(@RequestBody TarefasDTO tarefasDTO){

        TarefasDTO novaTarefa = tarefasService.criarTarefa(tarefasDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tarefa criada com sucesso: " + "(ID):" + novaTarefa.getId() + " " + novaTarefa.getDescricao());

    }

    @PutMapping("/atualizaTarefa/{id}")
    public ResponseEntity<String> atualizaTarefa(@PathVariable Long id, @RequestBody TarefasDTO tarefaAtualizada){

        if (tarefasService.listarTarefaPorId(id) != null) {

            TarefasDTO tarefaAnterior = tarefasService.listarTarefaPorId(id);
            TarefasDTO tarefaAlterada = tarefasService.atualizaTarefas(id, tarefaAtualizada);

            Map<String, Object> resposta = Map.of(
                    "Descrição", tarefaAnterior.getDescricao() + " -> " + tarefaAlterada.getDescricao(),
                    "Agentes responsáveis:", "Anteriores: " + tarefaAnterior.getAgentes() + "\n Novos:" + tarefaAlterada.getAgentes()
            );
            return ResponseEntity.status(HttpStatus.OK).body("Tarefa alterada com Sucesso!\n" + resposta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa Não encontrado para edição");


    }

    @DeleteMapping("/deletarTarefa/{id}")
    public ResponseEntity<String> deletarTarefaPorId(@PathVariable Long id){



        if (tarefasService.listarTarefaPorId(id) != null) {
            tarefasService.deletarTarefaPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body("Tarefa " + id + "Deletada com Sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não existe");


    }

}
