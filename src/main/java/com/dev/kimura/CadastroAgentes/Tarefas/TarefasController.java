package com.dev.kimura.CadastroAgentes.Tarefas;

import com.dev.kimura.CadastroAgentes.Agentes.AgenteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Listagem de todos os Tarefas", description = "Essa rota é responsável por retornar uma lista de todos as tarefas cadastradas")
    public ResponseEntity<List<TarefasDTO>> listarTarefas(){

        List<TarefasDTO> tarefasEncontradas = tarefasService.listarTarefas();

        return ResponseEntity.ok(tarefasEncontradas);
    }

    @GetMapping("/listarTarefa/{id}")
    @Operation(summary = "Listagem de uma única Tarefa por ID", description = "Essa rota é responsável por retornar uma Tarefa cadastrada de acordo com o ID inserido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa Retornarda: descrição da tarefa"),
            @ApiResponse(responseCode = "400", description = "Tarefa não encontrada na agenda dos Agentes")
    })
    public ResponseEntity<String> listarTarefasPorId(@Parameter(description = "Usuário manda o ID no caminho da requisição")
                                                     @PathVariable Long id){

        if(tarefasService.listarTarefaPorId(id) != null){
        TarefasDTO tarefaEncontrada = tarefasService.listarTarefaPorId(id);
        return ResponseEntity.ok("Tarefa Retornarda: "+ tarefaEncontrada.getDescricao());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada na agenda dos Agentes");

    }


    @PostMapping("/criarTarefa")
    @Operation(summary = "Cria uma nova Tarefa", description = "Essa rota é responsável por permitir a criação de um nova tarefa com base nos dados básicos fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso: (ID): id - descrição da tarefa"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da Tarefa")
    })
    public ResponseEntity<String> criarTarefa(
            @Parameter(description = "Converte o JSON recebido no DTO de tarefas")
            @RequestBody TarefasDTO tarefasDTO,
            @Parameter(description = "Lista de IDs dos agentes associados à tarefa (opcional)")
            @RequestParam(required = false) List<Long> agenteIds) {

        TarefasDTO novaTarefa = tarefasService.criarTarefa(tarefasDTO, agenteIds);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tarefa criada com sucesso: (ID): " + novaTarefa.getId() + " - " + novaTarefa.getDescricao());
    }

    @PutMapping("/atualizaTarefa/{id}")
    @Operation(summary = "Altera Tarefa por ID", description = "Essa rota é responsável por alterar os dados de uma Tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa alterada com Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Tarefa Não encontrado para edição")
    })
    public ResponseEntity<String> atualizaTarefa(@Parameter(description = "Usuário manda o ID no caminho da requisição")
                                                 @PathVariable Long id,
                                                 @Parameter(description = "Converte o JSON recebido no DTO de tarefas para atualização de dados")
                                                 @RequestBody TarefasDTO tarefaAtualizada){

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
    @Operation(summary = "Deleta o tarefa por ID", description = "Essa rota é responsável por deletar os dados de uma Tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa id da tarefa deletada com Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Tarefa não existe")
    })
    public ResponseEntity<String> deletarTarefaPorId(@Parameter(description = "Usuário manda o ID no caminho da requisição")
                                                     @PathVariable Long id){

        if (tarefasService.listarTarefaPorId(id) != null) {
            tarefasService.deletarTarefaPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body("Tarefa " + id + " Deletada com Sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não existe");


    }

}
