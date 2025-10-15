package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tarefas/ui")
public class TarefasControllerUI {

        private final TarefasService tarefasService;

        public TarefasControllerUI(TarefasService tarefasService) {
            this.tarefasService = tarefasService;
        }

        @GetMapping("/listarTarefas")
        public String listarTarefas(Model model){

            List<TarefasDTO> listaTarefas = tarefasService.listarTarefas();

            model.addAttribute("tarefas", listaTarefas);
            model.addAttribute("paginaAtiva", "tarefas");
            return "listarTarefas";

        }

        @GetMapping("/listarTarefa/{id}")
        public String listarTarefasPorId(@PathVariable Long id, Model model){

            TarefasDTO tarefa = tarefasService.listarTarefaPorId(id);

            if(tarefa != null){
                model.addAttribute("tarefa", tarefa);
                return "detalhesTarefa";
            }else{
                model.addAttribute("mensagem", "Tarefa não encontrada");
                return "listarTarefas";
            }
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
