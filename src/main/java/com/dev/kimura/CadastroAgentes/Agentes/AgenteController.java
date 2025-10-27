package com.dev.kimura.CadastroAgentes.Agentes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class AgenteController {

    private final AgenteService agenteService;

    AgenteController(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    @GetMapping("/")
    @Operation(summary = "Boas vindas ao Projeto", description = "Essa rota é a padrão inicial do meu projeto")
    public String bemVindo() {
        return "Bem vindo! este projeto é obra de Rafael Kimura, e provavelmente ele ainda estava treinando Spring!";
    }


    // Exibir todos os Agentes (Read)
    @GetMapping("/listarAgente")
    @Operation(summary = "Listagem de todos os Agentes", description = "Essa rota é responsável por retornar uma lista de todos os agentes cadastrados")
    public ResponseEntity<List<AgenteDTO>> listarAgentes() {
      List<AgenteDTO> listaAgentes = agenteService.listarAgentes();

        return ResponseEntity.ok(listaAgentes);

    }

    // Exibir apenas um Agente (Read)
    @GetMapping("/listarAgente/{id}")
    @Operation(summary = "Listagem de um único Agente por ID", description = "Essa rota é responsável por retornar um Agente cadastrado de acordo com o ID inserido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agente encontrado: Agente"),
            @ApiResponse(responseCode = "400", description = "Agente Não encontrado na lista")
    })
    public ResponseEntity<String> listarAgentePorID(@Parameter(description = "Usuário manda o ID no caminho da requisição")
                                                    @PathVariable Long id) {

        if(agenteService.listarAgentePorId(id) != null) {
            AgenteDTO agenteEncontrado = agenteService.listarAgentePorId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Agentes encontrados:\n" + agenteEncontrado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agente Não encontrado na lista");
    }

    // Adicionar um Agente (Create)
    @PostMapping("/criarAgente")
    @Operation(summary = "Cria um novo Agente", description = "Essa rota é responsável por permitir a criação de um novo agente com base nos dados básicos fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agente criado com sucesso: (ID): id - nome do agente"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do Agente")
    })
    public ResponseEntity<String> criarAgente(@RequestBody AgenteDTO agente) {

        AgenteDTO novoAgente = agenteService.criarNovoAgente(agente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Agente criado com sucesso: " + "(ID):" + novoAgente.getId() + " - " + novoAgente.getNome());
    }

    // Alterar os dados dos Agentes (Update)
    @PutMapping("/atualizarAgente/{id}")
    @Operation(summary = "Altera agente por ID", description = "Essa rota é responsável por alterar os dados de um Agente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agente Alterado com Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Agente Não encontrado para edição")
    })
    public ResponseEntity<String> atualizarAgentePorID(@Parameter(description = "Usuário manda o ID no caminho da requisição")
                                                       @PathVariable Long id,
                                                       @Parameter(description = "Usuário os dados atualizados no corpo da requisição")
                                                       @RequestBody AgenteDTO agenteAtualizado) {

        if (agenteService.listarAgentePorId(id) != null) {

            AgenteDTO agenteAntigo = agenteService.listarAgentePorId(id);
            AgenteDTO agenteAlterado = agenteService.atualizarAgente(id, agenteAtualizado);

            Map<String, Object> resposta = Map.of(
                    "nome", agenteAntigo.getNome() + " -> " + agenteAtualizado.getNome(),
                    "email", agenteAntigo.getEmail() + " -> " + agenteAtualizado.getEmail(),
                    "idade", agenteAntigo.getIdade() + " -> " + agenteAtualizado.getIdade(),
                    "nivelDeDemanda", agenteAntigo.getResponsabilidade() + " -> " + agenteAtualizado.getResponsabilidade()
            );
            return ResponseEntity.status(HttpStatus.OK).body("Agente Alterado com Sucesso!\n" + resposta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agente Não encontrado para edição");

    }

    // Deletar um Agente (Delete)
    @DeleteMapping("/deletarAgente/{id}")
    @Operation(summary = "Deleta o agente por ID", description = "Essa rota é responsável por deletar os dados de um Agente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agente id do agente  deletada com Sucesso!"),
            @ApiResponse(responseCode = "400", description = "Agente não existe")
    })
    public ResponseEntity<String> deletarAgentePorID( @Parameter(description = "Usuário manda o ID no caminho da requisição")
                                                      @PathVariable Long id) {

        if (agenteService.listarAgentePorId(id) != null) {
            agenteService.deletarAgentePorId(id);
            return ResponseEntity.status(HttpStatus.OK).body("Agente "+ id + "deletada com Sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agente não existe");


    }


}
